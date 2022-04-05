package com.example.news_api.repository

import com.example.news_api.model.NewsResponse

interface NewsRepository {
    suspend fun getNewsTopHeadlines() : NewsResponse
}