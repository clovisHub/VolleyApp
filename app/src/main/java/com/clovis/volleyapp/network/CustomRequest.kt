package com.clovis.volleyapp.network

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.nio.charset.Charset

class CustomRequest<T> ( url: String,
                         private val clazz: Class<T>,
                         private val headerZ: MutableMap<String, String>?,
                         private val listener: Response.Listener<T>,
                         errorListener: Response.ErrorListener)

    : Request<T> (Method.GET, url, errorListener) {

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        val gson = Gson()

        return try {
            val json = String(
                response?.data ?: ByteArray(0),
                Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
            Response.success(
                gson.fromJson(json, clazz),
                HttpHeaderParser.parseCacheHeaders(response))

        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}