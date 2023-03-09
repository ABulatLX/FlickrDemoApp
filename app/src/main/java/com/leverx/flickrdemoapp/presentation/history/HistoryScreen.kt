package com.leverx.flickrdemoapp.presentation.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.items
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.scrollAway
import coil.compose.AsyncImage
import com.leverx.flickrdemoapp.R
import com.leverx.history.models.HistoryScreenUiState

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    uiState: HistoryScreenUiState
) {
    val listState = rememberScalingLazyListState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        timeText = {
            TimeText(modifier = Modifier.scrollAway(listState))
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        }
    ) {
        if (uiState.photoList.isEmpty()) {
            MessageView(message = stringResource(id = R.string.no_photos))
        } else if (uiState.error.isNotBlank()) {
            MessageView(message = uiState.error)
        } else {
            ScalingLazyColumn(
                modifier = modifier.fillMaxSize(),
                autoCentering = AutoCenteringParams(itemIndex = 0),
                state = listState
            ) {
                items(items = uiState.photoList, key = { it.id }) { item ->
                    AsyncImage(
                        model = item.url,
                        contentDescription = "${stringResource(com.leverx.search.R.string.photo_list_item)} ${item.id}",
                        placeholder = painterResource(id = com.leverx.common.R.drawable.placeholder),
                        error = painterResource(id = com.leverx.common.R.drawable.error),
                        filterQuality = FilterQuality.Low,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                println("Photo clicked ${item.id}")
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun MessageView(
    modifier: Modifier = Modifier,
    message: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message)
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun HistoryScreenPreview() {
    val uiState = HistoryScreenUiState(
        photoList = listOf(
            com.leverx.history.models.PhotoUiState("101", "url1", 1),
            com.leverx.history.models.PhotoUiState("102", "url2", 2),
            com.leverx.history.models.PhotoUiState("103", "url3", 3),
            com.leverx.history.models.PhotoUiState("104", "url4", 4),
            com.leverx.history.models.PhotoUiState("105", "url5", 5)
        )
    )
    HistoryScreen(uiState = uiState)
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun MessagePreview() {
    MessageView(message = "Preview message")
}