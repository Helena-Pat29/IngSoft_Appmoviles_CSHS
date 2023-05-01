package com.example.vynils.repository

import java.lang.reflect.Type
import com.example.vynils.brokers.ApiService
import com.example.vynils.DTO.ResponseAlbumDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.android.volley.Response
import android.content.Context
import com.example.vynils.DTO.PerformerDTO
import com.example.vynils.genre.Genre
import com.example.vynils.model.Album
import com.example.vynils.model.Performer
import com.example.vynils.network.NetworkServiceAdapter
import com.example.vynils.recordlabel.RecordLabel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AlbumRepository {
    private val gson = Gson()

    private fun responseAlbumToAlbum(responseAlbum: ResponseAlbumDTO): Album {
        val mainPerformerDTO = responseAlbum.performers.firstOrNull() ?: PerformerDTO(-1, "Unknown", "", "", "")
        return Album(
            id = responseAlbum.id,
            name = responseAlbum.name,
            cover = responseAlbum.cover,
            releaseDate = responseAlbum.releaseDate,
            description = responseAlbum.description,
            genre = Genre.valueOf(responseAlbum.genre.uppercase()),
            recordLabel = RecordLabel.valueOf(responseAlbum.recordLabel.uppercase()),
            track = responseAlbum.tracks,
            mainPerformer = Performer(
                id = mainPerformerDTO.id,
                name = mainPerformerDTO.name,
                image = mainPerformerDTO.image,
                description = mainPerformerDTO.description,
                birthDate = mainPerformerDTO.birthDate
            )
        )
    }

    suspend fun fetchAlbums(
        context: Context
    ): List<Album> = withContext(Dispatchers.IO) {
        val apiService = NetworkServiceAdapter(context)

        val responseListener = apiService.fetchAlbums()

        val albumListType: Type = object : TypeToken<List<ResponseAlbumDTO>>() {}.type
        val responseAlbums: List<ResponseAlbumDTO> = gson.fromJson(responseListener, albumListType)
        responseAlbums.map { responseAlbumToAlbum(it) }
    }
}
