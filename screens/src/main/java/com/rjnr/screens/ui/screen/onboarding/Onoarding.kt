package com.rjnr.screens.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.NavigationRoot
import com.rjnr.navigation.Screen
import com.rjnr.navigation.navigation
import com.rjnr.screens.R


@Composable
fun OnboardingScreen(screen: Screen, modifier: Modifier = Modifier) {
    val navigation = navigation()
    RickAndMorty(modifier, navigation)

}

@Composable
private fun RickAndMorty(
    modifier: Modifier,
    navigation: Navigation
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            Image(
                painter = painterResource(id = R.drawable.rick),
                contentDescription = "Rick sanchez",
                modifier = Modifier
                    .padding(bottom = 78.dp, end = 12.dp)
                    .clip(shape = CircleShape)
            )

            Text(
                text = "&",
                style = MaterialTheme.typography.displaySmall,
                modifier = modifier.padding(top = 72.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.morty),
                contentDescription = "Morty",
                modifier = modifier
                    .padding(top = 78.dp, start = 8.dp)
                    .clip(shape = CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = modifier.padding(top = 22.dp),
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

@Preview(showBackground = true)
@Composable
fun OnboardPrev() {
    NavigationRoot(navigation = Navigation()) {
        RickAndMorty(modifier = Modifier, navigation = navigation())
    }

}
