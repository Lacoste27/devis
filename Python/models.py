class Panneau:
    def __init__(self, id, nom, puissance, prix):
        self.id = id
        self.nom = nom
        self.puissance = puissance
        self.prix = prix

class Batterie:
    def __init__(self, id, nom, voltage,amperage, prix , marge, datefabrication):
        self.id = id
        self.nom = nom  
        self.voltage = voltage
        self.amperage = amperage
        self.prix = prix
        self.marge = marge
        self.datefabrication = datefabrication
