package com.app.bitesmart.viewModels


import androidx.datastore.preferences.core.edit
import androidx.lifecycle.viewModelScope
import com.app.bitesmart.data.datastore.CONFIRM_PASSWORD_KEY
import com.app.bitesmart.data.datastore.EMAIL_KEY
import com.app.bitesmart.data.datastore.PASSWORD_KEY
import com.app.bitesmart.data.datastore.PHONE_NUMBER_KEY
import com.app.bitesmart.data.datastore.USERNAME_KEY
import com.app.bitesmart.data.datastore.userDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class UserViewModel(private val context: Context) : ViewModel() {

    // Function to save account details (username, email, phone number, etc.)
    fun saveAccountDetails(username: String, email: String, phoneNumber: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            context.userDataStore.edit { preferences ->
                preferences[USERNAME_KEY] = username
                preferences[EMAIL_KEY] = email
                preferences[PHONE_NUMBER_KEY] = phoneNumber
                preferences[PASSWORD_KEY] = password
                preferences[CONFIRM_PASSWORD_KEY] = confirmPassword
            }
        }
    }

    // Function to retrieve account details (username, email, phone number, etc.)
    suspend fun getAccountDetails(): AccountDetails {
        val preferences = context.userDataStore.data.first()
//        Log.d("DataStore", "Stored preferences: $preferences")
        return AccountDetails(
            username = preferences[USERNAME_KEY] ?: "No username",
            password = preferences[PASSWORD_KEY] ?: "No password",
            email = preferences[EMAIL_KEY] ?: "No email",
            phoneNumber = preferences[PHONE_NUMBER_KEY] ?: "No phone number",
            confirmPassword = preferences[CONFIRM_PASSWORD_KEY] ?: "No confirm password"
        )
    }
}

// Data class to represent the user account details
data class AccountDetails(
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val confirmPassword: String
)
