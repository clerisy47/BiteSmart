from datetime import datetime


class IngrdientsDatabase:
    def __init__(self, name: str, description: str, allergies: list, source: str):
        self.name = name
        self.description = description
        self.allergies = allergies
        self.source = source
