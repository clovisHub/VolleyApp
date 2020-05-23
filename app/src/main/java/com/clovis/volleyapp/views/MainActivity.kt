package com.clovis.volleyapp.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.clovis.volleyapp.R
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.viewmodel.VolleyViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = VolleyViewModel(application as VolleyApp)
        viewModel.fetchPosts()

        supportFragmentManager.beginTransaction()
            .add(R.id.containerId, PostsListFragment.newInstance())
            .commit()
    }
}
