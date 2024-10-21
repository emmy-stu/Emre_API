package com.example.emre_api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Generates Moshi Adapter to convert JSON to Kotlin objects
@JsonClass(generateAdapter = true)

// Entities list receive from the API keypass
// They're all String except for year
data class LanguageEntity(
    @Json(name = "name") val name: String,
    @Json(name = "family") val family: String,
    @Json(name = "branch") val branch: String,
    @Json(name = "speakers") val speakers: Int,
    @Json(name = "writingSystem") val writingSystem: String,
    @Json(name = "officialIn") val officialIn: List<String>,
    @Json(name = "description") val description: String

)