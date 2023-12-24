package com.rjnr.screens.ui.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.navigation

@Composable
fun DetailScreen(modifier: Modifier = Modifier, screen: DetailScreen) {
    val navigation = navigation()
    Details(modifier = modifier, navigation)

    BackHandler {
        navigation.onBackPressed()
    }
}

@Composable
private fun Details(
    modifier: Modifier,
    navigation: Navigation,

) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Hi, this is the details screen",
        )
        Button(
            onClick = {
                navigation.onBackPressed()
            },
        ) {
            Text(
                text = "Move to List screen",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
