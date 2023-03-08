package com.leverx.flickrNetwork.models

import com.leverx.photo.Photo
import com.google.gson.annotations.SerializedName

internal data class PhotoDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("owner")
    val owner: String,

    @SerializedName("secret")
    val secret: String,

    @SerializedName("server")
    val server: String,

    @SerializedName("farm")
    val farm: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("ispublic")
    val isPublic: Int,

    @SerializedName("isfriend")
    val isFriend: Int,

    @SerializedName("isfamily")
    val isFamily: Int,

    val views: Int
) {
    fun extractImageUrl(): String {
        return "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
    }
}

internal fun PhotoDto.toDomain() = Photo(
    id = this.id,
    title = this.title,
    url = this.extractImageUrl(),
    author = this.owner,
    views = this.views
)
