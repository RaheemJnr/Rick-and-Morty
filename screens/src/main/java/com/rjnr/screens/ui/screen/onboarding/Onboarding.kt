package com.rjnr.screens.ui.screen.onboarding

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.NavigationRoot
import com.rjnr.navigation.Screen
import com.rjnr.navigation.navigation
import com.rjnr.screens.R
import com.rjnr.screens.ui.animateXCenterToLeft
import com.rjnr.screens.ui.lerp
import com.rjnr.screens.ui.springBounceSlow
import com.rjnr.screens.ui.toDensityDp
import com.rjnr.screens.ui.toDensityPx
import com.rjnr.screens.ui.wrapperContext
import kotlin.math.roundToInt

enum class ScreenState {
    SHOW_BUTTON,
    NO_SHOW
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB_MR2)
@Composable
fun OnboardingScreen(
    screen: Screen,
    modifier: Modifier = Modifier,
    screenState: ScreenState = ScreenState.NO_SHOW
) {

    val navigation = navigation()

    var showButton by remember { mutableStateOf(false) }

    var internalSwitch by remember { mutableStateOf(true) }

    val wrapper = wrapperContext()

    val transition = updateTransition(
        targetState = if (!internalSwitch) ScreenState.SHOW_BUTTON else screenState,
        label = "Splash"
    )

    val percentTransition by transition.animateFloat(
        transitionSpec = {
            springBounceSlow()
        },
        label = "percentTransition"
    ) {
        when (it) {
            ScreenState.NO_SHOW -> 0f
            else -> 1f
        }
    }

    val logoHeight by transition.animateDp(
        transitionSpec = {
            springBounceSlow()
        },
        label = "logoHeight"
    ) {
        when (it) {
            ScreenState.NO_SHOW -> 96.dp
            else -> 64.dp
        }
    }

    val logoWidth by transition.animateDp(
        transitionSpec = {
            springBounceSlow()
        },
        label = "logoWidth"
    ) {
        when (it) {
            ScreenState.NO_SHOW -> 113.dp
            else -> 76.dp
        }
    }

    val spacerTop by transition.animateDp(
        transitionSpec = {
            springBounceSlow()
        },
        label = "spacerTop"
    ) {
        when (it) {
            ScreenState.NO_SHOW -> {
                (wrapper.screenHeight / 2f - logoHeight.toDensityPx() / 2f).toDensityDp()
            }

            else -> 56.dp
        }
    }
    val marginTextTop by transition.animateDp(
        transitionSpec = {
            springBounceSlow()
        },
        label = "marginTextTop"
    ) {
        when (it) {
            ScreenState.NO_SHOW -> 64.dp
            else -> 40.dp
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(spacerTop))

        Row {

            Image(
                modifier = Modifier
                    .size(
                        width = logoWidth,
                        height = logoHeight
                    )
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        val xSplash = wrapper.screenWidth / 2f - placeable.width / 2
                        val xLogin = 24.dp.toPx()

                        layout(placeable.width, placeable.height) {
                            placeable.placeRelative(
                                x = lerp(xSplash, xLogin, percentTransition).roundToInt(),
                                y = 0,
                            )
                        }
                    }
                    .clickable {
                        internalSwitch = !internalSwitch
                    }
                    .padding(bottom = 78.dp, end = 12.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = R.drawable.rick),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Rick sanchez",

                )
            Spacer(Modifier.height(marginTextTop))
            Text(
                modifier = modifier
                    .animateXCenterToLeft(
                        percentTransition = percentTransition,
                        wrapper = wrapper
                    )
                    .padding(top = 72.dp),
                text = "&",
                style = MaterialTheme.typography.displaySmall,

                )

            Image(
                painter = painterResource(id = R.drawable.morty),
                contentDescription = "Morty",
                modifier = modifier
                    .padding(top = 78.dp, start = 8.dp)
                    .clip(shape = CircleShape)
                    .size(
                        width = logoWidth,
                        height = logoHeight
                    )
                    .clickable {
                        internalSwitch = !internalSwitch
                    }
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        val xSplash = wrapper.screenWidth / 2f - placeable.width / 2
                        val xLogin = 24.dp.toPx()

                        layout(placeable.width, placeable.height) {
                            placeable.placeRelative(
                                x = lerp(xSplash, xLogin, percentTransition).roundToInt(),
                                y = 0,
                            )
                        }
                    }
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


@Composable
private fun RickAndMorty(
    modifier: Modifier,
    navigation: Navigation
) {
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB_MR2)
@Preview(showBackground = true)
@Composable
fun OnboardPrev() {
    NavigationRoot(navigation = Navigation()) {
        if (it != null) {
            OnboardingScreen(screen = it)
        }
    }

}
