package com.clovis.volleyapp.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.clovis.volleyapp.R
import com.clovis.volleyapp.VolleyApp
import com.clovis.volleyapp.viewmodel.VolleyViewmodel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = VolleyViewmodel(application as VolleyApp)
        viewModel.fetchPosts()
    }
}
