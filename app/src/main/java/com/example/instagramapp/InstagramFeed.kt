package com.example.instagramclone

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.instagramapp.FeedViewModel
import com.example.instagramapp.Post
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



@Composable
fun InstagramFeed(viewModel: FeedViewModel) {
    val posts by viewModel.feedPosts.collectAsState()

    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNavigationBar() },
        containerColor = Color.White
    ) { paddingValues ->
        // Main feed
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
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
//                    PostItem(post = post)
                    EnhancedPostItem(post)
                }

                Divider(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    thickness = 1.dp
                )
            }

            // Add music player at the bottom (optional)
            item {
                MiniMusicPlayer()
            }
        }
    }
}@Composable
fun StoriesRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        // Your story item
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter("https://picsum.photos/id/237/150/150"),
                        contentDescription = "Your story",
                        modifier = Modifier
                            .size(70.dp)
                            .border(2.dp, Color(0xFFEEEEEE), CircleShape)
                            .padding(3.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.BottomEnd)
                            .background(Color.White, CircleShape)
                            .border(1.5.dp, Color.White, CircleShape)
                            .padding(2.dp)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add story",
                            tint = Color.Blue,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Your story",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // User stories
        items(10) { index ->
            val hasUnseenStory = index % 3 != 0
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .background(
                                brush = if (hasUnseenStory) Brush.linearGradient(
                                    colors = listOf(Color(0xFFFFD80D), Color(0xFFFF5107), Color(0xFFFB37A5))
                                ) else Brush.linearGradient(
                                    colors = listOf(Color.LightGray, Color.LightGray)
                                )
                            )
                            .padding(3.dp)
                    )

                    Image(
                        painter = rememberAsyncImagePainter("https://picsum.photos/id/${101 + index}/150/150"),
                        contentDescription = "Story",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "user_${index + 1}",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(70.dp)
                )
            }
        }
    }
}


@Composable
fun BottomNavigationBar() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = "Home",
                modifier = Modifier.size(28.dp)
            )
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(28.dp)
            )
            Icon(
                Icons.Default.Add,
                contentDescription = "Create",
                modifier = Modifier
                    .size(28.dp)
                    .padding(2.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                    .padding(2.dp)
            )
            Icon(
                Icons.Default.Movie, // Changed from Build to Movie
                contentDescription = "Reels",
                modifier = Modifier.size(28.dp)
            )
            // Profile picture
            Image(
                painter = rememberAsyncImagePainter("https://picsum.photos/id/237/150/150"),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun SponsoredPost() {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Post header with sponsor info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sponsor avatar
            Image(
                painter = rememberAsyncImagePainter("https://picsum.photos/id/0/150/150"),
                contentDescription = "Sponsor avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                // Sponsor name
                Text(
                    text = "Building AI Products and Services",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Sponsored",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // More options icon
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More options"
                )
            }
        }

        // Sponsored content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.8f)
                .background(Color(0xFF8B1E3F))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // MIT xPRO logo-like element
                Text(
                    text = "MIT xPRO",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "BY 2030",
                    color = Color(0xFFEBA83A),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "AI WILL ADD $15.7",
                    color = Color(0xFFEBA83A),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "TRILLION TO THE",
                    color = Color(0xFFEBA83A),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "GLOBAL ECONOMY*",
                    color = Color(0xFFEBA83A),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Building AI Products",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "and Services",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Program duration badge
                Surface(
                    color = Color(0xFFEBA83A),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        text = "12 Weeks | Online",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "*Source: PwC",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 10.sp
                )
            }
        }

        // CTA button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Apply now",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Apply now",
                modifier = Modifier.size(20.dp)
            )
        }

        // Post interactions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Likes",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "1,776",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Outlined.ChatBubble, // Changed from Build
                    contentDescription = "Comments",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "48",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                Icons.Default.Send,
                contentDescription = "Share",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Default.BookmarkBorder, // Changed from Build
                contentDescription = "Save",
                modifier = Modifier.size(24.dp)
            )
        }

        // Ad description
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "75% Indian employers looking for new hires with AI skills, take charge of your career with MIT... more",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SuggestedUserItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar with colored border for suggested user
        Box {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFE040FB), Color(0xFFFF5722), Color(0xFFFFEB3B))
                        )
                    )
            )

            Image(
                painter = rememberAsyncImagePainter("https://picsum.photos/id/1025/150/150"),
                contentDescription = "Suggested user avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = "agasthya_anand",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Text(
                text = "Suggested for you",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        OutlinedButton(
            onClick = { },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.3f)),
            modifier = Modifier.height(32.dp)
        ) {
            Text(
                text = "Follow",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }

        IconButton(
            onClick = { },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Instagram",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Cursive
                )
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Notifications"
                )
            }
            IconButton(onClick = { }) {
                BadgedBox(
                    badge = {
                        Badge {
                            Text("6")
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Send,
                        contentDescription = "Direct Messages"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        )
    )
}

@Composable
fun PostItem(post: Post) {
    var isLiked by remember { mutableStateOf(false) }
    var isSaved by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Post header with user info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // User avatar
            Image(
                painter = rememberAsyncImagePainter(post.userAvatar),
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

        // Post image with double tap to like
        // For real double tap implementation, you would add a GestureDetector
        // with onDoubleTap, but for simplicity we'll use Image click for now
        Image(
            painter = rememberAsyncImagePainter(post.imageUrl),
            contentDescription = "Post image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable {
                    // Simulate double-tap like
                    isLiked = true
                },
            contentScale = ContentScale.Crop
        )

        // Action buttons - updated to match Instagram's style
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp)
        ) {
            IconButton(
                onClick = { isLiked = !isLiked },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (isLiked) Color.Red else Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = { },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    Icons.Default.Build,
                    contentDescription = "Comment",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = { },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Share",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { isSaved = !isSaved },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    if (isSaved) Icons.Default.Build else Icons.Default.Person,
                    contentDescription = "Save",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Likes count - updated style
        Text(
            text = "${post.likesCount + (if (isLiked) 1 else 0)} likes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // Caption - updated style
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp)
        ) {
            Text(
                text = post.username,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 4.dp)
            )

            Text(text = post.caption)
        }

        // Comments preview - updated style
        if (post.comments.isNotEmpty()) {
            // View all comments text if there are many
            if (post.comments.size > 2) {
                Text(
                    text = "View all ${post.comments.size} comments",
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                )
            }

            // Display up to 2 comments
            post.comments.take(2).forEach { comment ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp)
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

        // Timestamp - updated style
        Text(
            text = formatTimestamp(post.timestamp),
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // Add some space between posts
        Spacer(modifier = Modifier.height(4.dp))
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


@Composable
fun PostImage(
    imageUrl: String,
    onDoubleTap: () -> Unit
) {
    var showHeart by remember { mutableStateOf(false) }

    Box {
        // The post image
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "Post image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            onDoubleTap()
                            showHeart = true
                        }
                    )
                },
            contentScale = ContentScale.Crop
        )

        // Heart animation overlay
        AnimatedVisibility(
            visible = showHeart,
            enter = scaleIn(initialScale = 0f) + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
            )

            // Hide heart after animation
            LaunchedEffect(showHeart) {
                if (showHeart) {
                    delay(1000)
                    showHeart = false
                }
            }
        }
    }
}



// 3. Add pull-to-refresh functionality
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshableFeed(viewModel: FeedViewModel) {

    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val refreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.refreshFeed() }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {
        // Your existing feed content
        InstagramFeed(viewModel)

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

// Add this property and function to FeedViewModel

// 4. Add post sharing functionality
@Composable
fun ShareDialog(
    onDismiss: () -> Unit,
    onShareToStory: () -> Unit,
    onShareToDirectMessage: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Share to") },
        text = {
            Column {
                ShareOption(
                    icon = Icons.Default.Person,
                    text = "Add to your story",
                    onClick = onShareToStory
                )
                Divider()
                ShareOption(
                    icon = Icons.Default.Send,
                    text = "Send to...",
                    onClick = onShareToDirectMessage
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ShareOption(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        )
    }
}

// 5. Add a floating music player like Instagram Reels
@Composable
fun MiniMusicPlayer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.8f))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://picsum.photos/id/65/300/300"),
            contentDescription = "Album art",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Song Name",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Artist Name",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            Icons.Default.Pause, // Changed from Build
            contentDescription = "Pause",
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
        )

        Icon(
            Icons.Default.Close, // Changed from Build
            contentDescription = "Close",
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
        )
    }
}

@Composable
fun EnhancedPostItem(post: Post) {
    var isLiked by remember { mutableStateOf(false) }
    var isSaved by remember { mutableStateOf(false) }
    var showShareDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Post header with user info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // User avatar
            Image(
                painter = rememberAsyncImagePainter(post.userAvatar),
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

        // Use PostImage for double-tap like
        PostImage(
            imageUrl = post.imageUrl,
            onDoubleTap = { isLiked = true }
        )

        // Action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp)
        ) {
            IconButton(
                onClick = { isLiked = !isLiked },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (isLiked) Color.Red else Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = { },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    Icons.Outlined.ChatBubble, // Using the Outlined version from the extended icons
                    contentDescription = "Comment",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = { showShareDialog = true },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Share",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { isSaved = !isSaved },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = "Save",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Likes count
        Text(
            text = "${post.likesCount + (if (isLiked) 1 else 0)} likes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // Caption
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp)
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
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                )
            }

            // Display up to 2 comments
            post.comments.take(2).forEach { comment ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp)
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
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // Add some space between posts
        Spacer(modifier = Modifier.height(4.dp))
    }

    // Show share dialog if needed
    if (showShareDialog) {
        ShareDialog(
            onDismiss = { showShareDialog = false },
            onShareToStory = {
                // Handle share to story
                showShareDialog = false
            },
            onShareToDirectMessage = {
                // Handle share to DM
                showShareDialog = false
            }
        )
    }
}