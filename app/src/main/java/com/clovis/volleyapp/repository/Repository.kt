package com.clovis.volleyapp.repository

import android.content.Context
import com.android.volley.toolbox.ImageLoader
import com.clovis.volleyapp.VolleyApp

interface Repository {

    fun <T> makeCustomerRequest(endpoint:String, obj: Class<T>)

    fun getImageLoader(context: VolleyApp): ImageLoader?

    fun createSimpleRequest(context: VolleyApp, endPoint: String)

    fun makeAstandardRequest(context: VolleyApp, endPoint: String)
}