from motor.motor_asyncio import AsyncIOMotorClient
import urllib.parse

username = urllib.parse.quote_plus("clerisy47")
password = urllib.parse.quote_plus("lol123")
MONGO_URL = f"mongodb+srv://{username}:{password}@cluster0.j3ygy.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"


client = AsyncIOMotorClient(MONGO_URL)
db = client.bitesmart

ingredients = db.ingredients
to_add = db.to_add


async def shutdown_db_client():
    client.close()
