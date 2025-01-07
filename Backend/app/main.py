from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from app.api.hazard_routes import router as hazard_router
from app.database.connection import shutdown_db_client

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(hazard_router, prefix="/api")

@app.on_event("shutdown")
async def shutdown_db():
    await shutdown_db_client()