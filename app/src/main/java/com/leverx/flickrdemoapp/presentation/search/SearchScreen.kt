package com.leverx.flickrdemoapp.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.OutlinedButton
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
import com.leverx.search.models.SearchScreenUiState

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchScreenUiState,
    onPhotoClicked: (String) -> Unit,
    onRefresh: () -> Unit,
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
        if (uiState.error.isBlank()) {
            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                autoCentering = AutoCenteringParams(itemIndex = 0),
                state = listState
            ) {
                items(items = uiState.photoList, key = { it.id }) { photo ->
                    AsyncImage(
                        model = photo.url,
                        contentDescription = "${stringResource(com.leverx.search.R.string.photo_list_item)} ${photo.id}",
                        placeholder = painterResource(id = com.leverx.common.R.drawable.placeholder),
                        error = painterResource(id = com.leverx.common.R.drawable.error),
                        filterQuality = FilterQuality.Low,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillParentMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onPhotoClicked(photo.id)
                                println("Photo clicked ${photo.id}")
                            }
                    )
                }
            }
        } else {
            LoadingError(errorMessage = uiState.error, onRetryClick = onRefresh)
        }
    }
}

@Composable
fun LoadingError(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Warning,
            contentDescription = stringResource(com.leverx.search.R.string.loading_error_description),
            tint = MaterialTheme.colors.error,
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = errorMessage,
            color = MaterialTheme.colors.error,
            fontStyle = MaterialTheme.typography.body1.fontStyle,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(36.dp))
        OutlinedButton(onClick = onRetryClick) {
            Text(stringResource(com.leverx.search.R.string.retry_text))
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    val previewPhotoList = listOf(
        com.leverx.search.models.PhotoUiState(id = "101", url = "url1"),
        com.leverx.search.models.PhotoUiState(id = "102", url = "url2"),
        com.leverx.search.models.PhotoUiState(id = "103", url = "url3"),
        com.leverx.search.models.PhotoUiState(id = "104", url = "url4"),
        com.leverx.search.models.PhotoUiState(id = "105", url = "url5")
    )
    val uiState = SearchScreenUiState(photoList = previewPhotoList)
    SearchScreen(
        uiState = uiState,
        onPhotoClicked = {},
        onRefresh = {}
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun LoadingErrorPreview() {
    LoadingError(
        errorMessage = "Test error message!",
        onRetryClick = {}
    )
}