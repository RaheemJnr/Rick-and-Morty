package com.rjnr.screens.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rjnr.navigation.ListScreen

@Composable
fun ListScreen(screen: ListScreen) {
    Text(text = "Hii $screen")
}