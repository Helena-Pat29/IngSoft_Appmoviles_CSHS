package com.example.vynils.repository

import android.content.Context
import com.example.vynils.network.NetworkServiceAdapter
import com.example.vynils.dto.ResponseArtistDTO
import com.example.vynils.model.Artist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class ArtistRepository {
    private val gson = Gson()

    private fun responseArtistToArtist(responseArtist: ResponseArtistDTO): Artist {
        return Artist(
            id = responseArtist.id,
            name = responseArtist.name,
            image = responseArtist.image,
            description = responseArtist.description,
            birthDate = responseArtist.birthDate,
            albums = responseArtist.albums
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