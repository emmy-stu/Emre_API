package com.example.emre_api.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emre_api.data.LanguageEntity
import com.example.emre_api.network.RestfulApiDevService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Purpose:
// - Handles business logic for the Dashboard
// - Fetches language data using the API and stores it in a MutableStateFlow
// - Uses Hilt for dependency injection

// Let Hilt know that this file will be Injected with dependencies
@HiltViewModel

// CLASS: LoginViewModel - DEPENDENCY INJECTION
class DashboardViewModel @Inject constructor(

    // DEPENDENCY INJECTIONS:

    // - apiService: Injected for API calls <-- NetworkModule
    private val apiService: RestfulApiDevService,

    // - appContext: Injected for accessing shared preferences <-- Built in
    @ApplicationContext private val appContext: Context

) : ViewModel() {

    // MutableStateFlow to hold language entities -> values can change
    // Starts of as empty list
    private val _languageEntities = MutableStateFlow<List<LanguageEntity>>(emptyList())
    val languageEntity: StateFlow<List<LanguageEntity>> = _languageEntities

    // Fetch language  data from API
    fun fetchLanguage() {

        // Coroutine
        viewModelScope.launch {
            try {
                val sharedPreferences = appContext.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                val keypass = sharedPreferences.getString("keypass", "")

                if (!keypass.isNullOrEmpty()) {
                    val apiResponse = apiService.getLanguage(keypass)
                    _languageEntities.value = apiResponse.entities
                }

            } // Handle errors here if needed
            catch (e: Exception) {

            }
        } // End of Coroutine

    } // End of fetchLanguage()

} // Class