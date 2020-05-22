package com.clovis.volleyapp.network

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.ImageLoader
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.repository.Repository
import com.clovis.volleyapp.util.Config
import com.google.gson.Gson
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.nio.charset.Charset

class CustomRequest<T> (
    url: String,
    private val clazz: Class<T>,
    private val headerZ: MutableMap<String, String>?,
    private val listener: MyListener<T>,
    errorListener: MyListener<T>
)

    : Request<T> (Method.GET, url, errorListener), Repository {

    private val gson = Gson()

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {

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

    override fun getHeaders(): MutableMap<String, String> = headerZ?:super.getHeaders()

    override fun deliverResponse(response: T) = listener.onResponse(response)

    private val volleyApp : VolleyApp by lazy { VolleyApp() }

    override fun <T> makeCustomerRequest(endpoint:String, obj: Class<T>) {

        val request: RequestQueue = SimpleRequest.getInstance(volleyApp).requestQueue

        val urlBuilder = StringBuilder()
        urlBuilder.append(Config.getBaseUrl())
        urlBuilder.append("/posts")
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

    override fun getImageLoader(context: VolleyApp): ImageLoader? {
        return null
    }

    override fun createSimpleRequest(context: VolleyApp, endPoint: String) {}

    override fun makeAstandardRequest(context: VolleyApp, endPoint: String) {}

}