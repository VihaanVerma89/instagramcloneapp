package com.example.instagramapp.generatedUis.windsurf

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.instagramapp.generatedUis.v0.Post
import com.example.instagramapp.generatedUis.v0.SoundTrack

@Composable
fun WaterfrontRestaurantPost(
    post: Post = sampleWaterfrontPost,
    onLikeClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onMoreClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        // Header with profile info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(post.authorImageUrl),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = post.authorName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                
                post.soundTrack?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${it.artist} • ${it.title}",
                            fontSize = 12.sp,
                            color = Color.DarkGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            
            IconButton(
                onClick = onMoreClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options"
                )
            }
        }
        
        // Main post image with overlay text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(post.postImages.first()),
                contentDescription = "Post image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            
            // Overlay text for "MOST BEAUTIFUL WATERFRONT RESTAURANTS"
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "MOST BEAUTIFUL",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "WATERFRONT RESTAURANTS",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Bengaluru",
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 32.sp,
                    letterSpacing = 1.sp
                )
            }
        }
        
        // Action buttons (like, comment, share, save)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                IconButton(onClick = onLikeClick) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like"
                    )
                }
                
                IconButton(onClick = onCommentClick) {
                    Icon(
                        imageVector = Icons.Outlined.ChatBubbleOutline,
                        contentDescription = "Comment"
                    )
                }
                
                IconButton(onClick = onShareClick) {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = "Share"
                    )
                }
            }
            
            IconButton(onClick = onSaveClick) {
                Icon(
                    imageVector = Icons.Outlined.BookmarkBorder,
                    contentDescription = "Save"
                )
            }
        }
        
        // Likes count
        Text(
            text = "${post.likeCount} likes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        // Caption
        Text(
            text = buildAnnotatedString {
                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                append(post.authorName)
                pop()
                append(" ")
                append(post.caption)
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        
        // View all comments
        if (post.commentCount > 0) {
            Text(
                text = "View all ${post.commentCount} comments",
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
            )
        }
        
        // Timestamp
        Text(
            text = post.timestamp,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}

// Sample data for preview
val sampleWaterfrontPost = Post(
    id = "1",
    authorName = "ibengaluru",
    authorImageUrl = "https://picsum.photos/id/237/200",
    soundTrack = SoundTrack(
        artist = "Ed Sheeran",
        title = "Azizam"
    ),
    postImages = listOf("https://images.unsplash.com/photo-1580674684081-7617fbf3d745"),
    caption = "The most Beautiful restaurants in Bengaluru with waterfront views.",
    likeCount = 11000,
    commentCount = 31,
    likedBy = "travel_enthusiast",
    timestamp = "13 hours ago",
    isFollowing = true
)

@Preview(showBackground = true)
@Composable
fun WaterfrontRestaurantPostPreview() {
    MaterialTheme {
        Surface {
            WaterfrontRestaurantPost()
        }
    }
}