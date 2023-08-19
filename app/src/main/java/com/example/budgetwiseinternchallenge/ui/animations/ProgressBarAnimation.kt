package com.example.budgetwiseinternchallenge.ui.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

fun calculatePercentage(spent: Int, budget: Int): Float {
    return ((spent.toFloat() * 100) / budget.toFloat()) / 100
}

@Composable
fun progressBarAnimation(animationPlayed: Boolean, progress: Float): Float {
    val progressEducation by animateFloatAsState(

        targetValue = if (animationPlayed) progress else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = ""
    )
    return progressEducation
}