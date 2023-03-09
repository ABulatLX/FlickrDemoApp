package com.leverx.flickrNetwork

import com.leverx.data.PhotoRepository
import com.leverx.flickrNetwork.models.toDomain
import com.leverx.photo.Photo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FlickrPhotoRepository @Inject constructor(
    private val dataSource: FlickrNetworkDataSource
) : PhotoRepository {

    override suspend fun getPhotosBySearch(searchParam: String): Result<List<Photo>> {
        return dataSource.getPhotosBySearch(searchParam)
            .map { dto ->
                dto.photosPage.photosList.map { it.toDomain() }
            }
    }

    //FAKE API
    override suspend fun viewPhoto(photoId: String): Result<Photo> {
        return dataSource.viewPhoto(photoId)
            .map { it.toDomain() }
    }

    override fun getPhotosHistoryFlow(): Flow<List<Photo>> {
        return dataSource.getPhotosHistoryFlow()
            .map { dtos ->
                dtos.map { it.toDomain() }
            }
    }
}