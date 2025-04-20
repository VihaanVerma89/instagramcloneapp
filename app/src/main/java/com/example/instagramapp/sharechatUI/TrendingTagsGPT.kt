package com.example.instagramapp.sharechatUI


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrendingTodaySection() {
    val trendingTags = listOf(
        "\uD83D\uDD4F\u0917\u0941\u0921 \u092B\u094D\u0930\u093E\u0907\u0921\u0947",
        "\uD83C\uDF38\u0936\u0941\u092D \u0936\u0941\u0915\u094D\u0930\u0935\u093E\u0930\uD83D\uDE4F",
        "\u2694\uFE0F\u092A\u094D\u0930\u0915\u093E\u0936 \u0917\u0941\u0930\u0942\u092A\u0930\u094D\u0935 \u0936\u094D\u0930\u0940 \u0917\u0941\u0930\u0942 \u0924\u0947\u0917 \u092C\u0939\u093E\u0926\u0941\u0930 \u091C\u0940 \uD83D\uDD25",
        "\uD83C\uDDF3\uD83C\uDFC3\u092A\u094D\u0930\u0947\u092E\u093E\u0928\u0902\u0926 \u091C\u0940 \u092E\u0939\u093E\u0930\u093E\u091C\uD83D\uDE4F",
        "\uD83C\uDFF0\u091C\u0941\u092E\u094D\u092E\u093E \u092E\u0941\u092C\u093E\u0930\u0915\uD83D\uDE4C",
        "\uD83D\uDE31\u092E\u0947\u0930\u0920 \u092E\u0947\u0902 \u090F\u0915 \u0914\u0930 \u0938\u0902\u0938\u0928\u0940\u0916\u0947\u091C \u0939\u0924\u094D\u092F\u093E\u0915\u093E\u0902\u0921"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Trending Today",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "See All >",
                color = Color(0xFF1E88E5),
                fontSize = 14.sp,
                modifier = Modifier.clickable { /* Handle click */ }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Trending Tags
        FlowRow(
        ) {
            trendingTags.forEach { tag ->
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(text = tag, fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrendingTodaySectionPreview() {
    TrendingTodaySection()
}
