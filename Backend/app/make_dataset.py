import asyncio
import csv
from app.database.connection import to_add
from app.crud.ingredients_crud import delete_missing


async def export_todo_to_csv(csv_file_path: str):
    items = await to_add.find().to_list(None)
    with open(csv_file_path, mode="w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(
            f, fieldnames=["Ingredients", "Description", "Allergies", "Verified From"]
        )
        writer.writeheader()
        for doc in items:
            writer.writerow({
                "Ingredients": doc.get("name", ""),
                "Description": "",
                "Allergies": "[]",
                "Verified From": ""
            })

    for doc in items:
        await to_add.delete_one({"_id": doc["_id"]})
        await delete_missing(doc["name"])

if __name__ == "__main__":
    csv_path = "train_scripts/todo.csv"
    asyncio.run(export_todo_to_csv(csv_path))