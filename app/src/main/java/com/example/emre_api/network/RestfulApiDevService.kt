// RestfulApiDevService.kt
package com.example.emre_api.network

import com.example.emre_api.data.ApiResponse
import com.example.emre_api.data.LoginRequest
import com.example.emre_api.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// Purpose:
// - Contains the 2 API Endpoints: @POST("footscray/auth") & @GET("dashboard/languages")
interface RestfulApiDevService {

    // POST
    // This is for login
    @POST("footscray/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    // GET
    // This is to get the languages data
    @GET("dashboard/languages")
    suspend fun getLanguage(
        @Header("Authorization") keypass: String
    ): ApiResponse
    // Includes the keypass, and it returns ApiResponse
}


