package com.example.instagramapp.mvi.feed

import com.example.instagramapp.Post
import com.example.instagramapp.mvi.UiEffect
import com.example.instagramapp.mvi.UiIntent
import com.example.instagramapp.mvi.UiState

/**
 * MVI components for the Feed feature
 */

// Feed State
data class FeedState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
) : UiState

// Feed Intents
sealed class FeedIntent : UiIntent {
    // Load initial feed
    object LoadFeed : FeedIntent()
    
    // Refresh feed (pull-to-refresh)
    object RefreshFeed : FeedIntent()
    
    // Toggle like on a post
    data class ToggleLike(val postId: Long) : FeedIntent()
    
    // Toggle save on a post
    data class ToggleSave(val postId: Long) : FeedIntent()
    
    // Share a post
    data class SharePost(val postId: Long) : FeedIntent()
    
    // Add a comment to a post
    data class AddComment(val postId: Long, val comment: String) : FeedIntent()
    
    // Double tap to like
    data class DoubleTapLike(val postId: Long) : FeedIntent()
}

// Feed Effects (one-time events)
sealed class FeedEffect : UiEffect {
    // Show a toast message
    data class ShowToast(val message: String) : FeedEffect()
    
    // Navigate to a specific screen
    data class Navigate(val route: String) : FeedEffect()
    
    // Show share dialog
    data class ShowShareDialog(val postId: Long) : FeedEffect()
}
