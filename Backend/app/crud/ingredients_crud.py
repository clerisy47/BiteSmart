from database.connection import ingredients
from schemas.ingredients_schema import IngredientRequest, IngredientData

import logging

logging.basicConfig(level=logging.INFO)


async def extract_ingridients():
    pass


async def get_ingridient(name: str):
    ingridient = await ingredients.find_one({"name": name})
    if ingridient:
        return IngredientData(**ingridient)
    return None


async def add_ingridient(ingridient: IngredientRequest):
    ingridient_data = ingridient.model_dump()
    result = await ingredients.insert_one(ingridient_data)
    return result.inserted_id


async def update_ingridient(name: str, ingridient: IngredientRequest):
    ingridient_data = ingridient.model_dump()
    result = await ingredients.update_one({"name": name}, {"$set": ingridient_data})
    return result.modified_count


async def delete_ingridient(name: str):
    result = await ingredients.delete_one({"name": name})
    return result.deleted_count
