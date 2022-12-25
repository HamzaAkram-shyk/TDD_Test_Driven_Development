package com.example.testing.remotedatasource

import retrofit2.http.GET

interface RemoteApi {

    @GET("")
    suspend fun getData()
}