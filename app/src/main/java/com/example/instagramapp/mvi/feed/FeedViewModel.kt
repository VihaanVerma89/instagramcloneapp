package com.example.instagramapp.mvi.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramapp.Comment
import com.example.instagramapp.Post
import com.example.instagramapp.mvi.MVIViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong

class FeedViewModel : ViewModel(), MVIViewModel<FeedState, FeedIntent, FeedEffect> {
    
    // Mutable state that stores the current UI state
    private val _state = MutableStateFlow(FeedState())
    override val state: FeedState get() = _state.value
    
    // Mutable shared flow for one-time effects
    private val _effect = MutableSharedFlow<FeedEffect>()
    
    // ID counter for new comments
    private val commentIdCounter = AtomicLong(1000)
    
    // Map to track liked posts
    private val likedPosts = mutableMapOf<Long, Boolean>()
    
    // Map to track saved posts
    private val savedPosts = mutableMapOf<Long, Boolean>()
    
    init {
        // Load initial feed when ViewModel is created
        processIntent(FeedIntent.LoadFeed)
    }
    
    override fun processIntent(intent: FeedIntent) {
        when (intent) {
            is FeedIntent.LoadFeed -> loadFeed()
            is FeedIntent.RefreshFeed -> refreshFeed()
            is FeedIntent.ToggleLike -> toggleLike(intent.postId)
            is FeedIntent.ToggleSave -> toggleSave(intent.postId)
            is FeedIntent.SharePost -> sharePost(intent.postId)
            is FeedIntent.AddComment -> addComment(intent.postId, intent.comment)
            is FeedIntent.DoubleTapLike -> doubleTapLike(intent.postId)
        }
    }
    
    override fun observeState(): StateFlow<FeedState> = _state.asStateFlow()
    
    override fun observeEffect(): SharedFlow<FeedEffect> = _effect.asSharedFlow()
    
    private fun loadFeed() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            try {
                // Simulate network delay
                kotlinx.coroutines.delay(1000)
                
                // Generate sample posts
                val posts = generateSamplePosts()
                
                _state.update { 
                    it.copy(
                        posts = posts,
                        isLoading = false,
                        error = null
                    ) 
                }
            } catch (e: Exception) {
                _state.update { 
                    it.copy(
                        isLoading = false,
                        error = "Failed to load feed: ${e.message}"
                    )
                }
                
                // Emit effect to show error toast
                _effect.emit(FeedEffect.ShowToast("Failed to load feed"))
            }
        }
    }
    
    private fun refreshFeed() {
        viewModelScope.launch {
            _state.update { it.copy(isRefreshing = true) }
            
            // Simulate network request
            kotlinx.coroutines.delay(1500)
            
            // Get current liked and saved posts
            val currentLikedMap = state.posts.associate { it.id to (likedPosts[it.id] ?: false) }
            val currentSavedMap = state.posts.associate { it.id to (savedPosts[it.id] ?: false) }
            
            // Generate new posts
            val newPosts = generateSamplePosts()
            
            // Restore liked and saved state for existing posts
            newPosts.forEach { post ->
                currentLikedMap[post.id]?.let { likedPosts[post.id] = it }
                currentSavedMap[post.id]?.let { savedPosts[post.id] = it }
            }
            
            _state.update { 
                it.copy(
                    posts = newPosts,
                    isRefreshing = false,
                    error = null
                ) 
            }
            
            // Show toast
            _effect.emit(FeedEffect.ShowToast("Feed refreshed"))
        }
    }
    
    private fun toggleLike(postId: Long) {
        // Toggle like state for the post
        val isLiked = !(likedPosts[postId] ?: false)
        likedPosts[postId] = isLiked
        
        // Update post in the state
        updatePostInState(postId) { post ->
            post.copy(
                likesCount = post.likesCount + if (isLiked) 1 else -1
            )
        }
    }
    
    private fun toggleSave(postId: Long) {
        // Toggle save state for the post
        savedPosts[postId] = !(savedPosts[postId] ?: false)
        
        viewModelScope.launch {
            // Show toast
            _effect.emit(
                FeedEffect.ShowToast(
                    if (savedPosts[postId] == true) "Post saved" else "Post unsaved"
                )
            )
        }
    }
    
    private fun sharePost(postId: Long) {
        viewModelScope.launch {
            // Emit effect to show share dialog
            _effect.emit(FeedEffect.ShowShareDialog(postId))
        }
    }
    
    private fun addComment(postId: Long, comment: String) {
        // Add new comment to the post
        val newComment = Comment(
            id = commentIdCounter.incrementAndGet(),
            username = "me",
            text = comment,
            timestamp = System.currentTimeMillis()
        )
        
        // Update post in the state
        updatePostInState(postId) { post ->
            post.copy(
                comments = post.comments + newComment
            )
        }
    }
    
    private fun doubleTapLike(postId: Long) {
        // Always set to liked on double tap
        val wasLiked = likedPosts[postId] ?: false
        likedPosts[postId] = true
        
        // Only increment the count if it wasn't already liked
        if (!wasLiked) {
            updatePostInState(postId) { post ->
                post.copy(
                    likesCount = post.likesCount + 1
                )
            }
        }
    }
    
    private fun updatePostInState(postId: Long, update: (Post) -> Post) {
        _state.update { currentState ->
            val updatedPosts = currentState.posts.map { post ->
                if (post.id == postId) update(post) else post
            }
            
            currentState.copy(posts = updatedPosts)
        }
    }
    
    private fun generateSamplePosts(): List<Post> {
        val posts = mutableListOf<Post>()

        // Sample usernames that look more realistic
        val usernames = listOf(
            "john.smith", "travel_diaries", "design.inspiration",
            "tech_enthusiast", "food_lover_123", "fitness.guru",
            "photography_pro", "art.ist", "music_fanatic", "nature_explorer"
        )

        // Sample post captions that look more realistic
        val captions = listOf(
            "Enjoying the weekend vibes! ☀️ #weekend #relax",
            "New setup, new possibilities. #workstation #productivity",
            "Morning coffee is always a good idea ☕ #coffee #morning",
            "Nature never fails to amaze me 🌿 #nature #explore #hiking",
            "Perfect day for outdoor activities! #outdoors #fun #weekend",
            "Good food, good mood 🍕 #foodie #delicious #yummy",
            "Working on exciting new projects! #work #creative #design",
            "Beach day with friends 🏖️ #beach #summer #friends",
            "City lights and good vibes ✨ #city #nightlife #urban",
            "This view was worth the climb 🏔️ #mountains #adventure #travel"
        )

        // Generate 25 sample posts (we'll have 20 regular posts plus sponsored and suggested)
        for (i in 1..25) {
            posts.add(
                Post(
                    id = i.toLong(),
                    username = usernames[i % usernames.size],
                    userAvatar = "https://picsum.photos/id/${100 + i}/150/150",
                    imageUrl = "https://picsum.photos/id/${200 + i}/500/500",
                    caption = captions[i % captions.size],
                    likesCount = (100..10000).random(),
                    timestamp = System.currentTimeMillis() - (i * 3600000), // Hours ago
                    comments = generateSampleComments(i)
                )
            )
        }

        return posts
    }

    private fun generateSampleComments(postId: Int): List<Comment> {
        val commentsCount = (0..5).random() // 0 to 5 comments per post
        val commentsList = mutableListOf<Comment>()

        for (i in 1..commentsCount) {
            commentsList.add(
                Comment(
                    id = (postId * 10 + i).toLong(),
                    username = "commenter_${(i * postId) % 10 + 1}",
                    text = "This is comment #$i on post #$postId. Great post!",
                    timestamp = System.currentTimeMillis() - ((i * 600000) + (postId * 3600000)) // Random times
                )
            )
        }

        return commentsList
    }
}
