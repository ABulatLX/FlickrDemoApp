package com.leverx.flickrNetwork

import com.leverx.flickrNetwork.models.PhotosListDto
import com.leverx.flickrNetwork.models.REQUEST_STAT_OK
import javax.inject.Inject

internal interface FlickrNetworkDataSource {

    suspend fun getPhotosBySearch(searchParam: String): Result<PhotosListDto>
}

internal class FlickrNetworkDataSourceImpl @Inject constructor(
    private val flickrApi: FlickrApi
) : FlickrNetworkDataSource {

    override suspend fun getPhotosBySearch(searchParam: String): Result<PhotosListDto> {
        return executeCatching {
            flickrApi.getPhotosBySearch(searchParam)
        }
    }

    private inline fun <T> T.executeCatching(block: T.() -> PhotosListDto): Result<PhotosListDto> {
        return try {
            val result = block()
            if (result.stat == REQUEST_STAT_OK) {
                Result.success(result)
            } else {
                Result.failure(IllegalArgumentException(result.message))
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}
