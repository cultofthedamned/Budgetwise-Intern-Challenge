package com.example.budgetwiseinternchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.budgetwiseinternchallenge.R
import com.example.budgetwiseinternchallenge.ui.animations.calculatePercentage
import com.example.budgetwiseinternchallenge.ui.animations.progressBarAnimation
import com.example.budgetwiseinternchallenge.ui.theme.DarkBlue
import com.example.budgetwiseinternchallenge.ui.theme.Green
import com.example.budgetwiseinternchallenge.ui.theme.LightBlue
import com.example.budgetwiseinternchallenge.ui.theme.LightGreen
import com.example.budgetwiseinternchallenge.ui.theme.LightPurple
import com.example.budgetwiseinternchallenge.ui.theme.Orange
import com.example.budgetwiseinternchallenge.ui.theme.ProgressBarPurple
import com.example.budgetwiseinternchallenge.ui.theme.TextBlack
import com.example.budgetwiseinternchallenge.ui.theme.TextGray
import com.example.budgetwiseinternchallenge.ui.theme.Turquoise
import com.example.budgetwiseinternchallenge.ui.theme.White

@Composable
fun Budget(modifier: Modifier = Modifier) {
    val paddingTop = 70.dp
    Column(
        modifier = modifier.offset(y = -paddingTop)
    ) {
        TopBarContent()
        BudgetContent()
    }
}

@Composable
fun TopBarContent(modifier: Modifier = Modifier) {
    val paddingSides = 25.dp
    val itemSize = 20.sp
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = paddingSides)
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Monthly Budget",
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = itemSize
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center, modifier = modifier.height(25.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = White,
                modifier = modifier.padding(end = 5.dp)
            )
            Icon(
                imageVector = Icons.Default.MoreVert, contentDescription = null, tint = White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetContent(modifier: Modifier = Modifier) {
    val budget = 2000
    val categories = arrayOf("Food", "Shopping", "Transportation", "Education")
    var totalSpent by remember { mutableStateOf(800) }
    var totalAvailable by remember { mutableStateOf(1200) }
    var spentOnFood by remember { mutableStateOf(10) }
    var spentOnShopping by remember { mutableStateOf(20) }
    var spentOnTransportation by remember { mutableStateOf(20) }
    var spentOnEducation by remember { mutableStateOf(40) }
    var isDialogShown by remember { mutableStateOf(false) }
    var totalSpentProgress by remember {
        mutableStateOf(
            calculatePercentage(
                totalSpent,
                budget
            )
        )
    }
    var spentOnFoodProgress by remember {
        mutableStateOf(
            calculatePercentage(
                spentOnFood,
                100
            )
        )
    }
    var spentOnShoppingProgress by remember {
        mutableStateOf(
            calculatePercentage(
                spentOnShopping,
                100
            )
        )
    }
    var spentOnEducationProgress by remember {
        mutableStateOf(
            calculatePercentage(
                spentOnEducation,
                100
            )
        )
    }
    var spentOnTransportationProgress by remember {
        mutableStateOf(
            calculatePercentage(
                spentOnTransportation,
                100
            )
        )
    }
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(categories[0]) }
    var spentOnCategoryAmount by remember { mutableStateOf("") }
    fun onFABClick() {
        isDialogShown = true
    }

    fun onDismiss() {
        isDialogShown = false
    }
    Column(
        modifier = modifier
            .padding(horizontal = 25.dp)
            .clip(
                RoundedCornerShape(25.dp)
            )
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .background(White),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        MonthSelectionBar()
        BudgetBar(modifier = modifier, totalSpent, totalAvailable, budget)
        BudgetCategories(
            modifier = modifier,
            spentOnFood,
            spentOnShopping,
            spentOnTransportation,
            spentOnEducation,
            totalSpentProgress,
            spentOnFoodProgress,
            spentOnShoppingProgress,
            spentOnEducationProgress,
            spentOnTransportationProgress
        )
    }
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
    ) {
        FloatingActionButton(
            onClick = {
                onFABClick()
            },
            containerColor = LightPurple,
            shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
            modifier = modifier
                .padding(end = 25.dp, top = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "null",
                tint = White
            )
        }
    }
    if (isDialogShown) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = modifier
                    .fillMaxWidth(0.95f)
                    .shadow(10.dp, shape = RoundedCornerShape(15.dp))
                    .clip(shape = RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 25.dp, bottom = 10.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Row(modifier = modifier
                                .clip(RoundedCornerShape(5.dp))
                                .background(LightGreen)
                                .padding(start = 6.dp)
                                .clickable { expanded = !expanded }) {
                                Text(
                                    text = selectedCategory,
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
                                modifier = modifier.wrapContentSize(Alignment.TopCenter)
                            ) {
                                categories.forEach { item ->
                                    DropdownMenuItem(
                                        modifier = modifier.background(LightGreen),
                                        text = {
                                            Text(
                                                text = item,
                                                color = Green,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                        },
                                        onClick = {
                                            selectedCategory = item
                                            expanded = false
                                        })
                                }
                            }
                        }

                    }
                    TextField(
                        value = spentOnCategoryAmount,
                        onValueChange = { newText ->
                            spentOnCategoryAmount = newText
                        },
                        colors = TextFieldDefaults.textFieldColors(containerColor = White)
                    )
                    Button(onClick = {
                        onDismiss()
                        when (selectedCategory) {
                            "Food" -> {
                                spentOnFood += spentOnCategoryAmount.toInt()
                                totalSpent += spentOnCategoryAmount.toInt()
                                totalAvailable -= spentOnCategoryAmount.toInt()
                                spentOnFoodProgress = calculatePercentage(spentOnFood, 100)
                                totalSpentProgress = calculatePercentage(totalSpent, budget)
                            }

                            "Shopping" -> {
                                spentOnShopping += spentOnCategoryAmount.toInt()
                                totalSpent += spentOnCategoryAmount.toInt()
                                totalAvailable -= spentOnCategoryAmount.toInt()
                                spentOnShoppingProgress = calculatePercentage(spentOnShopping, 100)
                                totalSpentProgress = calculatePercentage(totalSpent, budget)
                            }

                            "Transportation" -> {
                                spentOnTransportation += spentOnCategoryAmount.toInt()
                                totalSpent += spentOnCategoryAmount.toInt()
                                totalAvailable -= spentOnCategoryAmount.toInt()
                                spentOnTransportationProgress =
                                    calculatePercentage(spentOnTransportation, 100)
                                totalSpentProgress = calculatePercentage(totalSpent, budget)
                            }

                            "Education" -> {
                                spentOnEducation += spentOnCategoryAmount.toInt()
                                totalSpent += spentOnCategoryAmount.toInt()
                                totalAvailable -= spentOnCategoryAmount.toInt()
                                spentOnEducationProgress =
                                    calculatePercentage(spentOnEducation, 100)
                                totalSpentProgress = calculatePercentage(totalSpent, budget)
                            }
                        }
                    }
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}

@Composable
fun MonthSelectionBar(modifier: Modifier = Modifier) {
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
    var selectedMonth by remember { mutableStateOf(months[3]) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 25.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Row(modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(LightGreen)
            .padding(start = 6.dp)
            .clickable { expanded = !expanded }) {
            Text(
                text = selectedMonth,
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
            modifier = modifier.wrapContentSize(Alignment.TopCenter)
        ) {
            months.forEach { item ->
                DropdownMenuItem(modifier = modifier.background(LightGreen), text = {
                    Text(
                        text = item,
                        color = Green,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }, onClick = {
                    selectedMonth = item
                    expanded = false
                })
            }
        }
    }
}

@Composable
fun BudgetBar(modifier: Modifier = Modifier, spent: Int, available: Int, budget: Int) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Spent", color = TextGray, fontSize = 16.sp, fontWeight = FontWeight.Medium
            )
            Text(
                text = "$${spent}",
                color = TextBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Divider(
            color = TextGray, modifier = modifier
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
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$${available}",
                color = Green,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Divider(
            color = TextGray, modifier = modifier
                .height(30.dp)
                .width(1.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Budget", color = TextGray, fontSize = 16.sp, fontWeight = FontWeight.Medium
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
fun BudgetCategories(
    modifier: Modifier = Modifier,
    spentOnFood: Int,
    spentOnShopping: Int,
    spentOnTransportation: Int,
    spentOnEducation: Int,
    totalSpentProgress: Float,
    spentOnFoodProgress: Float,
    spentOnShoppingProgress: Float,
    spentOnEducationProgress: Float,
    spentOnTransportationProgress: Float,
) {
    val leftForFood = 100 - spentOnFood
    val leftForShopping = 100 - spentOnShopping
    val transportationLeft = 100 - spentOnTransportation
    val educationLeft = 100 - spentOnEducation
    var animationPlayed by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    LinearProgressIndicator(
        modifier = modifier
            .padding(start = 14.dp, end = 14.dp, top = 10.dp, bottom = 25.dp)
            .height(30.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        color = ProgressBarPurple,
        progress = progressBarAnimation(animationPlayed, totalSpentProgress),
    )
    Column {
        Category(
            modifier,
            painterResource(id = R.drawable.food),
            "Food",
            spentOnFood.toString(),
            leftForFood.toString(),
            DarkBlue,
            animationPlayed,
            spentOnFoodProgress
        )
        Category(
            modifier,
            painterResource(id = R.drawable.shopping),
            "Shopping",
            spentOnShopping.toString(),
            leftForShopping.toString(),
            LightBlue,
            animationPlayed,
            spentOnShoppingProgress
        )
        Category(
            modifier,
            painterResource(id = R.drawable.transportation),
            "Transportation",
            spentOnTransportation.toString(),
            transportationLeft.toString(),
            Orange,
            animationPlayed,
            spentOnTransportationProgress
        )
        Category(
            modifier,
            painterResource(id = R.drawable.education),
            "Education",
            spentOnEducation.toString(),
            educationLeft.toString(),
            Turquoise,
            animationPlayed,
            spentOnEducationProgress
        )
    }
}