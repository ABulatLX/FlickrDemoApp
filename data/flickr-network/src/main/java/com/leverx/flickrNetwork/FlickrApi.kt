package com.leverx.flickrNetwork

import com.leverx.flickrNetwork.models.PhotosListDto
import retrofit2.http.GET
import retrofit2.http.Query

internal const val API_KEY_NAME = "api_key"

internal interface FlickrApi {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&safe_search=1")
    suspend fun getPhotosBySearch(@Query("text") searchParam: String): PhotosListDto
}