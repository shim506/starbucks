package com.example.starbucks.network.dto

import com.squareup.moshi.Json

data class HomeInfoDto(
    @Json(name = "display-name")
    val displayName: String,
    @Json(name = "main-event")
    val mainEvent: MainEvent,
    @Json(name = "now-recommand")
    val nowRecommend: NowRecommand,
    @Json(name = "your-recommand")
    val yourRecommend: YourRecommand
)

data class NowRecommand(
    val products: List<String>
)

data class MainEvent(
    val img_UPLOAD_PATH: String,
    val mob_THUM: String
)

data class YourRecommand(
    val products: List<String>
)