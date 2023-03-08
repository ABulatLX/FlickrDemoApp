package com.leverx.flickrNetwork.di

import com.leverx.data.PhotoRepository
import com.leverx.flickrNetwork.FlickrNetworkDataSource
import com.leverx.flickrNetwork.FlickrNetworkDataSourceImpl
import com.leverx.flickrNetwork.FlickrPhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface BindingModule {

    @Binds
    @Singleton
    fun bindFlickrNetworkDataSource(dataSource: FlickrNetworkDataSourceImpl): FlickrNetworkDataSource

    @Binds
    @Singleton
    @FlickrPhotoApi
    fun bindPhotoRepository(repository: FlickrPhotoRepository): PhotoRepository
}