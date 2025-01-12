package com.app.bitesmart.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bitesmart.data.datastore.UserAllergiesDataStore
import kotlinx.coroutines.launch

class UserAllergiesViewModel(private val userAllergiesDataStore: UserAllergiesDataStore) : ViewModel() {

    val userAllergies = mutableStateOf<Set<String>>(emptySet())
    val allergyInput = mutableStateOf("")

    // Function to add allergy
    fun addAllergy() {
        val allergy = allergyInput.value.trim()
        if (allergy.isNotEmpty()) {
            viewModelScope.launch {
                userAllergiesDataStore.addUserAllergy(allergy)
                allergyInput.value = "" // Clear the input after adding
                fetchUserAllergies() // Fetch the updated list
            }
        }
    }

    // Function to fetch the current user allergies
    fun fetchUserAllergies() {
        viewModelScope.launch {
            userAllergiesDataStore.userAllergiesFlow.collect { allergies ->
                userAllergies.value = allergies
            }
        }
    }
}
