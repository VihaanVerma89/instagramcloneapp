package com.example.instagramapp.generatedUis.cursor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.instagramapp.R

@Composable
fun WaterfrontPostScreen(post: PostCursor) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                // Dummy profile icon
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile",
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("ibengaluru", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = "More options"
            )
        }
        // Content (Image)
        Image(
            painter = rememberAsyncImagePainter(post.imageUrl),
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentScale = ContentScale.Crop
        )
        // Footer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "Like",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "Comment",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "Send",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "Bookmark",
                modifier = Modifier.size(28.dp)
            )
        }
        // Likes and description
        Text(
            text = "${post.likes} likes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Text(
            text = post.description,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
        Text(
            text = "View all ${post.comments} comments",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWaterfrontPostScreen() {
    val post = PostCursor(
        imageUrl = "https://scontent.fblr1-6.fna.fbcdn.net/v/t39.30808-6/484838862_667681102815476_8576093131149004199_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=833d8c&_nc_ohc=qt-BepJcBGIQ7kNvwGQzbap&_nc_oc=Adlk6y8qNSk4952ldAHUe0TKUMjx3rxS3sbJRS7EhmSdPGi9v3F-sexR8Uz1PRDPkpgRJ_f57lE-nlkppW8kgB02&_nc_zt=23&_nc_ht=scontent.fblr1-6.fna&_nc_gid=rJAcvrtVpLxG22lWGX2uqg&oh=00_AfGgM9DUo1WMNm1sMHR56S9hmeBFoTTW0yGPXgqZVJ2FYA&oe=6816EBF5",
        likes = 11000,
        comments = 9737,
        description = "The most Beautiful restaurants in Bengaluru. #waterfront #bengaluru #restaurant"
    )
    WaterfrontPostScreen(post)
} 