package com.rjnr.screens.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.navigation

@Composable
fun DetailScreen(modifier: Modifier = Modifier, screen: DetailScreen) {
    val navigation = navigation()
    UI(modifier = modifier)


    BackHandler {
        navigation.onBackPressed()
    }
}

@Composable
private fun UI(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hii details")
    }

}