package com.clovis.volleyapp.models

data class  Data(val posts: List<Post>?)
data class Post(val userId: Long?, val id: Long?, val title: String?, val body: String?)

