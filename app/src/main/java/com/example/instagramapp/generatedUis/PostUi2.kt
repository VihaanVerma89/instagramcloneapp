package com.example.instagramapp.generatedUis


import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ModeComment
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter

/*

Prompt:

Context:
This is an android instagram app  post UI.
We need to generate the android compose UI functions for rendering it in my app.

Task:
- Use post data class to store post related information like author image, author name, post sound track, follow option, post image, post actions ( like, comments, send, bookmark)
- pass this post data class into compose functions
- Break the post compose function into post header, post content and post actions functions
- Header compose function gives information renders author image, author name, post soundtrack name and show follow button along with three dots menu
- content compose function should be responsible for rendering the image from server.
- post actions compose function should show options for like , comment , send and bookmark options in a row. Below this row include liked by and comments information
-

 */
// 1. Data Class
data class PostGPT(
    val authorImageUrl: String,
    val authorName: String,
    val soundtrackName: String,
    val postImageUrl: String,
    val likedByText: String,
    val commentsText: String
)

// 2. Main Post Item
@Composable
fun PostItem(postGPT: PostGPT) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PostHeader(
            authorImageUrl = postGPT.authorImageUrl,
            authorName = postGPT.authorName,
            soundtrackName = postGPT.soundtrackName
        )
        PostContent(postImageUrl = postGPT.postImageUrl)
        PostActions(
            likedByText = postGPT.likedByText,
            commentsText = postGPT.commentsText
        )
    }
}

// 3. Post Header
@Composable
fun PostHeader(authorImageUrl: String, authorName: String, soundtrackName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(authorImageUrl),
            contentDescription = "Author Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = authorName, fontWeight = FontWeight.Bold)
            Text(text = soundtrackName, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
        TextButton(onClick = { /* Follow clicked */ }) {
            Text(text = "Follow")
        }
        IconButton(onClick = { /* More menu clicked */ }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
        }
    }
}

// 4. Post Content
@Composable
fun PostContent(postImageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(postImageUrl),
        contentDescription = "Post Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentScale = ContentScale.Crop
    )
}

// 5. Post Actions
@Composable
fun PostActions(likedByText: String, commentsText: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Like clicked */ }) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Like")
            }
            IconButton(onClick = { /* Comment clicked */ }) {
                Icon(imageVector = Icons.Default.ModeComment, contentDescription = "Comment")
            }
            IconButton(onClick = { /* Send clicked */ }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Bookmark clicked */ }) {
                Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = "Bookmark")
            }
        }
        Text(
            text = likedByText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Text(
            text = commentsText,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

// Example Usage
@Preview
@Composable
fun ExamplePostScreen() {
    val samplePostGPT = PostGPT(
        authorImageUrl = "https://scontent.fblr1-8.fna.fbcdn.net/v/t39.30808-1/466463917_880511570956102_8348740157884585754_n.jpg?stp=dst-jpg_s480x480_tt6&_nc_cat=1&ccb=1-7&_nc_sid=2d3e12&_nc_ohc=7NmvsQg9BH0Q7kNvwErE_9h&_nc_oc=Adl31ygZtiPpt8ohcpfwCclSodX9wdkeq7mYJClGZsO7w_fOL-zzWq2EHCVNOTHqpntHAKIvZbMcVx4h21Xzja_w&_nc_zt=24&_nc_ht=scontent.fblr1-8.fna&_nc_gid=9H8psDGE1RyQWoWSHQunew&oh=00_AfGqkPIsh-hLUVSybbyYPtlrR4_fw57Xa02adj3MonDPpQ&oe=680A9768",
        authorName = "pixel_muse_",
        soundtrackName = "Chad Kroeger - Hero",
        postImageUrl = "https://scontent.fblr1-7.fna.fbcdn.net/v/t39.30808-6/489308681_986196410387617_7109754497624245468_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=127cfc&_nc_ohc=eW-w9OZvIowQ7kNvwFzqnLv&_nc_oc=AdmQbwzpOQ12ed5lvQ85QQ6xH8rkOGqAVkvQyPCRtLuiPvwIxsNqggqxcqJU1m9yWJNaGzJ-GN05HN6pKPe9Dl7D&_nc_zt=23&_nc_ht=scontent.fblr1-7.fna&_nc_gid=5Yv6xDuw9vNNY_dgq8JV5w&oh=00_AfHBLE5VenmrS_2AplcRWNGxc1pxUsj4pC6VcBxywC4tzg&oe=680ACAC1",
        likedByText = "Liked by a__sis__h and others",
        commentsText = "30 comments"
    )
    PostItem(postGPT = samplePostGPT)
}
