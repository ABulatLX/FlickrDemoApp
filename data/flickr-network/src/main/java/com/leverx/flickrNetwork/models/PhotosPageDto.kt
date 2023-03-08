package com.leverx.flickrNetwork.models

import com.google.gson.annotations.SerializedName

internal data class PhotosPageDto(

    @SerializedName("page")
    val page: Int,

    @SerializedName("pages")
    val pages: Int,

    @SerializedName("perpage")
    val perPage: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("photo")
    val photosList: List<PhotoDto>
)
