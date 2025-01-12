from app.database.connection import ingredients, to_add
from app.schemas.ingredients_schema import IngredientRequest, IngredientItem
import google.generativeai as genai

import logging

logging.basicConfig(level=logging.INFO)

import typing_extensions as typing


class Information(typing.TypedDict):
    information: typing.List[str]
    extra_details: str


GOOGLE_API_KEY = "AIzaSyBV_7pw611g6J19JzJCwWevKXGIfnVuEjg"


def extract_ingredients(text: str):
    genai.configure(api_key=GOOGLE_API_KEY)
    system_instruction = """The prompt contains many words, out of the words identify ingredients and nutritional information. 
        If any component has multiple words, make it 1 or 2 word.
        The word should be full and common version, if it is written in other language, translate to English, but display only one word.
        If it is written in short form like Vit B for Vitamin B, then write more common word ie Vitamin. 
        The output words must have first letter capital. ie it should not be palm oil or Palm oil, it should be Palm Oil. 
        If some items has 0 gm composition, then it means that the item is not present. So, don't consider it. 
        Don't add extra words than what is mentioned in the text. 
        Apart from the nutrtional components, if there are extra details that is written that user should know, write it in extra_details"""

    model = genai.GenerativeModel(
        "gemini-1.5-pro-latest", system_instruction=system_instruction
    )

    result = model.generate_content(
        text,
        generation_config=genai.GenerationConfig(
            response_mime_type="application/json", response_schema=Information
        ),
    )
    print(result.text)
    return result.text


async def get_ingredient(name: str):
    ingredient = await ingredients.find_one({"name": name})
    if ingredient:
        return IngredientItem(**ingredient)
    return None

async def add_ingredient(ingredient: IngredientItem):
    ingredient_data = ingredient.model_dump()
    result = await ingredients.insert_one(ingredient_data)
    return result.inserted_id


async def update_ingredient(name: str, ingredient: IngredientRequest):
    ingredient_data = ingredient.model_dump()
    result = await ingredients.update_one({"name": name}, {"$set": ingredient_data})
    return result.modified_count


async def delete_ingredient(name: str):
    result = await ingredients.delete_one({"name": name})
    return result.deleted_count

async def find_missing(name: str):
    is_present = await to_add.find_one({"name": name})
    if is_present:
        return True
    return False


async def add_missing(name: str):
    result = await to_add.insert_one({'name': name})
    return result.inserted_id

async def delete_missing(name: str):
    result = await to_add.delete_one({"name": name})
    return result.deleted_count
