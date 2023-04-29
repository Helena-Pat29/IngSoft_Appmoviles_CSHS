package com.example.vynils.repository

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.example.vynils.DTO.AlbumArtistDTO
import com.example.vynils.DTO.ResponseArtistDTO
import com.example.vynils.brokers.ApiService
import com.example.vynils.genre.Genre
import com.example.vynils.model.AlbumArtist
import com.example.vynils.model.Artist
import com.example.vynils.recordlabel.RecordLabel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ArtistRepository {
    private val gson = Gson()

    private fun responseArtistToArtist(responseArtist: ResponseArtistDTO): Artist {
        val albumArtists = responseArtist.albumsArtists?.map { albumArtistDTO ->
            AlbumArtist(
                id = albumArtistDTO.id,
                name = albumArtistDTO.name,
                cover = albumArtistDTO.cover,
                releaseDate = albumArtistDTO.releaseDate,
                description = albumArtistDTO.description,
                genre = Genre.valueOf(albumArtistDTO.genre.uppercase()),
                recordLabel = RecordLabel.valueOf(albumArtistDTO.recordLabel.uppercase())
            )
        } ?: emptyList()
        return Artist(
            id = responseArtist.id,
            name = responseArtist.name,
            image = responseArtist.image,
            description = responseArtist.description,
            birthDate = responseArtist.birthDate,
            albums = albumArtists
        )
    }

    suspend fun fetchArtists(context: Context): List<Artist> = withContext(Dispatchers.IO) {
        val apiService = ApiService(context)

        val responseListener = suspendCancellableCoroutine<String> { continuation ->
            val request = ApiService.getRequest("musicians",
                Response.Listener { response -> continuation.resume(response) },
                Response.ErrorListener { error -> continuation.resumeWithException(error) }
            )
            Log.d("log1", request.toString())
            apiService.instance.add(request)

            continuation.invokeOnCancellation {
                request.cancel()
            }
        }

        val artistListType: Type = object : TypeToken<List<ResponseArtistDTO>>() {}.type
        val responseArtists: List<ResponseArtistDTO> =
            gson.fromJson(responseListener, artistListType)
        Log.d("log4", responseArtists.toString())
        responseArtists.map { responseArtistToArtist(it) }
    }
}