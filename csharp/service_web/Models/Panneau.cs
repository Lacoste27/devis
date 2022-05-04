using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;

namespace service_web.Models
{
    public class Panneau
    {
        private int id;
        private String nom;
        private Double puissance;
        private Double prix;

        public Panneau() {
            
        }

        public Panneau(int id , String nom , Double puissance , Double prix) {
            this.id = id ;
            this.nom = nom ;
            this.puissance = puissance ;
            this.prix = prix ;
        }

        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        public Double Prix
        {
            get { return prix; }
            set { prix = value; }
        }

        public Double Puissance
        {
            get { return puissance; }
            set { puissance = value; }
        }

        public String Nom
        {
            get { return nom; }
            set { nom = value; }
        }

        public List<Panneau> findAll()
        {
            List<Panneau> retour = new List<Panneau>();
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select * from panneau";
            commande.CommandText = sql;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {
                retour.Add(new Panneau(Convert.ToInt16(result["id"]), result["nom"].ToString(), Convert.ToDouble(result["puissance"]), Convert.ToDouble(result["prix"])));
            }
            connexion.Close();
            return retour;
        }

        public List<Panneau> findAllInterval(int min , int max)
        {
            List<Panneau> retour = new List<Panneau>();
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select * from panneau where puissance>="+(min+1)+" and puissance<="+max;
            commande.CommandText = sql;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {
                retour.Add(new Panneau(Convert.ToInt16(result["id"]), result["nom"].ToString(), Convert.ToDouble(result["puissance"]), Convert.ToDouble(result["prix"])));
            }
            connexion.Close();
            return retour;
        }

        public Double getMax()
        {
            Double retour = 0;
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select max(puissance) As maximum from panneau ";
            commande.CommandText = sql;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {
                retour = Convert.ToDouble(result["maximum"]);
            }
            connexion.Close();
            return retour;
        }

        public Panneau findById(int id)
        {
            List<Panneau> retour = new List<Panneau>();
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select * from panneau where id = @id";
            commande.CommandText = sql;
            commande.Parameters.Add("@id", System.Data.OleDb.OleDbType.Integer).Value = id;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {
                retour.Add(new Panneau(Convert.ToInt16(result["id"]), result["nom"].ToString(), Convert.ToDouble(result["puissance"]), Convert.ToDouble(result["prix"])));
            }
            connexion.Close();
            return retour.ElementAt(0);
        }


    }
}