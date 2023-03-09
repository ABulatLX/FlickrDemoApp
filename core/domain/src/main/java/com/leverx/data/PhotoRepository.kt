package com.leverx.data

import com.leverx.photo.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    suspend fun getPhotosBySearch(searchParam: String): Result<List<Photo>>

    //FAKE API
    suspend fun viewPhoto(photoId: String): Result<Photo>
    fun getPhotosHistoryFlow(): Flow<List<Photo>>
}