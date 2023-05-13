package com.example.vynils.brokers

import android.content.Context
import android.net.Uri
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class ApiService private constructor(private val context: Context) {
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    companion object {
        private const val BASE_URL= "https://back-vynils-grupo19.herokuapp.com/"
        @Volatile
        private var INSTANCE: ApiService? = null

        fun getInstance(context: Context): ApiService =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ApiService(context).also {
                    INSTANCE = it
                }
            }

        fun getRequest(path: String, responseListener: Response.Listener<String>, errorListener: (String) -> Unit): StringRequest {
            val url = Uri.parse(BASE_URL).buildUpon().appendEncodedPath(path).build().toString()
            return StringRequest(
                Request.Method.GET,
                url,
                responseListener
            ) { error -> errorListener("GET request to $url failed with error: ${error.message}") }
        }

        fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: (String) -> Unit): JsonObjectRequest {
            val url = Uri.parse(BASE_URL).buildUpon().appendEncodedPath(path).build().toString()
            return JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
                responseListener
            ) { error -> errorListener("POST request to $url failed with error: ${error.message}") }
        }
    }

    fun add(request: Request<*>) {
        requestQueue.add(request)
    }
}
