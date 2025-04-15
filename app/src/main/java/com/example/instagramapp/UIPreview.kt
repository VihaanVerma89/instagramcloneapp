package com.example.instagramapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.instagramapp.ui.theme.InstagramAppTheme
import com.example.instagramclone.BottomNavigationBar
import com.example.instagramclone.PostItem
import com.example.instagramclone.SponsoredPost
import com.example.instagramclone.TopAppBar


@Preview(showBackground = true)
@Composable
fun previewTopAppBar() {
    InstagramAppTheme {
        TopAppBar()
    }
}

@Preview(showBackground = true)
@Composable
fun previewBottomNavigationBar() {
    InstagramAppTheme {
        BottomNavigationBar()
    }
}


@Preview(showBackground = true)
@Composable
fun previewSponseredPost() {
    InstagramAppTheme {
        SponsoredPost()
    }
}


@Preview(showBackground = true)
@Composable
fun previewPostItem() {
    InstagramAppTheme {
        val post = Post(
            id = 1,
            username = "user_1",
            userAvatar = "https://picsum.photos/id/101/150/150",
            imageUrl = "https://picsum.photos/id/102/500/500",
            caption = "This is a sample post caption.",
            likesCount = 1234,
            timestamp = System.currentTimeMillis(),
            comments = emptyList()
        )
        PostItem(post)
    }
}

@Preview(showBackground = true)
@Composable
fun previewFeed(){
    InstagramAppTheme {

    }
}