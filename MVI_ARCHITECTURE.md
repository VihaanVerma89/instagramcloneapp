# MVI Architecture in Instagram Clone

This document explains the Model-View-Intent (MVI) architecture pattern implemented in this Instagram clone app.

## What is MVI?

MVI is a unidirectional data flow architecture pattern that consists of:

- **Model**: Represents the state of the UI
- **View**: Renders the UI based on the current state
- **Intent**: Represents user actions that can change the state

## Key Components

### UiState

Represents the current state of the UI. In our Instagram clone:

```kotlin
data class FeedState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
) : UiState
```

### UiIntent

Represents actions that can change the state. In our app:

```kotlin
sealed class FeedIntent : UiIntent {
    object LoadFeed : FeedIntent()
    object RefreshFeed : FeedIntent()
    data class ToggleLike(val postId: Long) : FeedIntent()
    data class ToggleSave(val postId: Long) : FeedIntent()
    data class SharePost(val postId: Long) : FeedIntent()
    data class AddComment(val postId: Long, val comment: String) : FeedIntent()
    data class DoubleTapLike(val postId: Long) : FeedIntent()
}
```

### UiEffect

Represents one-time events that don't change the state but need to be handled by the UI:

```kotlin
sealed class FeedEffect : UiEffect {
    data class ShowToast(val message: String) : FeedEffect()
    data class Navigate(val route: String) : FeedEffect()
    data class ShowShareDialog(val postId: Long) : FeedEffect()
}
```

### ViewModel

The ViewModel processes intents, updates the state, and emits effects:

```kotlin
class FeedViewModel : ViewModel(), MVIViewModel<FeedState, FeedIntent, FeedEffect> {
    // State and effect flows
    private val _state = MutableStateFlow(FeedState())
    private val _effect = MutableSharedFlow<FeedEffect>()
    
    // Process user intents
    override fun processIntent(intent: FeedIntent) {
        when (intent) {
            is FeedIntent.LoadFeed -> loadFeed()
            is FeedIntent.RefreshFeed -> refreshFeed()
            // ...
        }
    }
    
    // Expose state and effects as read-only flows
    override fun observeState(): StateFlow<FeedState> = _state.asStateFlow()
    override fun observeEffect(): SharedFlow<FeedEffect> = _effect.asSharedFlow()
}
```

## Flow of Data in MVI

1. The UI sends intents to the ViewModel via `processIntent()`
2. The ViewModel processes these intents and updates the state accordingly
3. The UI observes the state and re-renders when changes occur
4. One-time events (effects) are handled by the UI via `observeEffect()`

## Benefits of MVI

- **Predictable state**: State changes come from a single source of truth
- **Testability**: Each component can be tested in isolation
- **Debuggability**: Every user action is explicit and can be logged
- **Unidirectional data flow**: Makes it easier to reason about the app
- **Immutable state**: Prevents accidental modifications

## Implementation in this App

The Instagram clone app follows this MVI pattern with:

- **Models**: `FeedState` and other data classes
- **Views**: Composables like `FeedScreen` and `InstagramFeedContent`
- **Intents**: Actions defined in `FeedIntent`
- **Effects**: One-time events defined in `FeedEffect`
- **ViewModel**: Processes intents and updates state

## Usage Example

```kotlin
// Screen observes state and sends intents
@Composable
fun FeedScreen(viewModel: FeedViewModel) {
    val uiState by viewModel.observeState().collectAsState()
    
    // Observe effects for one-time events
    LaunchedEffect(Unit) {
        viewModel.observeEffect().collectLatest { effect ->
            // Handle effects...
        }
    }
    
    // Process user actions as intents
    Button(onClick = { viewModel.processIntent(FeedIntent.RefreshFeed) }) {
        Text("Refresh")
    }
    
    // UI renders based on current state
    if (uiState.isLoading) {
        CircularProgressIndicator()
    } else {
        InstagramFeedContent(posts = uiState.posts)
    }
}
```

This architecture ensures a clean separation of concerns and a predictable data flow throughout the app.
