package com.example.vynils.network
import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vynils.DTO.CreateAlbumDTO
import com.example.vynils.DTO.ResponseAlbumDTO
import com.example.vynils.brokers.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NetworkServiceAdapter (private val context: Context) {
    private val apiService = ApiService.getInstance(context)

    suspend fun fetchAlbums(): String = suspendCancellableCoroutine { continuation ->
        val cachedAlbums = CacheManager.get("albums")
        if (cachedAlbums != null) {
            continuation.resume(cachedAlbums)
        } else {
            val request = ApiService.getRequest("albums",
                { response ->
                    CacheManager.put("albums", response)
                    continuation.resume(response)
                },
                { error -> continuation.resumeWithException(Exception(error)) }
            )
            apiService.add(request)

            continuation.invokeOnCancellation {
                request.cancel()
            }
        }
    }



    suspend fun fetchCollectors(): String = suspendCancellableCoroutine { continuation ->
        val cachedCollectors = CacheManager.get("collectors")
        if (cachedCollectors != null) {
            Log.d("API", "Fetching collectors from cache")
            continuation.resume(cachedCollectors)
        } else {
            Log.d("API", "Making API call to fetch collectors")
            val request = ApiService.getRequest("collectors",
                { response ->
                    Log.d("API", "API call successful. Saving collectors to cache")
                    CacheManager.put("collectors", response)
                    continuation.resume(response)
                },
                { error ->
                    Log.e("API", "API call failed $error", Exception(error))
                    continuation.resumeWithException(Exception(error))
                }
            )
            apiService.add(request)

            continuation.invokeOnCancellation {
                request.cancel()
            }
        }
    }

    suspend fun fetchArtists(): String = suspendCancellableCoroutine { continuation ->
        val cachedArtists = CacheManager.get("musicians")
        if (cachedArtists != null) {
            continuation.resume(cachedArtists)
        } else {
            val request = ApiService.getRequest("musicians",
                { response ->
                    CacheManager.put("musicians", response)
                    continuation.resume(response)
                },
                { error -> continuation.resumeWithException(Exception(error)) }
            )
            apiService.add(request)

            continuation.invokeOnCancellation {
                request.cancel()
            }
        }
    }

    suspend fun postPrize(prize: JSONObject): String = suspendCancellableCoroutine { continuation ->
        val request = ApiService.postRequest("prizes", prize,
            { response -> continuation.resume(response.toString()) },
            { error -> continuation.resumeWithException(Exception(error)) }
        )
        apiService.add(request)

        continuation.invokeOnCancellation {
            request.cancel()
        }
    }

    suspend fun createAlbum(albumDTO: CreateAlbumDTO): String = suspendCancellableCoroutine { continuation ->
        val jsonRequest = JSONObject(Gson().toJson(albumDTO))
        val request = ApiService.postRequest("albums", jsonRequest,
            { response ->
                continuation.resume(response.toString())
            },
            { error -> continuation.resumeWithException(Exception(error)) }
        )
        apiService.add(request)

        continuation.invokeOnCancellation {
            request.cancel()
        }
    }


}