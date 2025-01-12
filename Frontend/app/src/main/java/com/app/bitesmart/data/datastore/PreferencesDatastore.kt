package com.app.bitesmart.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// predefined
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "allergies")
private val ALLERGIES_KEY = stringSetPreferencesKey("allergies_key")
val allergiesList = setOf(
    "Shellfish", "Fish", "Phenylketonuria (PKU)", "Legume", "Red 40", "Diabetes",
    "Gluten", "Sulphites", "Barley", "Soy", "Tree Nut", "Peanuts", "Eggs", "Asthma",
    "Preservatives", "Cancer", "Tree Nuts", "Sesame", "Sulphite Sensitivity", "Dairy",
    "Obesity", "Wheat", "Potato", "Macadamia Nut", "Peanut", "Yellow 5", "Shellac",
    "Sulfite Sensitivity", "Corn", "Heart Problems", "Milk", "Nuts", "Almonds", "Non Veg",
    "Pollen", "Honey", "Asthma"
)

class PreferencesDataStore(private val context: Context) {
    // Function to store the predefined allergies list
    suspend fun storeAllergies() {
        context.dataStore.edit { preferences ->
            preferences[ALLERGIES_KEY] = allergiesList
        }
    }
    // Flow to get allergies from DataStore
    val allergiesFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[ALLERGIES_KEY] ?: emptySet() // Return empty set if allergies are not found
        }
}

//user defined
val Context.userAllergiesDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_allergies")
private val USER_ALLERGIES_KEY = stringSetPreferencesKey("user_allergies_key")
class UserAllergiesDataStore(private val context: Context) {

    suspend fun storeUserAllergies(userAllergies: Set<String>) {
        context.userAllergiesDataStore.edit { preferences ->
            preferences[USER_ALLERGIES_KEY] = userAllergies
        }
    }
    suspend fun addUserAllergy(allergy: String) {
        context.userAllergiesDataStore.edit { preferences ->
            val currentAllergies = preferences[USER_ALLERGIES_KEY] ?: emptySet()
            preferences[USER_ALLERGIES_KEY] = currentAllergies + allergy // Adds the allergy to the set
        }
    }

    suspend fun removeUserAllergy(allergy: String) {
        context.userAllergiesDataStore.edit { preferences ->
            val currentAllergies = preferences[USER_ALLERGIES_KEY] ?: emptySet()
            preferences[USER_ALLERGIES_KEY] = currentAllergies - allergy // Removes the allergy from the set
        }
    }

    val userAllergiesFlow: Flow<Set<String>> = context.userAllergiesDataStore.data
        .map { preferences ->
            preferences[USER_ALLERGIES_KEY] ?: emptySet() // Return empty set if no allergies are found
        }
}

//login
val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

val USERNAME_KEY = stringPreferencesKey("username_key")
val EMAIL_KEY = stringPreferencesKey("email_key")
val PHONE_NUMBER_KEY = stringPreferencesKey("phone_number_key")
val PASSWORD_KEY = stringPreferencesKey("password_key")
val CONFIRM_PASSWORD_KEY = stringPreferencesKey("confirm_password_key")