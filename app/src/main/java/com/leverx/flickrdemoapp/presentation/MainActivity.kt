/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.leverx.flickrdemoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.leverx.flickrdemoapp.presentation.history.HistoryScreen
import com.leverx.flickrdemoapp.presentation.search.SearchScreen
import com.leverx.flickrdemoapp.presentation.theme.FlickrDemoAppTheme
import com.leverx.history.HistoryViewModel
import com.leverx.history.models.HistoryScreenUiState
import com.leverx.search.SearchViewModel
import com.leverx.search.models.SearchScreenUiState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var searchViewModelFactory: SearchViewModel.Factory

    @Inject
    lateinit var historyViewModelFactory: HistoryViewModel.Factory
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrDemoAppTheme {
                MainScreen()
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun MainScreen() {
        val swipeToDismissState = rememberSwipeToDismissBoxState()
        val pagerState = rememberPagerState()

        SwipeToDismissBox(
            modifier = Modifier.fillMaxSize(),
            state = swipeToDismissState
        ) {
            val searchViewModel = remember(searchViewModelFactory) {
                viewModels<SearchViewModel> { searchViewModelFactory }
            }
            val searchUiState: SearchScreenUiState by searchViewModel.value.uiState.collectAsState()

            val historyViewModel = remember(historyViewModelFactory) {
                viewModels<HistoryViewModel> { historyViewModelFactory }
            }
            val historyUiState: HistoryScreenUiState by historyViewModel.value.uiState.collectAsState()

            Box(
                contentAlignment = Alignment.Center
            ) {
                HorizontalPager(
                    state = pagerState,
                    count = MainPage.values().size
                ) { pageId ->
                    when (MainPage.values()[pageId]) {
                        MainPage.Search -> {
                            SearchScreen(
                                uiState = searchUiState,
                                onPhotoClicked = searchViewModel.value::viewPhoto
                            )
                        }
                        MainPage.History -> {
                            HistoryScreen(
                                uiState = historyUiState
                            )
                        }
                    }
                }

                HorizontalPagerIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 4.dp),
                    pagerState = pagerState,
                    activeColor = MaterialTheme.colors.primary,
                    inactiveColor = MaterialTheme.colors.primaryVariant,
                    spacing = 4.dp
                )
            }
        }
    }
}