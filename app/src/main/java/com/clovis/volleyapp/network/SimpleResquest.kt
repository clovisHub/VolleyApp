package com.clovis.volleyapp.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.util.Config

class SimpleResquest (context: Context) {

    companion object {

        @Volatile
        private var INSTANCE: SimpleResquest? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SimpleResquest(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

  fun createSimpleRequest(context: VolleyApp, endPoint: String) {
      // Instantiate the RequestQueue.
      val queue = Volley.newRequestQueue(context)
      val urlBuilder = StringBuilder()

      urlBuilder.append(Config.getBaseUrl())
      urlBuilder.append(endPoint)

      // Request a string response from the provided URL.
      val stringRequest = StringRequest(
          Request.Method.GET, urlBuilder.substring(0),
          Response.Listener<String> { response ->
              // Display the first 500 characters of the response string.
              "Response is: ${response.substring(0, 500)}"
          },
          Response.ErrorListener { })

       // Add the request to the RequestQueue.
       queue.add(stringRequest)
  }

    fun makeAstandardRequest(context: VolleyApp, endPoint: String) {

        val urlBuilder = StringBuilder()

        urlBuilder.append(Config.getBaseUrl())
        urlBuilder.append(endPoint)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, urlBuilder.substring(0), null,
            Response.Listener { response ->
                val data  = "Response: %s".format(response.toString())
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        SimpleResquest.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    private fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }

//    fun createCustomRequest(context: VolleyApp, endPoint: String) {
//        // Instantiate the cache
//        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
//
//        // Set up the network to use HttpURLConnection as the HTTP client.
//        val network = BasicNetwork(HurlStack())
//
//        // Instantiate the RequestQueue with the cache and network. Start the queue.
//        val requestQueue = RequestQueue(cache, network).apply {
//            start()
//        }
//
//        val url = "http://www.example.com"
//
//        // Formulate the request and handle the response.
//        val stringRequest = StringRequest(Request.Method.GET, url,
//            Response.Listener<String> { response ->
//                // Do something with the response
//            },
//            Response.ErrorListener { error ->
//                // Handle error
//                val yse= "ERROR: %s".format(error.toString())
//            })
//
//        // Add the request to the RequestQueue.
//        requestQueue.add(stringRequest)
//
//
//    }

}