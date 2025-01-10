from database.connection import ingredients
from schemas.ingredients_schema import IngredientRequest, IngredientItem
import google.generativeai as genai

import logging

logging.basicConfig(level=logging.INFO)

import typing_extensions as typing


class Information(typing.TypedDict):
    information: typing.List[str]
    extra_details: str


GOOGLE_API_KEY = "AIzaSyBV_7pw611g6J19JzJCwWevKXGIfnVuEjg"


def extract_ingridients(text: str):
    genai.configure(api_key=GOOGLE_API_KEY)
    system_instruction = """Extract list of nutritional information contents inside the food. 
        If the information has multiple words, make it 1 or 2 word, also capitalise first letter.
        The word should be full and common version, if it is written in other language, translate to English.
        If it is written in short form like Vit B for Vitamin B, then write more common word ie vitamin.
        Moreover, add extra information about the product as well that a user must know."""

    model = genai.GenerativeModel(
        "gemini-1.5-pro-latest", system_instruction=system_instruction
    )

    result = model.generate_content(
        text,
        generation_config=genai.GenerationConfig(
            response_mime_type="application/json", response_schema=Information
        ),
    )
    return result.text


async def get_ingridient(name: str):
    ingridient = await ingredients.find_one({"name": name})
    if ingridient:
        return IngredientItem(**ingridient)
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
