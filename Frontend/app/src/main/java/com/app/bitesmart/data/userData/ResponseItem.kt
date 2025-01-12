package com.app.bitesmart.data.userData


import android.util.Log
import com.app.bitesmart.data.dummyData.IngredientsData
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val description: String,
    val source: String,
    val allergies: List<String> // Add allergies field
)

@Serializable
data class Response(
    val items: List<Item>,
    val extra_details: String
)



fun parseJsonResponse(jsonResponse: String): List<IngredientsData> {
    return try {
        val json = Json { ignoreUnknownKeys = true }
        val response = json.decodeFromString<Response>(jsonResponse)
        response.items.map { item ->
            IngredientsData(
                name = item.name,
                description = item.description,
                source = item.source,
                allergies = item.allergies // Map allergies field
            )
        }
    } catch (e: Exception) {

        Log.d("Response", "Error parsing JSON: ${e.message}")
        e.printStackTrace()
        emptyList() // Return an empty list or handle as needed
    }
}
