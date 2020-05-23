package com.clovis.volleyapp.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.models.Post
import com.clovis.volleyapp.repository.RepositoryCall

class VolleyViewModel(private val app: VolleyApp): AndroidViewModel(app) {

    private var mutablePosts : MutableLiveData<List<Post>>  = MutableLiveData()

    fun fetchPosts() {
        RepositoryCall.fetchComments(app)
    }

    fun fetchPost(id:String) {
        RepositoryCall.fectchSinglePost(app, id)
    }

    fun getPosts(): LiveData<List<Post>> {
        mutablePosts.postValue(RepositoryCall.getPosts())

        return mutablePosts
    }
}