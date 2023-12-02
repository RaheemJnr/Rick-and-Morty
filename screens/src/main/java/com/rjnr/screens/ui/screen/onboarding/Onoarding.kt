package com.rjnr.screens.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Screen
import com.rjnr.navigation.navigation
import com.rjnr.screens.R


@Composable
fun OnboardingScreen(screen: Screen) {
    val navigation = navigation()
    Column(modifier = Modifier.fillMaxSize()) {

        Row {
            Image(
                painter = painterResource(id = R.drawable.rick),
                contentDescription = "Rick sanchez"
            )

            Image(
                painter = painterResource(id = R.drawable.morty),
                contentDescription = "Morty"
            )
        }

        Button(
            onClick = {
                navigation.navigateTo(ListScreen)
            }
        ) {
            Text(
                text = "Move to next screen",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }



}
