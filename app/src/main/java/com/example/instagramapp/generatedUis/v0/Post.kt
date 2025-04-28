package com.example.instagramapp.generatedUis.v0

data class Post(
    val id: String,
    val authorName: String,
    val authorImageUrl: String,
    val soundTrack: SoundTrack?,
    val postImages: List<String>,
    val caption: String,
    val likeCount: Int,
    val commentCount: Int,
    val likedBy: String?,
    val timestamp: String,
    val isFollowing: Boolean = false
)

data class SoundTrack(
    val artist: String,
    val title: String
)