package com.leverx.history.models

import com.leverx.photo.Photo

data class PhotoUiState(
    val id: String,
    val url: String,
    val views: Int = 0
)

fun Photo.toUiState() = PhotoUiState(
    id = this.id,
    url = this.url,
    views = this.views
)