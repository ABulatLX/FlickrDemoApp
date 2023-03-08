package com.leverx.flickrNetwork.di

import com.leverx.flickrNetwork.API_KEY_NAME
import com.leverx.flickrNetwork.FlickrApi
import com.leverx.flickrNetwork.data.FlickrDataProvider
import com.leverx.flickr_network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {

    @Provides
    @Singleton
    fun provideFlickrApi(okHttpClient: OkHttpClient, data: FlickrDataProvider): FlickrApi {
        return Retrofit.Builder()
            .baseUrl(data.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickrApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(data: FlickrDataProvider): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter(API_KEY_NAME, data.apiKey)
                    .build()

                val resultRequest = request.newBuilder()
                    .url(url)
                    .build()

                chain.proceed(resultRequest)
            }.build()
    }

    @Provides
    @Singleton
    fun provideFlickrData(): FlickrDataProvider {
        return FlickrDataProvider(
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.API_KEY
        )
    }
}