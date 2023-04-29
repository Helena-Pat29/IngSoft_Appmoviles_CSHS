package com.example.vynils.network
import android.content.Context
import com.android.volley.Response
import com.example.vynils.brokers.ApiService
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NetworkServiceAdapter (private val context: Context) {
    private val apiService = ApiService(context)

    suspend fun fetchAlbums(): String = suspendCancellableCoroutine { continuation ->
        val request = ApiService.getRequest("albums",
            Response.Listener { response -> continuation.resume(response) },
            Response.ErrorListener { error -> continuation.resumeWithException(error) }
        )
        apiService.instance.add(request)

        continuation.invokeOnCancellation {
            request.cancel()
        }
    }

    suspend fun fetchArtists(): String = suspendCancellableCoroutine { continuation ->
        val request = ApiService.getRequest("musicians",
            Response.Listener { response -> continuation.resume(response) },
            Response.ErrorListener { error -> continuation.resumeWithException(error) }
        )
        apiService.instance.add(request)

        continuation.invokeOnCancellation {
            request.cancel()
        }
    }
}