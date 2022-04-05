package com.example.bored_api

import com.example.bored_api.api.BoredApi
import com.example.bored_api.repository.BoredRepository
import com.example.bored_api.repository.BoredRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instance {
    private const val BASE_URL = "http://www.boredapi.com/"

    fun getBoredRepository(): BoredRepository {
        val okHttpClient = OkHttpClient.Builder().build()

        val converterFactory = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder().addConverterFactory(converterFactory).baseUrl(BASE_URL).client(okHttpClient).build()

        val newsApi = retrofit.create(BoredApi::class.java)

        return BoredRepositoryImpl(newsApi)
    }
}