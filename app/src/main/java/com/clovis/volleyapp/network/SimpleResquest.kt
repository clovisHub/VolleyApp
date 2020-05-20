package com.clovis.volleyapp.network

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.util.Config

object SimpleResquest {

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
}