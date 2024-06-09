package com.rjnr.screens.ui.screen.onboarding

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rjnr.navigation.*
import com.rjnr.screens.R
import com.rjnr.screens.ui.*
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

enum class ScreenState {
    SHOW_BUTTON,
    NO_SHOW,
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB_MR2)
@Composable
fun OnboardingScreen(
    screen: Screen,
    modifier: Modifier = Modifier,
) {
    val navigation = navigation()
    var screenState: ScreenState by remember { mutableStateOf(ScreenState.NO_SHOW) }

    var switchBetweenState by remember { mutableStateOf(true) }

    val wrapper = wrapperCtx()

    val transition =
        updateTransition(
            targetState = if (!switchBetweenState) ScreenState.SHOW_BUTTON else screenState,
            label = "Splash",
        )

    val percentTransition by transition.animateFloat(
        transitionSpec = {
            springBounceSlow()
        },
        label = "percentTransition",
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
        label = "logoHeight",
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
        label = "logoWidth",
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
        label = "spacerTop",
    ) {
        when (it) {
            ScreenState.NO_SHOW -> {
                (wrapper.screenHeight!! / 2f - logoHeight.toDensityPx() / 2f).toDensityDp()
            }

            else -> 56.dp
        }
    }
    val marginTextTop by transition.animateDp(
        transitionSpec = {
            springBounceSlow()
        },
        label = "marginTextTop",
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
        modifier =
            modifier
                .fillMaxSize()
                .systemBarsPadding()
                .navigationBarsPadding(),
    ) {
        Column {
            Spacer(Modifier.height(spacerTop))
            Row(
                modifier =
                    modifier
                        .wrapContentSize()
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            val xSplash = (wrapper.screenWidth!! / 2f) - (placeable.width / 2)
                            val xLogin = 204.dp.toPx()

                            layout(placeable.width, placeable.height) {
                                placeable.placeRelative(
                                    x = lerp(start = xSplash, xLogin, percentTransition).roundToInt(),
                                    y = 0,
                                )
                            }
                        }
                        .noRippleClickable {
                            switchBetweenState = !switchBetweenState
                        },
            ) {
                Image(
                    modifier =
                        modifier
                            .padding(bottom = 78.dp, end = 12.dp)
                            .size(
                                width = logoWidth,
                                height = logoWidth,
                            )
                            .clip(CircleShape),
                    painter = painterResource(id = R.drawable.rick),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Rick sanchez",
                )
                Text(
                    modifier =
                        modifier
                            .padding(top = 72.dp),
                    text = "&",
                    style = MaterialTheme.typography.displaySmall,
                )
                Image(
                    modifier =
                        modifier
                            .padding(top = 78.dp, start = 8.dp)
                            .size(
                                width = logoWidth,
                                height = logoWidth,
                            )
                            .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.morty),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Morty",
                )
            }
            Spacer(Modifier.height(marginTextTop))
            Text(
                modifier =
                    modifier
                        .animateXCenterToLeft(
                            wrapper = wrapper,
                            percentTransition = percentTransition,
                        ),
                softWrap = true,
                text = "Welcome to the Rick and Morty Character App",
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
            )
        }
        NavButton(
            navigation = navigation,
            modifier = modifier,
            percentTransition = percentTransition,
        )
    }
}

@Composable
private fun NavButton(
    navigation: Navigation,
    modifier: Modifier,
    percentTransition: Float,
) {
    if (percentTransition > 0.02f) {
        Column(
            modifier =
                Modifier
                    .alpha(percentTransition),
        ) {
            Spacer(Modifier.weight(2f))
            Button(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                onClick = {
                    navigation.navigateTo(ListScreen)
                },
            ) {
                Text(
                    text = "Explore",
                    style = MaterialTheme.typography.bodyLarge,
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
