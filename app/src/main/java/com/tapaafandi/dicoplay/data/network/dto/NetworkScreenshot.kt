package com.tapaafandi.dicoplay.data.network.dto


import com.squareup.moshi.Json

data class NetworkScreenshot(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "image")
    val image: String
)