package com.lejik.catsapi.network

import com.squareup.moshi.Json

data class CatsPhoto {
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
}