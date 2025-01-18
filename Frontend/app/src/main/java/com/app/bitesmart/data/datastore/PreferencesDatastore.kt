package com.app.bitesmart.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



//login
val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

val USERNAME_KEY = stringPreferencesKey("username_key")
val EMAIL_KEY = stringPreferencesKey("email_key")
val PHONE_NUMBER_KEY = stringPreferencesKey("phone_number_key")
val PASSWORD_KEY = stringPreferencesKey("password_key")
val CONFIRM_PASSWORD_KEY = stringPreferencesKey("confirm_password_key")

fun allergiesKeyForUser(username: String) = stringPreferencesKey("allergies_key_$username")
fun allergiesCompletedKeyForUser(username: String) = booleanPreferencesKey("allergies_completed_key_$username")
