package com.rjnr.screens.ui.screen.detail

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.domain.Character
import com.rjnr.screens.ui.screen.composeExt.onScreenStart
import com.rjnr.screens.ui.screen.viewModel.BaseViewModel

@Composable
fun DetailScreen(modifier: Modifier = Modifier, screen: DetailScreen) {
    val viewModel: BaseViewModel = viewModel()
    Log.i("detailScreen", "screen number: ${screen.id}")
    val navigation = navigation()
<<<<<<< HEAD:screens/src/main/java/com/rjnr/screens/ui/screen/detailScreen/DetailScreen.kt
    onScreenStart {
        viewModel.getCharacterDetails(screen.id)
=======
    Details(modifier = modifier, navigation)

    BackHandler {
        navigation.onBackPressed()
>>>>>>> master:screens/src/main/java/com/rjnr/screens/ui/screen/detail/DetailScreen.kt
    }

<<<<<<< HEAD:screens/src/main/java/com/rjnr/screens/ui/screen/detailScreen/DetailScreen.kt
    val details by viewModel.singleCharacter
    Log.i("detailScreen", "screen number: $details")
=======
@Composable
private fun Details(
    modifier: Modifier,
    navigation: Navigation,
>>>>>>> master:screens/src/main/java/com/rjnr/screens/ui/screen/detail/DetailScreen.kt

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
<<<<<<< HEAD:screens/src/main/java/com/rjnr/screens/ui/screen/detailScreen/DetailScreen.kt
            text = details.name,
        )
        Text(
            text = "${details.id}",
        )
        Text(
            text = details.gender,
        )
        Text(
            text = details.species,
        )
        Button(
            onClick = {
                viewModel.getCharacterDetails(screen.id)
=======
            text = "Hi, this is the details screen",
        )
        Button(
            onClick = {
                navigation.onBackPressed()
>>>>>>> master:screens/src/main/java/com/rjnr/screens/ui/screen/detail/DetailScreen.kt
            },
        ) {
            Text(
                text = "Move to List screen",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
<<<<<<< HEAD:screens/src/main/java/com/rjnr/screens/ui/screen/detailScreen/DetailScreen.kt

    BackHandler {
        navigation.onBackPressed()
    }
}

@Composable
private fun Details(
    modifier: Modifier,
    navigation: Navigation,
    character: Character,

) {
=======
>>>>>>> master:screens/src/main/java/com/rjnr/screens/ui/screen/detail/DetailScreen.kt
}
