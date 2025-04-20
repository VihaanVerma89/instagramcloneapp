package com.example.instagramapp.generatedUis


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter

/**
 * Data class to hold all information related to a post
 */

/**
 * Data class to hold all information related to a post
 */
data class Post(
    val id: String,
    val authorName: String,
    val authorImage: String,
    val soundtrackName: String,
    val postImage: String,
    val isFollowing: Boolean,
    val likesCount: Int,
    val caption: String,
    val commentCount: Int,
    val topCommentUserName: String? = null,
    val topCommentText: String? = null,
    val timePosted: String,
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false
)

/**
 * Main composable function for rendering an Instagram post
 */
@Composable
fun InstagramPost(
    post: Post,
    onLikeClick: (String) -> Unit,
    onCommentClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onBookmarkClick: (String) -> Unit,
    onFollowClick: (String) -> Unit,
    onMoreOptionsClick: (String) -> Unit,
    onProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        PostHeader(
            authorName = post.authorName,
            authorImage = post.authorImage,
            soundtrackName = post.soundtrackName,
            isFollowing = post.isFollowing,
            onFollowClick = { onFollowClick(post.id) },
            onMoreOptionsClick = { onMoreOptionsClick(post.id) },
            onProfileClick = { onProfileClick(post.id) }
        )

        PostContent(
            postImage = post.postImage
        )

        PostActions(
            postId = post.id,
            isLiked = post.isLiked,
            isBookmarked = post.isBookmarked,
            likesCount = post.likesCount,
            caption = post.caption,
            authorName = post.authorName,
            commentCount = post.commentCount,
            topCommentUserName = post.topCommentUserName,
            topCommentText = post.topCommentText,
            timePosted = post.timePosted,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick,
            onBookmarkClick = onBookmarkClick
        )

        Divider(
            color = Color.LightGray.copy(alpha = 0.5f),
            thickness = 0.5.dp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/**
 * Composable function for rendering the post header
 */
@Composable
fun PostHeader(
    authorName: String,
    authorImage: String,
    soundtrackName: String,
    isFollowing: Boolean,
    onFollowClick: () -> Unit,
    onMoreOptionsClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Author profile image
        Image(
            painter = rememberAsyncImagePainter(authorImage),
            contentDescription = "Profile picture of $authorName",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable(onClick = onProfileClick),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Author info (name and soundtrack)
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = authorName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = soundtrackName,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Follow button
        if (!isFollowing) {
            TextButton(
                onClick = onFollowClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
            ) {
                Text(
                    text = "Follow",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // More options button
        IconButton(
            onClick = onMoreOptionsClick,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

/**
 * Composable function for rendering the post content (image)
 */
@Composable
fun PostContent(
    postImage: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = postImage,
                contentScale = ContentScale.Crop
            ),
            contentDescription = "Post image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

/**
 * Composable function for rendering post actions, likes and comments
 */
@Composable
fun PostActions(
    postId: String,
    isLiked: Boolean,
    isBookmarked: Boolean,
    likesCount: Int,
    caption: String,
    authorName: String,
    commentCount: Int,
    topCommentUserName: String?,
    topCommentText: String?,
    timePosted: String,
    onLikeClick: (String) -> Unit,
    onCommentClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onBookmarkClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        // Action buttons row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side actions (Like, Comment, Share)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onLikeClick(postId) }) {
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp)
                    )
                }

                IconButton(onClick = { onCommentClick(postId) }) {
                    Icon(
                        imageVector = Icons.Default.ChatBubbleOutline,
                        contentDescription = "Comment",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp)
                    )
                }

                IconButton(onClick = { onShareClick(postId) }) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // Right side action (Bookmark)
            IconButton(onClick = { onBookmarkClick(postId) }) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = "Save",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        // Likes count
        Text(
            text = "$likesCount likes",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        // Caption
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp)
        ) {
            Text(
                text = authorName,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = caption,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        // View all comments
        if (commentCount > 0) {
            Text(
                text = "View all $commentCount comments",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .clickable { onCommentClick(postId) }
            )
        }

        // Show top comment if available
        if (topCommentUserName != null && topCommentText != null) {
            Row(
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp)
            ) {
                Text(
                    text = topCommentUserName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = topCommentText,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Time posted
        Text(
            text = timePosted,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}

/**
 * Preview function to show a sample Instagram post
 */
@Preview
@Composable
fun PreviewInstagramPost() {
    val samplePostGPT = Post(
        id = "123",
        authorName = "instagram_user",
        authorImage = "https://scontent.fblr1-8.fna.fbcdn.net/v/t39.30808-1/466463917_880511570956102_8348740157884585754_n.jpg?stp=dst-jpg_s480x480_tt6&_nc_cat=1&ccb=1-7&_nc_sid=2d3e12&_nc_ohc=7NmvsQg9BH0Q7kNvwErE_9h&_nc_oc=Adl31ygZtiPpt8ohcpfwCclSodX9wdkeq7mYJClGZsO7w_fOL-zzWq2EHCVNOTHqpntHAKIvZbMcVx4h21Xzja_w&_nc_zt=24&_nc_ht=scontent.fblr1-8.fna&_nc_gid=9H8psDGE1RyQWoWSHQunew&oh=00_AfGqkPIsh-hLUVSybbyYPtlrR4_fw57Xa02adj3MonDPpQ&oe=680A9768",
        soundtrackName = "Original Sound - Artist Name",
        postImage = "https://scontent.fblr1-7.fna.fbcdn.net/v/t39.30808-6/489308681_986196410387617_7109754497624245468_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=127cfc&_nc_ohc=eW-w9OZvIowQ7kNvwFzqnLv&_nc_oc=AdmQbwzpOQ12ed5lvQ85QQ6xH8rkOGqAVkvQyPCRtLuiPvwIxsNqggqxcqJU1m9yWJNaGzJ-GN05HN6pKPe9Dl7D&_nc_zt=23&_nc_ht=scontent.fblr1-7.fna&_nc_gid=5Yv6xDuw9vNNY_dgq8JV5w&oh=00_AfHBLE5VenmrS_2AplcRWNGxc1pxUsj4pC6VcBxywC4tzg&oe=680ACAC1",
        isFollowing = false,
        likesCount = 1234,
        caption = "This is a sample caption for the Instagram post! #instagram #jetpackcompose",
        commentCount = 56,
        topCommentUserName = "commenter",
        topCommentText = "Great post! 🔥",
        timePosted = "2 hours ago",
        isLiked = true,
        isBookmarked = false
    )

    InstagramPost(
        post = samplePostGPT,
        onLikeClick = {},
        onCommentClick = {},
        onShareClick = {},
        onBookmarkClick = {},
        onFollowClick = {},
        onMoreOptionsClick = {},
        onProfileClick = {}
    )
}