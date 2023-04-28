package com.example.vynils.repository

import android.content.Context
import com.android.volley.Response
import com.example.vynils.DTO.PerformerDTO
import com.example.vynils.DTO.ResponseAlbumDTO
import com.example.vynils.brokers.ApiService
import com.example.vynils.genre.Genre
import com.example.vynils.model.Album
import com.example.vynils.model.Artist
import com.example.vynils.model.Performer
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

    private fun responseArtistToArtist(responseArtist: PerformerDTO): Artist {
        //val ResponseAlbumDTO = responseArtist.albums.firstOrNull() ?: ResponseAlbumDTO(-1, "Unknown", "", "", "", "", "")
        return Artist(
            id = responseArtist.id,
            name = responseArtist.name,
            image = responseArtist.image,
            description = responseArtist.description,
            birthDate = responseArtist.birthDate,

        )
    }

    suspend fun fetchArtists(context: Context): List<Artist> = withContext(Dispatchers.IO) {
        val apiService = ApiService(context)

        val responseListener = suspendCancellableCoroutine<String> { continuation ->
            val request = ApiService.getRequest("artists",
                Response.Listener { response -> continuation.resume(response) },
                Response.ErrorListener { error -> continuation.resumeWithException(error) }
            )
            apiService.instance.add(request)

            continuation.invokeOnCancellation {
                request.cancel()
            }
        }

        val artistListType: Type = object : TypeToken<List<PerformerDTO>>() {}.type
        val responseArtists: List<PerformerDTO> = gson.fromJson(responseListener, artistListType)
        responseArtists.map { responseArtistToArtist(it) }
    }
}