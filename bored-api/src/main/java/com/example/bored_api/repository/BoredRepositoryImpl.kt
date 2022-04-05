package com.example.bored_api.repository

import com.example.bored_api.api.BoredApi
import com.example.bored_api.model.BoredResponse

class BoredRepositoryImpl(
    private val boredApi: BoredApi
): BoredRepository {
    override suspend fun getActivity(): BoredResponse {
        return boredApi.getActivity()
    }
}