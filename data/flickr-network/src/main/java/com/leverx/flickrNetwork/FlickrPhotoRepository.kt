package com.leverx.flickrNetwork

import com.leverx.data.PhotoRepository
import com.leverx.flickrNetwork.models.toDomain
import com.leverx.photo.Photo
import javax.inject.Inject

internal class FlickrPhotoRepository @Inject constructor(
    private val dataSource: FlickrNetworkDataSource
) : PhotoRepository {

    override suspend fun getPhotosBySearch(searchParam: String): Result<List<Photo>> {
        return dataSource.getPhotosBySearch(searchParam)
            .map { dto ->
                dto.photosPage.photosList.map { it.toDomain() }
            }
    }
}