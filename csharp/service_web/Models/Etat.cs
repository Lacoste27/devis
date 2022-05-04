using MySql.Data.MySqlClient;
using service_web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;

namespace service_web.Models
{
    public class Etat
    {
        String intervale ;
        int quantite ;
        Double prixunitairemoyenne ;
        Double prixwattmoyenne ;

        public String Intervale
        {
            get { return intervale; }
            set { intervale = value; }
        }

        public int Quantite {
            get {return quantite ;}
            set{quantite = value;}
        }

        public Double Prixunitairemoyenne {
            get {return prixunitairemoyenne ;}
            set {prixunitairemoyenne = value;}
        }

        public Double Prixwattmoyenne {
            get {return prixwattmoyenne;}
            set{prixwattmoyenne = value;}
        }

        public Etat(String intervale, int quantite, Double prixmoyenne, Double prixwatt) {
            this.intervale = intervale ;
            this.quantite = quantite ;
            this.prixunitairemoyenne = prixmoyenne ;
            this.prixwattmoyenne = prixwatt ;
        }

        public Etat() {

        }

         public int getMax()
        {
            int retour = 0;
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select max(puissance) As maximum from panneau ";
            commande.CommandText = sql;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {
                retour = Convert.ToInt16(result["maximum"]);
            }
            connexion.Close();
            return retour;
        }

        public List<String> generateinsert(int intervale) {
            List<string> retour = new List<string>() ;
            int debut = 0 ;
            int fin = intervale  ;
            int puisssancemax = this.getMax() ;
            Console.WriteLine(puisssancemax);
            while(debut<puisssancemax) {
                retour.Add("INSERT INTO intervale values("+debut+","+fin+");");
                debut += intervale ;
                fin += intervale ;
            }
            return retour ;
        }

        public List<Etat> GetEtat(int intervale) {
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            List<string> insertstring = this.generateinsert(intervale);
            Console.WriteLine(insertstring);
            List<Etat> retour = new List<Etat>();
            Etat.insert(insertstring, connexion) ;
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select * from etat ";
            commande.CommandText = sql;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {
                retour.Add(new Etat(result["debut"].ToString()+"-"+result["fin"].ToString(), Convert.ToInt16(result["quantite"]), Convert.ToDouble(result["prixmoyenne"]), Convert.ToDouble(result["prixwatt"])));
            }
            connexion.Close();
            return retour;
        }

       public List<Etat> GetEtatSousTotal(int intervale, int start) {
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            List<string> insertstring = this.generateinsert(intervale);
            Console.WriteLine(insertstring);
            List<Etat> retour = new List<Etat>();
            Etat.insert(insertstring, connexion) ;
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select * from etat";
            commande.CommandText = sql;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {
                retour.Add(new Etat(result["debut"].ToString()+"-"+result["fin"].ToString(), Convert.ToInt16(result["quantite"]), Convert.ToDouble(result["prixmoyenne"]), Convert.ToDouble(result["prixwatt"])));
            }
            connexion.Close();
            return retour;
        } 

        public List<int> GetIntervaleList(int soustotal) {
            List<int> retour = new List<int>() ;
            int intervale = soustotal ;
            int max = this.getMax() ;
            while(intervale<max) {
                retour.Add(intervale);
                intervale += soustotal ;
            }
            return retour ;
        } 

        public static void insert(List<String> list , OleDbConnection connection) {
            string requete = @"DELETE from intervale";
            OleDbCommand command = new OleDbCommand(requete, connection) ;
            command.ExecuteNonQuery() ;
            foreach (string query in list)
            {
                OleDbCommand commande = new OleDbCommand(query, connection);
                commande.ExecuteNonQuery() ;
            }
        }
    }

}