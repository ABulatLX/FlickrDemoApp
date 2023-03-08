/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.leverx.flickrdemoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.leverx.flickrdemoapp.presentation.search.SearchScreen
import com.leverx.flickrdemoapp.presentation.theme.FlickrDemoAppTheme
import com.leverx.navigation.AppDestination
import com.leverx.search.SearchViewModel
import com.leverx.search.models.SearchScreenUiState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var searchViewModelFactory: SearchViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrDemoAppTheme {
                val navController = rememberSwipeDismissableNavController()

                SwipeDismissableNavHost(
                    navController = navController,
                    startDestination = AppDestination.Search.route
                ) {
                    composable(AppDestination.Search.route) {
                        val searchViewModel = viewModel<SearchViewModel>(
                            factory = searchViewModelFactory
                        )
                        val uiState: SearchScreenUiState by searchViewModel.uiState.collectAsState()
                        SearchScreen(
                            uiState = uiState,
                            onPhotoClicked = {}
                        )
                    }
                    composable(AppDestination.History.route) {
                        Text("History Screen")
                    }
                }
            }
        }
    }
}