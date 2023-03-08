package com.leverx.flickrdemoapp.presentation.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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
        ScalingLazyColumn(
            modifier = modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 1),
            state = listState
        ) {
            items(items = uiState.photoList, key = { it.id }) { item ->
                AsyncImage(
                    model = item.url,
                    contentDescription = null,
//                    contentDescription = "${stringResource(com.leverx.search.R.string.photo_list_item)} ${item.id}",
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

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Text("Preview Android")
}