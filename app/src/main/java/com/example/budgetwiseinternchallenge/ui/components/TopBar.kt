package com.example.budgetwiseinternchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.budgetwiseinternchallenge.ui.theme.Purple

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    val topHeight = 100.dp
    val borderRadius = 25.dp
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = borderRadius, bottomEnd = borderRadius
                )
            )
            .height(topHeight)
            .fillMaxWidth()
            .background(Purple)
    )
}