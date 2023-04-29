package com.example.vynils.repository

import android.content.Context
import com.android.volley.Response
import com.example.vynils.network.NetworkServiceAdapter
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
        val apiService = NetworkServiceAdapter(context)
        val responseListener = apiService.fetchArtists()


        val artistListType: Type = object : TypeToken<List<ResponseArtistDTO>>() {}.type
        val responseArtists: List<ResponseArtistDTO> =
            gson.fromJson(responseListener, artistListType)
        responseArtists.map { responseArtistToArtist(it) }
    }
}