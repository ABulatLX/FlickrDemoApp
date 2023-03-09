package com.leverx.flickrNetwork

import com.leverx.flickrNetwork.models.PhotoDto
import com.leverx.flickrNetwork.models.PhotosListDto
import com.leverx.flickrNetwork.models.REQUEST_STAT_OK
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal interface FlickrNetworkDataSource {

    suspend fun getPhotosBySearch(searchParam: String): Result<PhotosListDto>

    //FAKE API
    suspend fun viewPhoto(photoId: String): Result<PhotoDto>
    fun getPhotosHistoryFlow(): Flow<List<PhotoDto>>
}

internal class FlickrNetworkDataSourceImpl @Inject constructor(
    private val flickrApi: FlickrApi
) : FlickrNetworkDataSource {

    override suspend fun getPhotosBySearch(searchParam: String): Result<PhotosListDto> {
        return executeCatching {
            flickrApi.getPhotosBySearch(searchParam)
        }.also { result ->      //TODO remove also block after fake api removal
            result.getOrNull()?.let { viewedPhotos.addAll(it.photosPage.photosList) }
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

    //FAKE API
    private val viewedPhotos = mutableListOf<PhotoDto>()
    private val viewedPhotosHistory = mutableListOf<PhotoDto>()
    private val viewedPhotosHistoryFlow = MutableSharedFlow<List<PhotoDto>>(replay = 1)
    override suspend fun viewPhoto(photoId: String): Result<PhotoDto> {
        return runCatching {
            val viewedPhoto = viewedPhotosHistory.find { it.id == photoId }
                ?: viewedPhotos.find { it.id == photoId }!!
            val updatedViewedPhoto = viewedPhoto.copy(views = viewedPhoto.views + 1)
            viewedPhotosHistory.remove(viewedPhoto)
            viewedPhotosHistory.add(updatedViewedPhoto)
            viewedPhotosHistoryFlow.emit(viewedPhotosHistory.reversed())
            updatedViewedPhoto
        }.also {
            println(it.getOrNull())
        }
    }

    override fun getPhotosHistoryFlow(): Flow<List<PhotoDto>> {
        return viewedPhotosHistoryFlow
    }
}
