package com.example.vynils.network
import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.vynils.brokers.ApiService
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NetworkServiceAdapter (private val context: Context) {
    private val apiService = ApiService(context)

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun fetchAlbums(): String = suspendCancellableCoroutine { continuation ->
        val request = ApiService.getRequest("albums",
            { response -> continuation.resume(response) },
            { error -> continuation.resumeWithException(error) }
        )
        apiService.instance.add(request)

        continuation.invokeOnCancellation {
            request.cancel()
        }
    }

    suspend fun fetchCollectors(): String = suspendCancellableCoroutine { continuation ->
        Log.d("API", "Making API call to fetch collectors")
        val request = ApiService.getRequest("collectors",
            { response ->
                Log.d("API", "API call successful $response")
                continuation.resume(response)
            },
            { error ->
                Log.e("API", "API call failed $error", error)
                continuation.resumeWithException(error)
            }
        )

        apiService.instance.add(request)

        continuation.invokeOnCancellation {
            request.cancel()
        }
    }
    suspend fun fetchArtists(): String = suspendCancellableCoroutine { continuation ->
        val request = ApiService.getRequest("musicians",
            { response -> continuation.resume(response) },
            { error -> continuation.resumeWithException(error) }
        )
        apiService.instance.add(request)

        continuation.invokeOnCancellation {
            request.cancel()
        }
    }

    suspend fun postPrize(prize: JSONObject): String = suspendCancellableCoroutine { continuation ->
        val request = ApiService.postRequest("prizes", prize,
            { response -> continuation.resume(response.toString()) },
            { error -> continuation.resumeWithException(error) }

        )
        apiService.instance.add(request)

        continuation.invokeOnCancellation {
            request.cancel()
        }
    }

}