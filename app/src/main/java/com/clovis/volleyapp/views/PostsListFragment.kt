package com.clovis.volleyapp.views

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.databinding.FragmentPostsBinding
import com.clovis.volleyapp.viewmodel.VolleyViewModel
import android.widget.LinearLayout
import com.clovis.volleyapp.R

class PostsListFragment : Basefragment() {

    private lateinit var viewModel : VolleyViewModel
    private val postListAdapter :PostsAdapter by lazy { PostsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = VolleyViewModel(VolleyApp())
        }
        viewModel.fetchPosts()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = DataBindingUtil.inflate<FragmentPostsBinding>(inflater, R.layout.fragment_posts, container, false)

        val layoutManager = LinearLayoutManager(this.context)
        view.recyclerId.layoutManager = layoutManager
        view.recyclerId.adapter = postListAdapter

        viewModel.getPosts().observe(this@PostsListFragment, Observer { list->
            list?.let { posts ->
                postListAdapter.setPostList(posts)
            }
        })
        view.recyclerId.setHasFixedSize(true)

        return view.root
    }

    companion object {
        fun newInstance() = PostsListFragment()
    }
}