package com.example.budgetwiseinternchallenge.ui.sreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.budgetwiseinternchallenge.ui.theme.Gray
import com.example.budgetwiseinternchallenge.ui.components.Budget
import com.example.budgetwiseinternchallenge.ui.components.TopBar

@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Gray)
            .fillMaxSize()
    ) {
        TopBar()
        Budget()
    }
}