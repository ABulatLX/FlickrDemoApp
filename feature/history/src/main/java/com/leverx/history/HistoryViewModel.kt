package com.leverx.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.leverx.data.PhotoRepository
import com.leverx.flickrNetwork.di.FlickrPhotoApi
import com.leverx.history.models.HistoryScreenUiState
import com.leverx.history.models.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryViewModel @Inject constructor(
    @FlickrPhotoApi private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryScreenUiState(isLoading = true))
    val uiState: StateFlow<HistoryScreenUiState> = _uiState

    init {
        viewModelScope.launch {
            photoRepository.getPhotosHistoryFlow()
                .collectLatest { photoList ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        photoList = photoList.map { it.toUiState() }
                    )
                }
        }
    }

    suspend fun getPhotosHistoryByViews() {
        photoRepository.getPhotosHistoryByViews()
            .fold(
                onSuccess = { photoList ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        photoList = photoList.map { it.toUiState() }
                    )
                },
                onFailure = { error ->
                    updateErrorState(error.localizedMessage ?: "")
                }
            )
    }

    private fun updateErrorState(errorMessage: String) {
        _uiState.value = _uiState.value.copy(error = errorMessage, isLoading = false)
    }

    class Factory @Inject constructor(
        @FlickrPhotoApi private val repository: PhotoRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return HistoryViewModel(repository) as T
        }
    }
}