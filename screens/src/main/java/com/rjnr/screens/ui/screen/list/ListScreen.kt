package com.rjnr.screens.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
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
import com.rjnr.screens.ui.noRippleClickable

@Composable
fun ListScreen(screen: ListScreen) {
    val modifier = Modifier
    val viewModel: ListViewModel = viewModel()
    val uiState: ListState = viewModel.uiState()

    val nav = navigation()
    List(
        modifier = modifier,
        navigation = nav,
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )
}

@Composable
fun List(
    modifier: Modifier,
    navigation: Navigation,
    uiState: ListState,
    onEvent: (ListEvent) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn {
            if (uiState.loading && uiState.character.isEmpty()) {
                item {
                    Loading()
                }
            } else {
                itemsIndexed(uiState.character) { index, item ->
                    onEvent(ListEvent.OnChangeItemScrollPosition(index))
                    if ((index + 1) >= (uiState.page * PAGE_SIZE) && !uiState.loading) {
                        onEvent(ListEvent.NextPage)
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
fun ListView(
    uiState: Character,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier =
            modifier
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
            modifier =
                modifier
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
                color = contentColorFor(backgroundColor = Theme.colors.onBackground),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = Theme.typography.bodyLarge,
            )

            Text(
                text = uiState.gender,
                fontWeight = FontWeight.Bold,
                color = contentColorFor(backgroundColor = Theme.colors.onBackground),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = Theme.typography.bodyLarge,
            )
            Text(
                text = uiState.status,
                fontWeight = FontWeight.Bold,
                color = contentColorFor(backgroundColor = Theme.colors.background),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = Theme.typography.bodyLarge,
            )
        }
    }
}
