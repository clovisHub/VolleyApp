package com.clovis.volleyapp.repository

import android.content.Context
import com.clovis.volleyapp.network.SimpleRequest

object RepositoryCall{

   private lateinit var repository: Repository

    fun getComments(context: Context) {
        repository = SimpleRequest.getInstance(context)
        repository.makeCustomerRequest()
    }

}
