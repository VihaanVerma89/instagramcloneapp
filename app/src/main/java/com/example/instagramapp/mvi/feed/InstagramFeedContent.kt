package com.example.instagramapp.mvi.feed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.instagramapp.EnhancedPostItem
import com.example.instagramapp.Post
import com.example.instagramapp.SponsoredPost
import com.example.instagramapp.StoriesRow
import com.example.instagramapp.SuggestedUserItem

@Composable
fun InstagramFeedContent(
    posts: List<Post>,
    onLikeToggle: (Long) -> Unit,
    onSaveToggle: (Long) -> Unit,
    onShareClick: (Long) -> Unit,
    onDoubleTapLike: (Long) -> Unit,
    onCommentAdd: (Long, String) -> Unit
) {
    // Keep track of post states locally to make UI feel responsive
    val likedPosts = remember { mutableStateMapOf<Long, Boolean>() }
    val savedPosts = remember { mutableStateMapOf<Long, Boolean>() }
    
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Add Stories at the top
        item {
            StoriesRow()
        }
        
        // Posts content
        items(posts) { post ->
            if ((post.id % 5).toInt() == 0) {
                SponsoredPost()
            } else if ((post.id % 7).toInt() == 0) {
                SuggestedUserItem()
            } else {
                MVIPostItem(
                    post = post,
                    isLiked = likedPosts[post.id] ?: false,
                    isSaved = savedPosts[post.id] ?: false,
                    onLikeToggle = { 
                        likedPosts[post.id] = !(likedPosts[post.id] ?: false)
                        onLikeToggle(post.id) 
                    },
                    onSaveToggle = { 
                        savedPosts[post.id] = !(savedPosts[post.id] ?: false)
                        onSaveToggle(post.id) 
                    },
                    onShareClick = { onShareClick(post.id) },
                    onDoubleTapLike = {
                        likedPosts[post.id] = true
                        onDoubleTapLike(post.id)
                    },
                    onCommentAdd = { comment -> onCommentAdd(post.id, comment) }
                )
            }
            
            Divider(
                color = Color.LightGray.copy(alpha = 0.3f),
                thickness = 1.dp
            )
        }
    }
}

@Composable
fun MVIPostItem(
    post: Post,
    isLiked: Boolean,
    isSaved: Boolean,
    onLikeToggle: () -> Unit,
    onSaveToggle: () -> Unit,
    onShareClick: () -> Unit,
    onDoubleTapLike: () -> Unit,
    onCommentAdd: (String) -> Unit
) {
    // Note: we're reusing the EnhancedPostItem from the original code, 
    // but we've added MVI-specific parameters
    
    // Create a custom wrapper around the existing EnhancedPostItem
    // In a real implementation, we would refactor EnhancedPostItem to work with MVI
    // This is a temporary solution to demonstrate the MVI pattern
    EnhancedPostItemWrapper(
        post = post,
        isLiked = isLiked,
        isSaved = isSaved,
        onLikeToggle = onLikeToggle,
        onSaveToggle = onSaveToggle,
        onShareClick = onShareClick,
        onDoubleTapLike = onDoubleTapLike
    )
}

// Temporary wrapper to connect existing UI to MVI
@Composable
private fun EnhancedPostItemWrapper(
    post: Post,
    isLiked: Boolean,
    isSaved: Boolean,
    onLikeToggle: () -> Unit,
    onSaveToggle: () -> Unit,
    onShareClick: () -> Unit,
    onDoubleTapLike: () -> Unit
) {
    // No need to adjust the post, as we're handling the like count in the UI
    
    // Use the existing EnhancedPostItem but set our callbacks
    EnhancedPostItem(
        post = adjustedPost,
        onLikeClick = onLikeToggle,
        onSaveClick = onSaveToggle,
        onShareClick = onShareClick,
        onDoubleTapLike = onDoubleTapLike,
        isLiked = isLiked,
        isSaved = isSaved
    )
}
