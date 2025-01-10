from pydantic import BaseModel, Field


class IngredientRequest(BaseModel):
    details: str = Field(...)


class IngredientItem(BaseModel):
    name: str
    allergies: list
    description: str
    source: str


class IngredientData(BaseModel):
    items: list[IngredientItem]
    extraDetails: str

    class Config:
        orm_mode = True
