package com.example.instagramapp.generatedUis


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrendingTodayScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(16.dp)
    ) {
        // Header with "Trending Today" and "See All" button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Trending Today",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "See All",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF0066CC)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "See All",
                    tint = Color(0xFF0066CC)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // First row of trending items
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TrendingItem(
                text = "गुड नाईट",
                emoji = "💤",
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFFF0E6FF)
            )

            Spacer(modifier = Modifier.width(8.dp))

            TrendingItem(
                text = "शुभ शुक्रवार",
                emoji = "🙏",
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFFFFF0F5)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Single wide trending item
        TrendingItem(
            text = "प्रकाश पुर्नवमी श्री गुरु टेग बहादुर जी",
            emoji = "🔥",
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xFFF5F5F5)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Second row of trending items
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TrendingItem(
                text = "प्रेमानंद जी महाराज",
                emoji = "🙏",
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFFFFEEEE)
            )

            Spacer(modifier = Modifier.width(8.dp))

            TrendingItem(
                text = "जुम्मा मुबारक",
                emoji = "👍",
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFFF5F0E0)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Second wide trending item
        TrendingItem(
            text = "मेरठ में एक और सनसनीखेज हत्याकांड",
            emoji = "😢",
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xFFE6F2FF)
        )
    }
}

@Composable
fun TrendingItem(
    text: String,
    emoji: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White
) {
    Surface(
        modifier = modifier
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = emoji,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrendingTodayPreview() {
    MaterialTheme {
        TrendingTodayScreen()
    }
}