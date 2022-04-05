package com.example.news_api.repository

import com.example.news_api.api.NewsApi
import com.example.news_api.model.NewsResponse

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository {
    override suspend fun getNewsTopHeadlines() : NewsResponse{
        return newsApi.getNewsTopHeadlines()
    }
}