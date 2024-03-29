package com.example.testing

import com.example.testing.remotedatasource.RemoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DependencyInjection {

    @Provides
    @Singleton
    fun provideRetrofit(): RemoteApi {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
    }


}