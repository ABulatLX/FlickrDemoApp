package com.leverx.data

import com.leverx.photo.Photo

interface PhotoRepository {

    suspend fun getPhotosBySearch(searchParam: String): Result<List<Photo>>
}