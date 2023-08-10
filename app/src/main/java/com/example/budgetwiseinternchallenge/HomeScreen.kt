package com.example.budgetwiseinternchallenge

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetwiseinternchallenge.ui.theme.Gray
import com.example.budgetwiseinternchallenge.ui.theme.Purple
import com.example.budgetwiseinternchallenge.ui.theme.White
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.budgetwiseinternchallenge.ui.theme.Green
import com.example.budgetwiseinternchallenge.ui.theme.LightGreen
import com.example.budgetwiseinternchallenge.ui.theme.ProgressBarPurple
import com.example.budgetwiseinternchallenge.ui.theme.TextBlack
import com.example.budgetwiseinternchallenge.ui.theme.TextGray
import kotlinx.coroutines.delay

@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Gray)
            .fillMaxSize()
    ) {
        Column {
            Header()
            Budget()
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    val topHeight = 100.dp
    val borderRadius = 25.dp
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = borderRadius,
                    bottomEnd = borderRadius
                )
            )
            .height(topHeight)
            .fillMaxWidth()
            .background(Purple)
    )
}

@Composable
fun Budget(modifier: Modifier = Modifier) {
    val paddingTop = 70.dp
    Column(
        modifier = modifier
            .offset(y = -paddingTop)
    ) {
        HeaderTitle()
        BudgetContent()
    }
}

@Composable
fun HeaderTitle(modifier: Modifier = Modifier) {
    val paddingSides = 25.dp
    val itemSize = 20.sp
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = paddingSides)
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {
        Row {
            Text(
                text = "Monthly Budget",
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = itemSize
            )
        }
        Row {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = White,
                modifier = modifier
                    .padding(end = 5.dp)
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = White
            )
        }
    }
}

@Composable
fun BudgetContent(modifier: Modifier = Modifier) {
    val height = 400.dp
    val a = 25.dp
    val spent = 800
    val available = 1200
    val budget = 2000
    Column(
        modifier = modifier
            .padding(horizontal = a)
            .clip(
                RoundedCornerShape(a)
            )
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .background(White)
    ) {
        MonthBar()
        BudgetBar(modifier = modifier, spent, available, budget)
        BudgetProgressBar(modifier = modifier, spent, budget)
    }
}

@Composable
fun MonthBar(modifier: Modifier = Modifier) {
    val months = arrayOf(
        "January 2022",
        "February 2022",
        "March 2022",
        "April 2022",
        "May 2022",
        "June 2022",
        "July 2022",
        "August 2022",
        "September 2022",
        "October 2022",
        "November 2022",
        "December 2022"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(months[3]) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 25.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(5.dp))
                .background(LightGreen)
                .padding(start = 6.dp)
                .clickable { expanded = !expanded }
        ) {
            Text(
                text = selectedText,
                color = Green,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open or close the drop down",
                tint = Green
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier
                .wrapContentSize(Alignment.TopCenter)
        ) {
            months.forEach { item ->
                DropdownMenuItem(
                    modifier = modifier
                        .background(LightGreen),
                    text = {
                        Text(
                            text = item,
                            color = Green,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    onClick = {
                        selectedText = item
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun BudgetBar(modifier: Modifier = Modifier, spent: Int, available: Int, budget: Int) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Spent",
                color = TextGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$${spent}",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Divider(
            color = TextGray,
            modifier = modifier
                .height(30.dp)
                .width(1.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Available",
                color = TextGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$${available}",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Divider(
            color = TextGray,
            modifier = modifier
                .height(30.dp)
                .width(1.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Budget",
                color = TextGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$${budget}",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun BudgetProgressBar(modifier: Modifier = Modifier, spent: Int, budget: Int) {
    var progress by remember { mutableStateOf(calculatePercent(spent, budget)) }
    var animationPlayed by remember { mutableStateOf(false) }
    val progressAnimation by animateFloatAsState(
        targetValue = if (animationPlayed) progress else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = ""
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    LinearProgressIndicator(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 25.dp)
            .height(30.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        color = ProgressBarPurple,
        progress = progressAnimation,
    )
}

fun calculatePercent(spent: Int, budget: Int): Float {
    return ((spent.toFloat() * 100) / budget.toFloat()) / 100
}




