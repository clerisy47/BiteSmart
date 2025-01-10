from fastapi import APIRouter, HTTPException
from schemas.ingredients_schema import IngredientRequest, IngredientData
from crud.ingredients_crud import extract_ingridients

router = APIRouter()


@router.post("/record-Ingridients/", response_model=IngredientData)
async def post_text(text: IngredientRequest):
    return extract_ingridients(text.details)
