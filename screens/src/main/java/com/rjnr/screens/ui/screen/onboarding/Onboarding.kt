package com.rjnr.screens.ui.screen.onboarding

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.rjnr.screens.ui.noRippleClickable
import com.rjnr.screens.ui.springBounceSlow
import com.rjnr.screens.ui.toDensityDp
import com.rjnr.screens.ui.toDensityPx
import com.rjnr.screens.ui.wrapperCtx
import kotlinx.coroutines.delay
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

    ) {
    val navigation = navigation()
    var screenState:ScreenState by remember { mutableStateOf(ScreenState.NO_SHOW) }

    var switchBetweenState by remember { mutableStateOf(true) }

    val wrapper = wrapperCtx()

    val transition = updateTransition(
        targetState = if (!switchBetweenState) ScreenState.SHOW_BUTTON else screenState,
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

    LaunchedEffect(key1 = screenState) {
        if (screenState == ScreenState.NO_SHOW) {
            delay(1000)
            screenState = ScreenState.SHOW_BUTTON
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding(),
    ) {
        Column(

        ) {
            Spacer(Modifier.height(spacerTop))
            Row(modifier = modifier
                .wrapContentSize()
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    val xSplash = (wrapper.screenWidth / 2f) - (placeable.width / 2)
                    val xLogin = 24.dp.toPx()

                    layout(placeable.width, placeable.height) {
                        placeable.placeRelative(
                            x = lerp(start = xSplash, xLogin, percentTransition).roundToInt(),
                            y = 0,
                        )
                    }
                }
                .noRippleClickable {
                    switchBetweenState = !switchBetweenState
                }
            ) {
                Image(
                    modifier = modifier
                        .padding(bottom = 78.dp, end = 12.dp)
                        .size(
                            width = logoWidth,
                            height = logoHeight
                        )
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.rick),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Rick sanchez",

                    )
                Text(
                    modifier = modifier
                        .padding(top = 72.dp),
                    text = "&",
                    style = MaterialTheme.typography.displaySmall,

                    )
                Image(
                    modifier = modifier
                        .padding(top = 78.dp, start = 8.dp)
                        .size(
                            width = logoWidth,
                            height = logoHeight
                        )
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.morty),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Morty"
                )
            }
            Spacer(Modifier.height(marginTextTop))
            val horizontalPadding = if (percentTransition > 0) 58.dp else 8.dp
            Text(
                modifier = modifier
                    .animateXCenterToLeft(
                        wrapper = wrapper,
                        percentTransition = percentTransition
                    ),
                softWrap = true,
                text = "Welcome to the Rick and Morty Character App",
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
            )

        }
        LoginSection(
            navigation = navigation,
            modifier = modifier,
            percentTransition = percentTransition
        )
    }

}


@Composable
private fun LoginSection(
    navigation: Navigation,
    modifier: Modifier,
    percentTransition: Float,
) {
    if (percentTransition > 0.02f) {
        Column(
            modifier = Modifier
                .alpha(percentTransition),
        ) {
            Spacer(Modifier.weight(2f))
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                onClick = {
                    navigation.navigateTo(ListScreen)
                }
            ) {
                Text(
                    text = "Move to next screen",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(Modifier.weight(3f))
            Spacer(Modifier.height(16.dp))
        }
    }
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
