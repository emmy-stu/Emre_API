package com.example.emre_api.di

//import com.example.emre_api.data.LoginRequest
//import com.example.emre_api.data.LoginResponse
import com.example.emre_api.network.RestfulApiDevService
import com.example.emre_api.network.RestfulApiDevRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Purpose:
// - Handles API calls
// - Function to call the login API taking username and password
// - Calling the RestfulApiDevService Interface to make network request
// - Connect ViewModel and API service

// Informs dagger this is a Dagger Module for Dependency Injection
@Module
// Module's Singleton making it available throughout the app
@InstallIn(SingletonComponent::class)

// OBJECT: NetworkModule
object NetworkModule {
    // Inform hilt, this function will add Dependency
    @Provides
    // One API is shared
    @Singleton
    // function to call for API
    fun provideApiService(): RestfulApiDevService {
        // Provide RestfulApiDevService automatically via Hilt
        return RestfulApiDevRetrofitClient.apiService
    }
}
