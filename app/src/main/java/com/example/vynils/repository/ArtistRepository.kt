package com.example.vynils.repository

import android.content.Context
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
        val mainAlbumArtistDTO = responseArtist.albumsArtists.firstOrNull() ?: AlbumArtistDTO(
            -1,
            "Unknown",
            "",
            "",
            "",
            "",
            ""
        )
        return Artist(
            id = responseArtist.id,
            name = responseArtist.name,
            image = responseArtist.image,
            description = responseArtist.description,
            birthDate = responseArtist.birthDate,
            albums = AlbumArtist(
                id = mainAlbumArtistDTO.id,
                name = mainAlbumArtistDTO.name,
                cover = mainAlbumArtistDTO.cover,
                releaseDate = mainAlbumArtistDTO.releaseDate,
                description = mainAlbumArtistDTO.description,
                genre = Genre.SALSA,
                recordLabel = RecordLabel.ELEKTRA
            )
        )
    }

    suspend fun fetchArtists(context: Context): List<Artist> = withContext(Dispatchers.IO) {
        val apiService = ApiService(context)

        val responseListener = suspendCancellableCoroutine<String> { continuation ->
            val request = ApiService.getRequest("musicians",
                Response.Listener { response -> continuation.resume(response) },
                Response.ErrorListener { error -> continuation.resumeWithException(error) }
            )
            apiService.instance.add(request)

            continuation.invokeOnCancellation {
                request.cancel()
            }
        }

        val artistListType: Type = object : TypeToken<List<ResponseArtistDTO>>() {}.type
        val responseArtists: List<ResponseArtistDTO> =
            gson.fromJson(responseListener, artistListType)
        responseArtists.map { responseArtistToArtist(it) }
    }
}