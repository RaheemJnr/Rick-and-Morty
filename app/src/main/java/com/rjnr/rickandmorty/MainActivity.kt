package com.rjnr.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rjnr.design.ui.RickAndMortyTheme
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.NavigationRoot
import com.rjnr.navigation.OnboardingScreen
import com.rjnr.screens.ui.screen.composeExt.IScreenWrapper
import com.rjnr.screens.ui.screen.composeExt.RMUIWrapper
import com.rjnr.screens.ui.screen.composeExt.ScreenWrapper
import com.rjnr.screens.ui.screen.composeExt.UiWrapper
import com.rjnr.screens.ui.screen.composeExt.UiWrapperImpl

class MainActivity : ComponentActivity() {

    val wrapper: UiWrapper = UiWrapperImpl()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navigation = Navigation()

            val viewModel: MainViewModel = viewModel()

            LaunchedEffect(key1 = Unit) {
                viewModel.start()
            }
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                RMUIWrapper(screenWrapper = createScreenWrapper(context = wrapper)) {
                    navigation.navigateTo(OnboardingScreen)

                    NavigationRoot(navigation = navigation) { screen ->
                        NavGraph(screen = screen)
                    }
                }


            }
        }
    }
}

// Function to create an IScreenWrapper with a given UiWrapper context
fun createScreenWrapper(context: UiWrapper): IScreenWrapper = object : ScreenWrapper() {
    override fun uiWrapper(): UiWrapper = context
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMortyTheme {
    }
}