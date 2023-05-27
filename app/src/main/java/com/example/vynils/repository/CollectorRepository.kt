package com.example.vynils.repository

import android.content.Context
import android.util.Log
import com.example.vynils.dto.ResponseCollectorDTO
import com.example.vynils.model.Collector
import com.example.vynils.model.Comment
import com.example.vynils.model.CollectorAlbum
import com.example.vynils.model.Performer
import com.example.vynils.network.NetworkServiceAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class CollectorRepository {
    private val gson = Gson()

    private fun responseCollectorToCollector(responseCollector: ResponseCollectorDTO): Collector {
        return Collector(
            id = responseCollector.id,
            name = responseCollector.name,
            telephone = responseCollector.telephone,
            email = responseCollector.email,
            comments = responseCollector.comments.map { Comment(it.id, it.description, it.rating) },
            favoritePerformers = responseCollector.favoritePerformers.map {
                Performer(
                    id = it.id,
                    name = it.name,
                    image = it.image,
                    description = it.description,
                    date = it.birthDate ?: it.creationDate
                )
            },
            collectorAlbums = responseCollector.collectorAlbums.map {
                CollectorAlbum(
                    id = it.id,
                    price = it.price,
                    status = it.status,
                )
            }
        )
    }


    suspend fun fetchCollectors(
        context: Context
    ): List<Collector> = withContext(Dispatchers.IO) {
        val apiService = NetworkServiceAdapter(context)

        val responseListener = apiService.fetchCollectors()
        Log.d("ResponseRepository", "API call with $responseListener")
        val collectorListType: Type = object : TypeToken<List<ResponseCollectorDTO>>() {}.type
        val responseCollectors: List<ResponseCollectorDTO> = gson.fromJson(responseListener, collectorListType)
        responseCollectors.map { responseCollectorToCollector(it) }
    }
}
