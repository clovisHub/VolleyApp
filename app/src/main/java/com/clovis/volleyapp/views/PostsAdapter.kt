package com.clovis.volleyapp.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.clovis.volleyapp.databinding.PostItemBinding
import com.clovis.volleyapp.models.Post


class PostsAdapter() : RecyclerView.Adapter<PostsAdapter.PostHolder>() {

    private var postsList:List<Post> = mutableListOf()

    fun setPostList(posts: List<Post>) {
        postsList = posts
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PostHolder {
        val view  = PostItemBinding
            .inflate(LayoutInflater.from(parent.context))

        return PostHolder(view)
    }

    override fun onBindViewHolder(postHolder: PostHolder, position: Int) {
           postHolder.view.post = postsList[position]
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    class PostHolder(val view: PostItemBinding) : RecyclerView.ViewHolder(view.root)
}