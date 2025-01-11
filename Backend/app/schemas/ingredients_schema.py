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
    extra_details: str

    class Config:
        from_attributes = True
