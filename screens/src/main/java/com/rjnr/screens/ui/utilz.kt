package com.rjnr.screens.ui

import android.os.Build
import androidx.annotation.FloatRange
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.rjnr.screens.ui.screen.composeExt.LocalRMContext
import com.rjnr.screens.ui.screen.composeExt.UiWrapper
import com.rjnr.screens.ui.screen.composeExt.UiWrapperImpl
import kotlin.math.roundToInt

// Composable function to execute a block of code within the current Density scope
@Composable
fun <T> densityScope(densityScope: @Composable Density.() -> T): T {
    // Use the current LocalDensity to provide the density scope and execute the given block
    return with(LocalDensity.current) { densityScope() }
}

// Extension function to convert a Float value to Density-independent
// Pixels (dp) within the current Density scope
@Composable
fun Float.toDensityDp() = densityScope { toDp() }

// Extension function to convert Dp value to Pixels within the current Density scope
@Composable
fun Dp.toDensityPx() = densityScope { toPx() }

// amination used for the shared element
fun <T> springBounceSlow() = spring<T>(
    dampingRatio = 0.75f,
    stiffness = Spring.StiffnessVeryLow,
)

fun lerp(start: Float, end: Float, @FloatRange(from = 0.0, to = 1.0) fraction: Float): Float {
    return (start + fraction * (end - start))
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB_MR2)
fun Modifier.animateXCenterToLeft(
    wrapper: UiWrapper,
    percentTransition: Float,
): Modifier {
    return this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        layout(placeable.width, placeable.height) {
            val xSplash = wrapper.screenWidth!! / 2f - placeable.width / 2
            val xLogin = 32.dp.toPx()

            placeable.placeRelative(
                x = lerp(xSplash, xLogin, percentTransition).roundToInt(),
                y = 0,
            )
        }
    }
}

@Composable
fun wrapperContext(): UiWrapper {
    return LocalRMContext.current
}

@Composable
fun wrapperCtx(): UiWrapperImpl = wrapperContext() as UiWrapperImpl

inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit,
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    ) {
        onClick()
    }
}

fun findContrastTextColor(backgroundColor: Color): Color =
    if (ColorUtils.calculateLuminance(backgroundColor.toArgb()) <= 0.5) White else Black
