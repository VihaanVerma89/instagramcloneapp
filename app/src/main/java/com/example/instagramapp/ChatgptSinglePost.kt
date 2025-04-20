
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter

@Composable
fun InstagramPostUI() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Top Profile Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://juststickers.in/wp-content/uploads/2016/06/ghanta-badge.png"), // replace with your local logo resource
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "ghantaa", fontWeight = FontWeight.Bold)
                Text(text = "Ajay-Atul, Shreya Ghoshal • Chikni Chameli", fontSize = 12.sp)
            }
            IconButton(onClick = { /* TODO: Menu action */ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
        }

        // Main Image
        Image(
            painter = rememberAsyncImagePainter("https://static.toiimg.com/thumb/msid-11180330,imgsize-47347,width-400,resizemode-4/11180330.jpg"), // replace with actual URL
            contentDescription = "Main Post Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

        // Text below image
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CRAZY HOW NO ONE NOTICED\nZAKIR KHAN AND SACHIN\nIN CHAMELI SONG",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        // Like, Comment and Share Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "\u2764\ufe0f 34K", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "\ud83d\udcac 476", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "\ud83d\udd17 1,854", fontWeight = FontWeight.Bold)
        }

        // Caption
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(buildString {
                append("ghantaa ")
                append("The journey is never easy")
            }, fontWeight = FontWeight.Normal)
            Text(text = "1 day ago", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

// For preview in Android Studio
@Preview(showBackground = true)
@Composable
fun InstagramPostPreview1() {
    Surface(color = Color.White) {
        InstagramPostUI()
    }
}
