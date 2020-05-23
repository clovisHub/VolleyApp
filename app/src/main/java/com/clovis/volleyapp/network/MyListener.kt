package com.clovis.volleyapp.network

import android.util.Log
import com.android.volley.Response
import com.android.volley.VolleyError
import com.clovis.volleyapp.repository.RepositoryCall

class MyListener <T> : Response.ErrorListener, Response.Listener<T>{
    override fun onResponse(response: T?) {
        Log.d("post", response.toString())
        RepositoryCall.setPosts(response)
    }

    override fun onErrorResponse(error: VolleyError?) {
         Log.d("postError", error?.message)
    }
}
