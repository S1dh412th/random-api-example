package com.example.bored_api.model

import com.google.gson.annotations.SerializedName


data class BoredResponse(

    @SerializedName("activity") var activity: String? = null,
    @SerializedName("accessibility") var accessibility: Double? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("participants") var participants: Int? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("link") var link: String? = null,
    @SerializedName("key") var key: String? = null

)