package com.example.bored_api.repository

import com.example.bored_api.model.BoredResponse

interface BoredRepository {
    suspend fun getActivity(): BoredResponse
}