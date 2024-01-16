package com.rjnr.screens.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rjnr.design.ui.Theme
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.domain.Character
import com.rjnr.screens.ui.findContrastTextColor
import com.rjnr.screens.ui.noRippleClickable
import com.rjnr.screens.ui.screen.composeExt.onScreenStart

@Composable
fun ListScreen(screen: ListScreen) {
    val modifier = Modifier
    val viewModel: ListViewModel = viewModel()
    val page = viewModel.page.intValue
    val loading = viewModel.loading.value
    val character = viewModel.character.value

    val nav = navigation()
    onScreenStart {
        viewModel.start()
    }
    List(
        modifier = modifier,
        navigation = nav,
        viewModel = viewModel,
        page = page,
        loading = loading,
        character = character,
    )
}

@Composable
fun List(
    modifier: Modifier,
    navigation: Navigation,
    viewModel: ListViewModel,
    page: Int,
    loading: Boolean,
    character: List<Character>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn() {
            if (loading && character.isEmpty()) {
                item {
                    Loading()
                }
            } else {
                itemsIndexed(character) { index, item ->
                    viewModel.onChangeItemScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        viewModel.nextPage()
                    }
                    ListView(uiState = item, modifier = modifier) {
                        navigation.navigateTo(DetailScreen(index + 1))
                    }
                }
            }
        }
    }
}

@Composable
fun Loading() {
    CircularProgressIndicator()
}

@Composable
fun ListView(uiState: Character, modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .noRippleClickable {
                onClick()
            }
            .background(
                shape = RoundedCornerShape(12.dp),
                color = Theme.colors.secondary.copy(alpha = .2f),
            ),

    ) {
        AsyncImage(
            model = uiState.image,
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .height(70.dp)
                .width(70.dp)
                .clip(shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                .clipToBounds(),
        )

        Column(
            modifier = modifier.padding(start = 6.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = uiState.name,
                fontWeight = FontWeight.Bold,
                color = findContrastTextColor(Theme.colors.onBackground),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = Theme.typography.bodyLarge,
            )

            Text(
                text = uiState.gender,
                fontWeight = FontWeight.Bold,
                color = findContrastTextColor(Theme.colors.onBackground),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = Theme.typography.bodyLarge,
            )
            Text(
                text = uiState.status,
                fontWeight = FontWeight.Bold,
                color = findContrastTextColor(Theme.colors.onBackground),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = Theme.typography.bodyLarge,
            )
        }
    }
}
