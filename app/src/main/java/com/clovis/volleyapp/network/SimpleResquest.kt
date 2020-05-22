package com.clovis.volleyapp.network

import android.content.Context
import android.database.CursorJoiner
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.util.Config
import com.android.volley.toolbox.ImageLoader
import android.graphics.Bitmap
import android.support.v4.util.LruCache
import com.clovis.volleyapp.models.Data
import com.clovis.volleyapp.models.Post
import com.clovis.volleyapp.repository.Repository

class SimpleRequest(private val context: Context): Repository {

    companion object {

        @Volatile
        private var INSTANCE: SimpleRequest? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SimpleRequest(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    override fun getImageLoader(context: VolleyApp): ImageLoader {

        val imageLoader = ImageLoader(requestQueue, object : ImageLoader.ImageCache {
            private val mCache : LruCache<String, Bitmap?> = LruCache(10)

            override fun putBitmap(url: String, bitmap: Bitmap) {
                mCache.put(url, bitmap)
            }

            override fun getBitmap(url: String): Bitmap? {
                return mCache.get(url)
            }
        })

        return imageLoader
    }


    override fun createSimpleRequest(context: VolleyApp, endPoint: String) {

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

    override fun makeAstandardRequest(context: VolleyApp, endPoint: String) {

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

        SimpleRequest.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    override fun <T> makeCustomerRequest(endpoint:String, obj : Class<T>) {
        val request: RequestQueue = SimpleRequest.getInstance(context ).requestQueue

        val urlBuilder = StringBuilder()
        urlBuilder.append(Config.getBaseUrl())
        urlBuilder.append(endpoint)
        ///posts/1/comments

        val headers: HashMap <String, String>? = HashMap()
        headers?.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")

        val customRequest = CustomRequest(urlBuilder.substring(0),
            obj,
            headers,
            MyListener(),
            MyListener())

        request.add(customRequest)
    }
    private inline fun <reified T:Any> foo() = T::class.java

    private fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }

}