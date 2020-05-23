package com.clovis.volleyapp.views

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.clovis.volleyapp.R
import com.clovis.volleyapp.databinding.PostItemBinding
import com.clovis.volleyapp.models.Post


class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostHolder>() {

    private var postsList:List<Post> = mutableListOf()

    fun setPostList(posts: List<Post>) {
        postsList = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PostHolder {
        val view  = DataBindingUtil
            .inflate<PostItemBinding>(LayoutInflater.from(parent.context),
                R.layout.post_item, parent, false)

        return PostHolder(view)
    }

    override fun onBindViewHolder(postHolder: PostHolder, position: Int) {
        val post = postsList[position]
           postHolder.view.post = post
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    class PostHolder(val view: PostItemBinding) : RecyclerView.ViewHolder(view.root)
}