# coding: utf-8  
import pyodbc
from models import Panneau
from models import Batterie
import json
 
class Data:
    def findAllPanneau(self):
        list_panneau = []
        connexion   = pyodbc.connect(r"Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\Users\Tsiory\Documents\Devis\Python\base.mdb")
        curseur = connexion.cursor()
        curseur.execute("select * from panneau")
        for row in curseur.fetchall():
            panneau = Panneau(row[0],row[1],row[2],row[3])
            list_panneau.append(panneau.__dict__)
        return list_panneau
        curseur.close()
        connexion.close()

    def findAllBatterie(self):
        list_batterie = []
        connexion   = pyodbc.connect(r"Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\Users\Tsiory\Documents\Devis\Python\base.mdb")
        curseur = connexion.cursor()
        curseur.execute("select * from batterie")
        for row in curseur.fetchall():
            batterie = Batterie(row[0],row[1],row[2],row[3],row[4],row[5],row[6])
            list_batterie.append(batterie.__dict__)
        return list_batterie
        curseur.close()
        connexion.close()