from fastapi import APIRouter, HTTPException
from typing import List
from app.schemas.location_schema import LocationRequest, HazardData
from app.crud.hazard_crud import increment_hazard_count,  get_all_hazards

router = APIRouter()

@router.post("/report-hazard/", response_model=HazardData)
async def report_hazard(location: LocationRequest):
    hazard_data = await increment_hazard_count(location.type, location.latitude, location.longitude)
    if not hazard_data:
        raise HTTPException(status_code=404, detail="Error reporting hazard.")
    return hazard_data

@router.get("/hazards/", response_model=List[HazardData])
async def get_hazards():
    hazards = await get_all_hazards()
    if not hazards:
        raise HTTPException(status_code=404, detail="No hazard data found.")
    return hazards