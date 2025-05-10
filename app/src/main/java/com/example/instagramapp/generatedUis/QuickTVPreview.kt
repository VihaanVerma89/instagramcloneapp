package com.example.instagramapp.generatedUis

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Preview for the Quick TV App
 */
@Preview(showBackground = true)
@Composable
fun QuickTVAppPreview() {
    QuickTVApp()
}

/**
 * Preview for the Video Carousel
 */
@Preview(showBackground = true)
@Composable
fun VideoCarouselPreview() {
    VideoCarousel()
}

/**
 * Preview for the Top Picks Section
 */
@Preview(showBackground = true)
@Composable
fun TopPicksSectionPreview() {
    TopPicksSection()
}

/**
 * Preview for the New Releases Section
 */
@Preview(showBackground = true)
@Composable
fun NewReleasesSectionPreview() {
    NewReleasesSection()
}

/**
 * Preview for the Stories Row
 */
@Preview(showBackground = true)
@Composable
fun StoriesRowPreview() {
    StoriesRow()
}

/**
 * Preview for the Post Image with double tap like feature
 */
@Preview(showBackground = true)
@Composable
fun PostImagePreview() {
    PostImage(
        imageUrl = "https://picsum.photos/id/237/400/400",
        onDoubleTap = {}
    )
}

/**
 * Preview for the Bottom Navigation Bar
 */
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}

/**
 * Preview for the Refreshable Feed
 */
// Preview for RefreshableFeed disabled due to viewModel requirement
// @Preview(showBackground = true)
// @Composable
// fun RefreshableFeedPreview() {
//     RefreshableFeed()
// }
