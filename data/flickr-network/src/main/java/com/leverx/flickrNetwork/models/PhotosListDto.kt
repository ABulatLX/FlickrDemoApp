package com.leverx.flickrNetwork.models

import com.google.gson.annotations.SerializedName

internal const val REQUEST_STAT_OK = "ok"

internal data class PhotosListDto(

    @SerializedName("photos")
    val photosPage: PhotosPageDto,

    @SerializedName("stat")
    val stat: String,

    @SerializedName("message")
    val message: String?,
)
