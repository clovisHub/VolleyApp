package com.clovis.volleyapp.models

object Result {

    // https://jsonplaceholder.typicode.com/posts

    data class Data(val mainResponse : List<Posts>?)

    data class Posts(val userId: Long?, val id: Long?, val title: String?, val body: String?)

}