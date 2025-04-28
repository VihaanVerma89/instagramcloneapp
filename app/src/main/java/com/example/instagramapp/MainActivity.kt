package com.example.instagramapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.instagramapp.mvi.feed.FeedScreen
import com.example.instagramapp.mvi.feed.FeedViewModel
// Backup implementation for fallback
import com.example.instagramapp.RefreshableFeedFixed
import com.example.instagramapp.ui.theme.InstagramAppTheme

class MainActivity : ComponentActivity() {
    // Use the new MVI ViewModel
    private val feedViewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstagramAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Try to use MVI implementation first, fallback to fixed implementation if there are issues
                    try {
                        FeedScreen(feedViewModel)
                    } catch (e: Exception) {
                        // Fallback to the fixed implementation
                        RefreshableFeedFixed(feedViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstagramAppTheme {
        Greeting("Android")
    }
}