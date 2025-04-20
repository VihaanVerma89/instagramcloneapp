
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

// Instagram light theme colors
private val InstagramBackground = Color.White
private val InstagramTextPrimary = Color.Black
private val InstagramTextSecondary = Color(0xFF8E8E8E)
private val InstagramBlue = Color(0xFF3897F0)

@Composable
fun InstagramPost() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(InstagramBackground)
    ) {
        // Header
        PostHeader()

        // Content
        PostContent()

        // Interaction Buttons
        InteractionButtons()

        // Likes
        LikesSection()

        // Caption
        CaptionSection()

        // Posted Time
        TimeSection()
    }
}

@Composable
fun PostHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Picture
            AsyncImage(
                model = "https://picsum.photos/seed/profile/200",
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Username and song info
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ghantaa",
                        color = InstagramTextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    // Verified badge
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified",
                        tint = InstagramBlue,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Music icon
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = "Music",
                        tint = InstagramTextPrimary,
                        modifier = Modifier.size(12.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "Ajay-Atul, Shreya Ghoshal • Chikni Chameli",
                        color = InstagramTextPrimary,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        // Three dots menu
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More Options",
            tint = InstagramTextPrimary
        )
    }
}

@Composable
fun PostContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        // Main Image - using a Bollywood-related image
        AsyncImage(
            model = "https://static.toiimg.com/thumb/msid-11180330,imgsize-47347,width-400,resizemode-4/11180330.jpg",
            contentDescription = "Post Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

//        // Text Overlay
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            // Top text - keeping white for visibility on image
//            Text(
//                text = "CRAZY HOW NO ONE NOTICED\nZAKIR KHAN AND SACHIN",
//                color = Color.White,
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp,
//                lineHeight = 28.sp
//            )
//
//            // Bottom text - keeping white for visibility on image
//            Text(
//                text = "IN CHAMELI SONG",
//                color = Color.White,
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//        }
//
        // Mute icon
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0x80000000)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.VolumeOff,
                contentDescription = "Muted",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun InteractionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Like button
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = InstagramTextPrimary,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Comment button
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.ChatBubbleOutline,
                    contentDescription = "Comment",
                    tint = InstagramTextPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Share button
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Share",
                    tint = InstagramTextPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Bookmark button
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.BookmarkBorder,
                contentDescription = "Save",
                tint = InstagramTextPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun LikesSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile picture of liker
        AsyncImage(
            model = "https://picsum.photos/seed/liker/100",
            contentDescription = "Liker's Profile",
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Likes text
        Text(
            text = buildAnnotatedString {
                append("Liked by ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("jeejaji")
                }
                append(" and others")
            },
            color = InstagramTextPrimary,
            fontSize = 14.sp
        )
    }
}

@Composable
fun CaptionSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("ghantaa")
                }
                append(" The journey is never easy")
            },
            color = InstagramTextPrimary,
            fontSize = 14.sp
        )
    }
}

@Composable
fun TimeSection() {
    Text(
        text = "1 day ago",
        color = InstagramTextSecondary,
        fontSize = 12.sp,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    )
}

// For preview in Android Studio
@Preview(showBackground = true)
@Composable
fun InstagramPostPreview() {
    Surface(color = InstagramBackground) {
        InstagramPost()
    }
}