package com.example.vynils.network
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vynils.brokers.ApiService
import com.example.vynils.brokers.ApiService.Companion.BASE_URL
import com.example.vynils.model.Album
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Comment
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NetworkServiceAdapter (private val context: Context) {
    private val apiService = ApiService(context)

    //Testing This requestQueue
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

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

    //TESTING THIS FUNCTION
 //   fun getAlbums(albumId:Int, onComplete:(resp:List<Album>)->Unit, onError: (error: VolleyError)->Unit) {

    //TESTING THIS FUNCTION
    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }

}