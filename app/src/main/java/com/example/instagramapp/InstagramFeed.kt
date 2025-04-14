package com.example.instagramclone

import FeedViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.instagramapp.Post
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun InstagramFeed(viewModel: FeedViewModel) {
    val posts by viewModel.feedPosts.collectAsState()

    Column {
        // Instagram-like top bar
        TopAppBar()

        // Main feed
        LazyColumn {
            items(posts) { post ->
                PostItem(post = post)

                Divider(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    thickness = 1.dp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Instagram Clone",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Direct Messages"
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Camera"
                )
            }
        }
    )
}

@Composable
fun PostItem(post: Post) {
    var isLiked by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Post header with user info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // User avatar
            AsyncImage(
                model = post.userAvatar,
                contentDescription = "User avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // Username
            Text(
                text = post.username,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // More options icon
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More options"
                )
            }
        }

        // Post image
        AsyncImage(
            model = post.imageUrl,
            contentDescription = "Post image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )

        // Action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            IconButton(onClick = { isLiked = !isLiked }) {
                Icon(
                    if (isLiked) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (isLiked) Color.Red else LocalContentColor.current
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Comment"
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Share"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Save"
                )
            }
        }

        // Likes count
        Text(
            text = "${post.likesCount + (if (isLiked) 1 else 0)} likes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        // Caption
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(
                text = post.username,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 4.dp)
            )

            Text(text = post.caption)
        }

        // Comments preview
        if (post.comments.isNotEmpty()) {
            // View all comments text if there are many
            if (post.comments.size > 2) {
                Text(
                    text = "View all ${post.comments.size} comments",
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }

            // Display up to 2 comments
            post.comments.take(2).forEach { comment ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    Text(text = comment.text)
                }
            }
        }

        // Timestamp
        Text(
            text = formatTimestamp(post.timestamp),
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diffInMillis = now - timestamp

    return when {
        diffInMillis < 60000 -> "Just now"
        diffInMillis < 3600000 -> "${diffInMillis / 60000} minutes ago"
        diffInMillis < 86400000 -> "${diffInMillis / 3600000} hours ago"
        diffInMillis < 604800000 -> "${diffInMillis / 86400000} days ago"
        else -> {
            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            sdf.format(Date(timestamp))
        }
    }
}