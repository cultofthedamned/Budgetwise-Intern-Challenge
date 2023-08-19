package com.example.budgetwiseinternchallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetwiseinternchallenge.ui.animations.progressBarAnimation
import com.example.budgetwiseinternchallenge.ui.theme.Green
import com.example.budgetwiseinternchallenge.ui.theme.LightBlue
import com.example.budgetwiseinternchallenge.ui.theme.TextBlack
import com.example.budgetwiseinternchallenge.ui.theme.TextGray

@Composable
fun Category(
    modifier: Modifier = Modifier,
    image: Painter,
    categoryName: String,
    spentOnCategory: String,
    leftForCategory: String,
    color: Color,
    animationPlayed: Boolean,
    progress: Float
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(color)
                    .padding()
            ) {
                Image(
                    painter = image,
                    contentDescription = "null",
                    modifier = modifier
                        .padding(10.dp)
                )
            }
            Column(
                modifier = modifier
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = categoryName,
                    modifier = modifier
                        .padding(bottom = 5.dp),
                    color = TextBlack,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Row {
                    Text(
                        text = "spent",
                        color = TextGray,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = " $${spentOnCategory}",
                        color = Green,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = " of $100",
                        color = TextGray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$${leftForCategory}",
                color = Green,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "left",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
    LinearProgressIndicator(
        modifier = modifier
            .padding(bottom = 25.dp)
            .height(5.dp)
            .fillMaxWidth(),
        color = LightBlue,
        progress = progressBarAnimation(animationPlayed, progress),
    )
}