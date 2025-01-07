from pydantic import BaseModel, Field
from datetime import datetime

class LocationRequest(BaseModel):
    type: str
    latitude: float = Field(..., ge=-90, le=90)
    longitude: float = Field(..., ge=-180, le=180)

class HazardData(BaseModel):
    type: str
    latitude: float
    longitude: float
    frequency: int
    latest_update: datetime

    class Config:
        orm_mode = True
