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



fun parseJsonResponse(jsonResponse: String): Pair<List<IngredientsData>, String> {
    return try {
        val json = Json { ignoreUnknownKeys = true }
        val response = json.decodeFromString<Response>(jsonResponse)

        val ingredientsList = response.items.map { item ->
            IngredientsData(
                name = item.name,
                description = item.description,
                source = item.source,
                allergies = item.allergies
            )
        }

        // Return both ingredientsList and extra_details
        Pair(ingredientsList, response.extra_details)
    } catch (e: Exception) {
        Log.d("Response", "Error parsing JSON: ${e.message}")
        e.printStackTrace()
        Pair(emptyList(), "") // Return empty list and empty string in case of error
    }
}

