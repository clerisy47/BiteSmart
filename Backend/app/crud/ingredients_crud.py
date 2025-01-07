from app.database.connection import hazard_collection
from app.schemas.location_schema import LocationRequest
from datetime import datetime
from app.database.connection import hazard_collection
from app.schemas.location_schema import HazardData
from typing import List

import logging

logging.basicConfig(level=logging.INFO)

async def find_hazard(type: str, lat: float, lon: float):
    return await hazard_collection.find_one({"type": type, "latitude": lat, "longitude": lon})

async def increment_hazard_count(type: str, lat: float, lon: float):
    logging.info(f"Incrementing hazard count for type: {type}, lat: {lat}, lon: {lon}")
    hazard = await find_hazard(type, lat, lon)
    if hazard:
        new_count = hazard["frequency"] + 1
        updated_data = {
            "$set": {
                "frequency": new_count,
                "latest_update": datetime.utcnow()
            }
        }
        await hazard_collection.update_one({"_id": hazard["_id"]}, updated_data)
        hazard["frequency"] = new_count
        hazard["latest_update"] = datetime.utcnow()
        return hazard
    else:
        new_hazard = {
            "type": type,
            "latitude": lat,
            "longitude": lon,
            "frequency": 1,
            "latest_update": datetime.utcnow()
        }
        await hazard_collection.insert_one(new_hazard)
        return new_hazard

async def get_all_hazards() -> List[HazardData]:
    hazards = await hazard_collection.find().to_list(length=None)
    return [HazardData(**hazard) for hazard in hazards]