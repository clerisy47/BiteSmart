package com.app.bitesmart.data.dummyData

data class IngredientsData(
    val name: String,
    val description: String,
    val source: String
)
fun getIngredientsData(): List<IngredientsData> {
    return listOf(
        IngredientsData(
            name = "Dietary Fiber",
            description = "A type of carbohydrate that the body cannot digest, aiding in digestion and helping to maintain bowel health.",
            source = "Data collected from scientific nutrition sources"
        ),
        IngredientsData(
            name = "Vitamin C",
            description = "An essential nutrient that supports the immune system, helps in the absorption of iron, and promotes skin health.",
            source = "Data collected from medical nutrition guides"
        ),
        IngredientsData(
            name = "Calcium",
            description = "A mineral that is crucial for maintaining healthy bones and teeth, and also plays a role in nerve and muscle function.",
            source = "Data collected from health and wellness articles"
        ),
        IngredientsData(
            name = "Omega-3 Fatty Acids",
            description = "A type of healthy fat that supports heart health, brain function, and reduces inflammation in the body.",
            source = "Data collected from health and nutrition research"
        ),
        IngredientsData(
            name = "Trans Fats",
            description = "Artificially created fats that can increase cholesterol levels and contribute to heart disease when consumed in excess.",
            source = "Data collected from dietary health reports"
        ),
        IngredientsData(
            name = "Sodium",
            description = "An essential electrolyte that helps maintain fluid balance in the body, but excessive intake can lead to high blood pressure.",
            source = "Data collected from public health nutrition guidelines"
        ),
        IngredientsData(
            name = "Sugars",
            description = "Simple carbohydrates that provide quick energy but can contribute to weight gain and increased risk of certain health conditions when consumed in excess.",
            source = "Data collected from nutritional databases"
        ),
        IngredientsData(
            name = "Protein",
            description = "A macronutrient that is essential for muscle growth, tissue repair, and immune function.",
            source = "Data collected from nutrition science sources"
        ),
        IngredientsData(
            name = "Potassium",
            description = "A mineral that helps maintain normal blood pressure and is important for proper muscle and nerve function.",
            source = "Data collected from dietary nutrition sources"
        ),
        IngredientsData(
            name = "Magnesium",
            description = "A mineral that supports muscle and nerve function, helps regulate blood sugar, and contributes to bone health.",
            source = "Data collected from health nutrition research"
        )
    )
}
