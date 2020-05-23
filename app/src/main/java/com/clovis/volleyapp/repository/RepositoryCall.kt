package com.clovis.volleyapp.repository

import android.content.Context
import android.util.JsonReader
import com.clovis.volleyapp.models.Post
import com.clovis.volleyapp.network.SimpleRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.StringReader



object RepositoryCall{

    private lateinit var repository: Repository
    private var list: ArrayList<Post> = arrayListOf()
    private val gson: Gson by lazy { Gson() }

    fun fetchComments(context: Context) {
        repository = SimpleRequest.getInstance(context)
        repository.makeCustomerRequest("/posts", Array<Post>::class.java)
    }

    fun fectchSinglePost(context: Context, id:String) {
        repository = SimpleRequest.getInstance(context)
        repository.makeCustomerRequest("/posts/$id", Post::class.java)
    }

    fun getPosts() : List<Post> {
        return list
    }

    fun <T> setPosts(lis: T) {

       val typeToken = object : TypeToken<ArrayList<Post>>() {}.type
        val reader = JsonReader(StringReader((lis).toString()))
        reader.isLenient = true
        val result = lis as Any

        when((list)::class.java.simpleName) {

            ArrayList::class.java.simpleName  -> {
                val json = gson.toJson(lis)
                list.addAll(gson.fromJson(json, Array<Post>::class.java))
            }
            Post::class.java.simpleName  -> {
                 val post = gson.fromJson(lis.toString(), Post::class.java)
                 list.add(post)
            }
        }
    }
}
