package com.example.bored_api.api

import com.example.bored_api.model.BoredResponse
import retrofit2.http.GET

interface BoredApi {
    @GET("api/activity/")
    suspend fun getActivity(): BoredResponse
}