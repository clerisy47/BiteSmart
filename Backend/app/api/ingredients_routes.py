from fastapi import APIRouter, HTTPException
from app.schemas.ingredients_schema import IngredientRequest,  IngredientData
from app.crud.ingredients_crud import extract_ingredients, get_ingredient, add_missing, find_missing
import json


router = APIRouter()


@router.post("/get-information/", response_model=IngredientData)
async def post_text(text: IngredientRequest):
    result_str = extract_ingredients(text.details)
    result_json = json.loads(result_str)
    ing_lst = []
    for info in result_json["information"]:
        ingredient = await get_ingredient(info)
        if ingredient:
            ing_lst.append(ingredient)
        else:
            is_present = await find_missing(info)
            if(not is_present):
                await add_missing(info)
        print(info)

    extra_details = result_json.get("extra_details", "")
    return IngredientData(items=ing_lst, extra_details=extra_details)
