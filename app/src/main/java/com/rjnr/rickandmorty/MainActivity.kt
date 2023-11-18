package com.rjnr.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rjnr.design.ui.RickAndMortyTheme
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.NavigationRoot
import com.rjnr.navigation.navigation

class MainActivity : ComponentActivity() {

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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navigation.navigateTo(ListScreen)

                    NavigationRoot(navigation = navigation) { screen ->
                        NavGraph(screen = screen)
                    }

                }
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMortyTheme {
    }
}