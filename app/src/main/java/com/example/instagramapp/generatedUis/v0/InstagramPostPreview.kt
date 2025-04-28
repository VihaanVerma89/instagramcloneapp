package com.example.instagramapp.generatedUis.v0

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun InstagramPostPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val imageUrl = "https://scontent.fblr1-5.fna.fbcdn.net/v/t39.30808-6/491834043_987394203500507_8627856862962029195_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=cc71e4&_nc_ohc=awSmyvt3eIkQ7kNvwGxGpie&_nc_oc=Adn168uhsZE9852hPkR-IzUuDSZ1YjO05kP2q0tImbKw5gs8dpwBoVJp3Fjz3_axuy7ouR9-uT5AamkWz20vEDMF&_nc_zt=23&_nc_ht=scontent.fblr1-5.fna&_nc_gid=IUmS-PIfY23xZqrHcCNrCw&oh=00_AfFPJvEV2ofNfz_ciYTw7RTpa4jhoZ3Eavz2_24opLA73A&oe=6814D6C4"
            val samplePost = Post(
                id = "1",
                authorName = "pixel_muse_",
                authorImageUrl = "https://scontent.fblr1-8.fna.fbcdn.net/v/t39.30808-1/466463917_880511570956102_8348740157884585754_n.jpg?stp=dst-jpg_s480x480_tt6&_nc_cat=1&ccb=1-7&_nc_sid=2d3e12&_nc_ohc=7NmvsQg9BH0Q7kNvwErE_9h&_nc_oc=Adl31ygZtiPpt8ohcpfwCclSodX9wdkeq7mYJClGZsO7w_fOL-zzWq2EHCVNOTHqpntHAKIvZbMcVx4h21Xzja_w&_nc_zt=24&_nc_ht=scontent.fblr1-8.fna&_nc_gid=9H8psDGE1RyQWoWSHQunew&oh=00_AfGqkPIsh-hLUVSybbyYPtlrR4_fw57Xa02adj3MonDPpQ&oe=680A9768",
                soundTrack = SoundTrack(
                    artist = "Chad Kroeger",
                    title = "Hero (feat. J...)"
                ),
                postImages = listOf(imageUrl),
                caption = "Heroes are made by the path they choose, not the powers they are graced with...",
                likeCount = 30,
                commentCount = 5,
                likedBy = "a__sis__h",
                timestamp = "25 February",
                isFollowing = false
            )
            
            InstagramPost(
                post = samplePost,
                onLikeClick = {},
                onCommentClick = {},
                onShareClick = {},
                onBookmarkClick = {},
                onFollowClick = {},
                onMoreOptionsClick = {},
                onProfileClick = {}
            )
        }
    }
}