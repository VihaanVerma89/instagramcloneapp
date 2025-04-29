package com.example.instagramapp.mvi.feed

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.instagramapp.BottomNavigationBar
import com.example.instagramapp.MiniMusicPlayer
import com.example.instagramapp.ShareDialog
import com.example.instagramapp.TopAppBar
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(viewModel: FeedViewModel) {
    // Collect the UI state
    val uiState by viewModel.observeState().collectAsState()
    
    // Local UI state for dialogs
    var showShareDialog by remember { mutableStateOf(false) }
    var selectedPostId by remember { mutableStateOf<Long?>(null) }
    
    // Context for showing toast messages
    val context = LocalContext.current
    
    // Handle one-time effects
    LaunchedEffect(Unit) {
        viewModel.observeEffect().collectLatest { effect ->
            when (effect) {
                is FeedEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is FeedEffect.Navigate -> {
                    // Navigation would be handled here if we had a NavController
                }
                is FeedEffect.ShowShareDialog -> {
                    selectedPostId = effect.postId
                    showShareDialog = true
                }
            }
        }
    }
    
    // Set up pull-to-refresh
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = { viewModel.processIntent(FeedIntent.RefreshFeed) }
    )
    
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pullRefresh(pullRefreshState)
        ) {
            // Show loading indicator when first loading
            if (uiState.isLoading && uiState.posts.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                // Show the feed
                InstagramFeedContent(
                    posts = uiState.posts,
                    onLikeToggle = { postId -> 
                        viewModel.processIntent(FeedIntent.ToggleLike(postId)) 
                    },
                    onSaveToggle = { postId -> 
                        viewModel.processIntent(FeedIntent.ToggleSave(postId)) 
                    },
                    onShareClick = { postId -> 
                        viewModel.processIntent(FeedIntent.SharePost(postId)) 
                    },
                    onDoubleTapLike = { postId -> 
                        viewModel.processIntent(FeedIntent.DoubleTapLike(postId)) 
                    },
                    onCommentAdd = { postId, comment -> 
                        viewModel.processIntent(FeedIntent.AddComment(postId, comment)) 
                    }
                )
            }
            
            // Pull-to-refresh indicator
            PullRefreshIndicator(
                refreshing = uiState.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        
        // Floating music player at the bottom (above bottom nav)
        MiniMusicPlayer()
    }
    
    // Show share dialog when needed
    if (showShareDialog && selectedPostId != null) {
        ShareDialog(
            onDismiss = { 
                showShareDialog = false
                selectedPostId = null
            },
            onShareToStory = {
                // Handle share to story
                Toast.makeText(context, "Shared to story", Toast.LENGTH_SHORT).show()
                showShareDialog = false
                selectedPostId = null
            },
            onShareToDirectMessage = {
                // Handle share to DM
                Toast.makeText(context, "Opening DM", Toast.LENGTH_SHORT).show()
                showShareDialog = false
                selectedPostId = null
            }
        )
    }
}
