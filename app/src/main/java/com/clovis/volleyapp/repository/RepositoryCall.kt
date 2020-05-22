package com.clovis.volleyapp.repository

import android.content.Context
import com.clovis.volleyapp.models.Post
import com.clovis.volleyapp.network.SimpleRequest

object RepositoryCall{

   private lateinit var repository: Repository

    fun getComments(context: Context) {
        repository = SimpleRequest.getInstance(context)
        repository.makeCustomerRequest("/posts", List::class.java)
    }

    fun fectchSinglePost(context: Context, id:String) {
        repository = SimpleRequest.getInstance(context)
        repository.makeCustomerRequest("/posts/$id", Post::class.java)
    }

}
