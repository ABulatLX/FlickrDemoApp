package com.leverx.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.leverx.data.PhotoRepository
import com.leverx.flickrNetwork.di.FlickrPhotoApi
import com.leverx.search.models.SearchScreenUiState
import com.leverx.search.models.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    @FlickrPhotoApi private val repository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenUiState(isLoading = true))
    val uiState: StateFlow<SearchScreenUiState> = _uiState

    init {
        viewModelScope.launch {
            getPhotosBySearch()
        }
    }

    fun getPhotosBySearch(searchQuery: String = "heidelberg") {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            repository.getPhotosBySearch(searchQuery)
                .fold(
                    onSuccess = { photoList ->
                        _uiState.value = _uiState.value.copy(
                            photoList = photoList.map { it.toUiState() },
                            isLoading = false,
                            error = ""
                        )
                    },
                    onFailure = { error ->
                        updateErrorState(error.localizedMessage ?: "")
                    }
                )
        }
    }

    private fun updateErrorState(errorMessage: String) {
        _uiState.value = _uiState.value.copy(error = errorMessage, isLoading = false)
    }

    class Factory @Inject constructor(
        @FlickrPhotoApi private val repository: PhotoRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return SearchViewModel(repository) as T
        }
    }
}