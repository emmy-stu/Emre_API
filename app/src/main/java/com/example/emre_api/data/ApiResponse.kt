package com.example.emre_api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Generates Moshi Adapter to convert JSON to Kotlin objects
@JsonClass(generateAdapter = true)

// API response of how manny items in the list
data class ApiResponse(
    // List of the objects from  <LanguageEntity>
    @Json(name = "entities") val entities: List<LanguageEntity>,
    // Integer: total entity
    @Json(name = "entityTotal") val entityTotal: Int
)