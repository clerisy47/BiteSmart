import asyncio
import csv
import ast
from app.crud.ingredients_crud import add_ingridient
from app.schemas.ingredients_schema import IngredientItem


async def upload_data(csv_file_path: str):
    with open(csv_file_path, mode="r", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            try:
                allergies = ast.literal_eval(row["Allergies"])
            except (SyntaxError, ValueError):
                allergies = []
            ingridient = IngredientItem(
                name=row["Ingredients"],
                description=row["Description"],
                allergies=allergies,
                source=row["Verified From"],
            )
            await add_ingridient(ingridient)
            print(f"Added ingredient: {ingridient.name}")


if __name__ == "__main__":
    csv_path = "train_scripts/dataset_2.csv"
    asyncio.run(upload_data(csv_path))
