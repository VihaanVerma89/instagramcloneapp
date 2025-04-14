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

        // Generate 20 sample posts
        for (i in 1..20) {
            posts.add(
                Post(
                    id = i.toLong(),
                    username = "user_${i % 5 + 1}",
                    userAvatar = "https://picsum.photos/id/${100 + i}/150/150",
                    imageUrl = "https://picsum.photos/id/${200 + i}/500/500",
                    caption = "This is a sample post caption for post #$i. #instagram #clone #android",
                    likesCount = (100..10000).random(),
                    timestamp = System.currentTimeMillis() - (i * 3600000), // Hours ago
                    comments = generateSampleComments(i)
                )
            )
        }

        return posts
    }

    private fun generateSampleComments(postId: Int): List<Comment> {
        val commentsList = mutableListOf<Comment>()
        val commentsCount = (0..5).random() // 0 to 5 comments per post

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
