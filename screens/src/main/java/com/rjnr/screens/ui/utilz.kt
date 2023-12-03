package com.rjnr.screens.ui

import android.os.Build
import androidx.annotation.FloatRange
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rjnr.screens.ui.screen.composeExt.LocalRMContext
import com.rjnr.screens.ui.screen.composeExt.UiWrapper
import com.rjnr.screens.ui.screen.composeExt.UiWrapperImpl
import kotlin.math.roundToInt


@Composable
fun <T> densityScope(densityScope: @Composable Density.() -> T): T {
    return with(LocalDensity.current) { densityScope() }
}


@Composable
fun Float.toDensityDp() = densityScope { toDp() }

@Composable
fun Dp.toDensityPx() = densityScope { toPx() }


fun <T> springBounceSlow() = spring<T>(
    dampingRatio = 0.75f,
    stiffness = Spring.StiffnessVeryLow,
)

fun lerp(start: Int, end: Int, @FloatRange(from = 0.0, to = 1.0) fraction: Float): Int {
    return ((start + fraction * (end - start)).roundToInt())
}


fun lerp(start: Float, end: Float, @FloatRange(from = 0.0, to = 1.0) fraction: Float): Float {
    return (start + fraction * (end - start))
}

fun lerp(start: Double, end: Double, @FloatRange(from = 0.0, to = 1.0) fraction: Double): Double {
    return (start + fraction * (end - start))
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB_MR2)
 fun Modifier.animateXCenterToLeft(
    wrapper:UiWrapper,
    percentTransition: Float,
): Modifier {
    return this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        layout(placeable.width, placeable.height) {
            val xSplash = wrapper.screenWidth / 2f - placeable.width / 2
            val xLogin = 32.dp.toPx()

            placeable.placeRelative(
                x = lerp(xSplash, xLogin, percentTransition).roundToInt(),
                y = 0
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