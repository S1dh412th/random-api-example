package com.example.news_api.api

import com.example.news_api.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNewsTopHeadlines(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = "2394d5b6f3fa4d62a03c7d5009f0a080"
    ): NewsResponse
}