from fastapi import FastAPI
from data import Data
import json

app = FastAPI()
data = Data()

@app.get("/api/panneau/all")
async def get_all_panneau():
    return data.findAllPanneau()

@app.get("/api/batterie/all")
async def get_all_batterie():
    return data.findAllBatterie()