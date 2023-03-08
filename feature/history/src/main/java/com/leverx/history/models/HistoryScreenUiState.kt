package com.leverx.history.models

data class HistoryScreenUiState(
    val isLoading: Boolean = false,
    val photoList: List<PhotoUiState> = emptyList(),
    val error: String = ""
)