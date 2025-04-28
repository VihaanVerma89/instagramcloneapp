package com.example.instagramapp.generatedUis.v0

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InstagramPost(
    post: Post,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onFollowClick: () -> Unit,
    onMoreOptionsClick: () -> Unit,
    onProfileClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PostHeader(
            authorName = post.authorName,
            authorImageUrl = post.authorImageUrl,
            soundTrack = post.soundTrack,
            isFollowing = post.isFollowing,
            onFollowClick = onFollowClick,
            onMoreOptionsClick = onMoreOptionsClick,
            onProfileClick = { onProfileClick(post.authorName) }
        )
        
        PostContent(
            images = post.postImages,
            currentImageIndex = 0 // This would be managed with state in a real app
        )
        
        PostActions(
            likeCount = post.likeCount,
            caption = post.caption,
            authorName = post.authorName,
            likedBy = post.likedBy,
            timestamp = post.timestamp,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick,
            onBookmarkClick = onBookmarkClick,
            onProfileClick = { onProfileClick(post.authorName) }
        )
        
        Divider(thickness = 0.5.dp)
    }
}