using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;
using service_web.Models;

namespace service_web.Models
{
    public class Batterie
    {
        private int id;
        private String nom;
        private Double amperage;
        private Double voltage;
        private Double prix;
        private int marges;
        private String datefabrication;

        public Batterie()
        {

        }

        public Batterie(int id, String nom , Double amperage, Double voltage , Double prix, int marges, String datefabrication){
            this.id = id ;
            this.nom = nom ;
            this.amperage = amperage ;
            this.voltage = voltage ;
            this.prix = prix ;
            this.marges = marges ;
            this.datefabrication = datefabrication ;
        }

        public Double Prix
        {
            get { return prix; }
            set { prix = value; }
        }

        public Double Voltage
        {
            get { return voltage; }
            set { voltage = value; }
        }

        public Double Amperage
        {
            get { return amperage; }
            set { amperage = value; }
        }

        public String Nom
        {
            get { return nom; }
            set { nom = value; }
        }


        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        public int Marges {
            get {return marges ;}
            set {marges = value;}
        }

        public String Datefabrication {
            get {return datefabrication;}
            set {datefabrication = value; }
        }

        public List<Batterie> findAll()
        {
            List<Batterie> retour = new List<Batterie>();
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select * from batterie";
            commande.CommandText = sql;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {   
                retour.Add(new Batterie(
                    Convert.ToInt16(result["id"]),
                    result["nom"].ToString(),
                    Convert.ToDouble(result["amperage"]),
                    Convert.ToDouble(result["voltage"]),
                    Convert.ToDouble(result["prix"]),
                    Convert.ToInt16(result["marge"]),
                    result["datefabrication"].ToString()
                ));
            }
            connexion.Close();
            return retour;
        }

        public Batterie findById(int id)
        {
            List<Batterie> retour = new List<Batterie>();
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select * from batterie where id = @id";
            commande.CommandText = sql;
            commande.Parameters.Add("@id",System.Data.OleDb.OleDbType.Integer).Value = id ;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {
                retour.Add(new Batterie(
                    Convert.ToInt16(result["id"]),
                    result["nom"].ToString(),
                    Convert.ToDouble(result["amperage"]),
                    Convert.ToDouble(result["voltage"]),
                    Convert.ToDouble(result["prix"]), 
                    Convert.ToInt16(result["marge"]),
                    result["datefabrication"].ToString()
                ));
            }
            connexion.Close();
            return retour.ElementAt(0);
        }

        public static void insert(Batterie batterie) {
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            String sql = "INSERT INTO Batterie(nom,voltage,amperage,prix,marge,datefabrication) values ('batterie',"+batterie.Voltage+","+batterie.amperage+",20000,100,'2022-02-02')";
            OleDbCommand commande = new OleDbCommand(sql, connexion);
            commande.ExecuteNonQuery() ;
        }
    }
}