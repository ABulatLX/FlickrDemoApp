package com.leverx.search.models

import androidx.compose.runtime.Immutable

@Immutable
data class SearchScreenUiState(
    val isLoading: Boolean = false,
    val photoList: List<PhotoUiState> = emptyList(),
    val error: String = ""
)