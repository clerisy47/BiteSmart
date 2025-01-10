from fastapi import APIRouter, HTTPException
from schemas.ingredients_schema import IngredientRequest, IngredientItem, IngredientData
from crud.ingredients_crud import extract_ingridients, get_ingridient
import json


router = APIRouter()


@router.post("/record-Ingridients/", response_model=IngredientData)
async def post_text(text: IngredientRequest):
    result_str = extract_ingridients(text.details)
    print(result_str)
    result_json = json.loads(result_str)
    ing_lst = []
    for info in result_json["information"]:
        ingredient = await get_ingridient(info)
        if ingredient:
            ing_lst.append(ingredient)
    return IngredientData(items=ing_lst, extra_details=result_json["extra_details"])
