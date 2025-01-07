from motor.motor_asyncio import AsyncIOMotorClient
import urllib.parse

username = urllib.parse.quote_plus("clerisy47")
password = urllib.parse.quote_plus("12345")
MONGO_URL = f"mongodb+srv://{username}:{password}@cluster0.rebpo.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"

client = AsyncIOMotorClient(MONGO_URL)
db = client.hazards_db

hazard_collection = db.hazards

async def shutdown_db_client():
    client.close()
