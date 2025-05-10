package com.example.instagramapp.generatedUis

// import androidx.compose.animation.core.tween - Removed unused import
// import androidx.compose.foundation.lazy.items - Removed unused import
// Removed viewModel import as it's causing issues
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Main composable function for the Quick TV App
 */
@Composable
fun QuickTVApp() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { VideoCarousel() }
            item { StoriesRow() }
            item { TopPicksSection() }
            item { NewReleasesSection() }
        }
    }
}

/**
 * Video carousel at the top of the app
 */
@Composable
fun VideoCarousel() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // Main video player view
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight()
                    .width(480.dp)
                    .background(Color.Black)
            ) {
                // Video content would be here
                // Using a placeholder for demonstration
                Image(
                    painter = rememberAsyncImagePainter("https://picsum.photos/id/237/400/300"),
                    contentDescription = "Video content",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                
                // Mute button
                IconButton(
                    onClick = { /* Handle mute toggle */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeOff,
                        contentDescription = "Mute",
                        tint = Color.White
                    )
                }
            }
            
            // Carousel indicator dots
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(width = 24.dp, height = 4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(if (index == 0) Color(0xFF3D7BF4) else Color.Gray.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}

/**
 * Section for "Top Picks For You"
 */
@Composable
fun TopPicksSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Section header
        Text(
            text = "Top Picks For You",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        
        // Horizontal scrollable content
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(4) { index ->
                ContentThumbnail(
                    imageUrl = "https://picsum.photos/id/${100 + index}/300/450",
                    onClick = { /* Handle click */ }
                )
            }
        }
    }
}

/**
 * Section for "New Releases"
 */
@Composable
fun NewReleasesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Section header
        Text(
            text = "New Releases",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        
        // Horizontal scrollable content
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(4) { index ->
                ContentThumbnail(
                    imageUrl = "https://picsum.photos/id/${200 + index}/300/450",
                    onClick = { /* Handle click */ }
                )
            }
        }
    }
}

/**
 * Content thumbnail item used in horizontal lists
 */
@Composable
fun ContentThumbnail(
    imageUrl: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(240.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray.copy(alpha = 0.2f))
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "Content thumbnail",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        
        // Play icon overlay
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

/**
 * Bottom navigation bar with Home, Wallet, and Profile
 */
@Composable
fun BottomNavigationBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = true,
                onClick = { /* Handle Home click */ }
            )
            
            BottomNavItem(
                icon = Icons.Default.AccountBalanceWallet,
                label = "Wallet",
                isSelected = false,
                onClick = { /* Handle Wallet click */ }
            )
            
            BottomNavItem(
                icon = Icons.Default.Person,
                label = "Profile",
                isSelected = false,
                onClick = { /* Handle Profile click */ }
            )
        }
    }
}

/**
 * Bottom navigation item
 */
@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) Color(0xFF3D7BF4) else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = label,
            color = if (isSelected) Color(0xFF3D7BF4) else Color.Gray,
            fontSize = 12.sp
        )
    }
}

/**
 * Stories row feature
 */
@Composable
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
                                brush = if (hasUnseenStory) 
                                    Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFFFD80D), 
                                            Color(0xFFFF5107), 
                                            Color(0xFFFB37A5)
                                        )
                                    ) 
                                else 
                                    Brush.linearGradient(
                                        colors = listOf(
                                            Color.LightGray, 
                                            Color.LightGray
                                        )
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

/**
 * Double-tap to like functionality with animation
 */
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

/**
 * Data class for content items
 */
data class ContentItem(
    val id: String,
    val title: String,
    val imageUrl: String,
    val type: String
)

/**
 * ViewModel for the Quick TV app feed
 */
class QuickTVFeedViewModel : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing
    
    private val _feedContent = MutableStateFlow<List<ContentItem>>(emptyList())
    val feedContent: StateFlow<List<ContentItem>> = _feedContent
    
    init {
        loadInitialContent()
    }
    
    private fun loadInitialContent() {
        viewModelScope.launch {
            // In a real app, fetch from API
            _feedContent.value = generateSampleContent()
        }
    }
    
    fun refreshFeed() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(1500) // Simulate network request
            _feedContent.value = generateSampleContent() // Refresh with new content
            _isRefreshing.value = false
        }
    }
    
    private fun generateSampleContent(): List<ContentItem> {
        // Sample data generation
        return List(10) { index ->
            ContentItem(
                id = "item_$index",
                title = "Content Title $index",
                imageUrl = "https://picsum.photos/id/${100 + index}/300/450",
                type = if (index % 2 == 0) "video" else "image"
            )
        }
    }
}

/**
 * Pull-to-refresh feed implementation
 * Note: This implementation is commented out because it requires additional setup
 * for the ViewModels. It's kept here as a reference for future implementation.
 */
/*
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshableFeed(viewModel: QuickTVFeedViewModel) {
    val refreshState = rememberPullRefreshState(
        refreshing = viewModel.isRefreshing.collectAsState().value,
        onRefresh = { viewModel.refreshFeed() }
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {
        // Main content
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item { VideoCarousel() }
            item { StoriesRow() }
            item { TopPicksSection() }
            item { NewReleasesSection() }
        }
        
        PullRefreshIndicator(
            refreshing = viewModel.isRefreshing.collectAsState().value,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
*/
