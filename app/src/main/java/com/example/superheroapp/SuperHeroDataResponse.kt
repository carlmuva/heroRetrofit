package com.example.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<ResultsItemResponse>
)


data class ResultsItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image:ImageResponse
)

data class ImageResponse(
    @SerializedName("url") val url: String
)
