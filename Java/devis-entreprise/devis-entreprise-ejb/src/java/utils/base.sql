/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Robsona
 * Created: 5 févr. 2022
 */

CREATE SEQUENCE idClient ;
CREATE SEQUENCE idAppareil :

--------------------------Postgresql-------------------------------

CREATE TABLE Appareil ( 
    id VARCHAR(20) NOT NULL ,
    nom VARCHAR(20) NOT NULL ,
    heuredebut TIME NOT NULL ,
    heurefin TIME NOT NULL ,
    puissance INTEGER NOT NULL ,
    quantite INTEGER NOT NULL ,
    PRIMARY KEY(id)
);



--------------------------MYSQL----------------------------
CREATE TABLE Client (
    id INTEGER NOT NULL ,
    nom VARCHAR(20) NOT NULL ,
    prenom VARCHAR(20) NOT NULL ,
    adresse VARCHAR(20) NOT NULL ,
    PRIMARY KEY(id) 
);

CREATE TABLE Devis (
    id INTEGER NOT NULL ,
    client_id INTEGER NOT NULL ,
    date Date ,
    PRIMARY KEY(id)
);

CREATE TABLE DevisDetails (
    id INTEGER NOT NULL ,
    devis_id INTEGER NOT NULL ,
    nomAppareil VARCHAR(100) NOT NULL ,
    puissance INTEGER NOT NULL ,
    quantite INTEGER NOT NULL ,
    prix INTEGER NOT NULL ,
    PRIMARY KEY(id) ,
    FOREIGN KEY(devis_id) REFERENCES Devis(id)
);


-------------------------Access---------------------------
TABLE Batterie
TABLE Panneau

