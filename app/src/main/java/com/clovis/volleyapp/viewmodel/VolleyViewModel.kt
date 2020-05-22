package com.clovis.volleyapp.viewmodel

import android.arch.lifecycle.AndroidViewModel
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.repository.RepositoryCall

class VolleyViewModel(private val app: VolleyApp): AndroidViewModel(app) {

    fun fetchPosts() {
        //RepositoryCall.getComments(app)
        RepositoryCall.fectchSinglePost(app, "1")
    }

}