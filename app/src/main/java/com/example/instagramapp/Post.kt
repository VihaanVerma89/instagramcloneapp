package com.example.instagramapp

data class Post(
    val id: Long,
    val username: String,
    val userAvatar: String,
    val imageUrl: String,
    val caption: String,
    val likesCount: Int,
    val timestamp: Long,
    val comments: List<Comment> = emptyList()
)


data class Comment(
    val id: Long,
    val username: String,
    val text: String,
    val timestamp: Long
)