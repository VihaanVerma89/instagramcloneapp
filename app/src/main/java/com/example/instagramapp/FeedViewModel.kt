import androidx.lifecycle.ViewModel
import com.example.instagramapp.Comment
import com.example.instagramapp.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FeedViewModel : ViewModel() {
    // Our post data
    private val _feedPosts = MutableStateFlow<List<Post>>(emptyList())
    val feedPosts: StateFlow<List<Post>> = _feedPosts.asStateFlow()

    init {
        // Generate 20 sample posts when the ViewModel is created
        _feedPosts.value = generateSamplePosts()
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


    private fun generateSampleComments(postId: Int) : List<Comment>{

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
