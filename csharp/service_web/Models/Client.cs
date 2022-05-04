using MySql.Data.MySqlClient;
using service_web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;

namespace service_web.Models
{
    public class Client
    {
        int id ;
        string demanderef;
        string nom ;
        string prenom ;
        string adresse;
        DateTime datedemande;

        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        public string Demanderef
        {
            get { return demanderef; }
            set { demanderef = value; }
        }

        public string Nom
        {
            get { return nom; }
            set { nom = value; }
        }

        public string Prenom
        {
            get { return prenom; }
            set { prenom = value; }
        }

        public string Adresse
        {
            get { return adresse; }
            set { adresse = value; }
        }

        public DateTime Datedemande
        {
            get { return datedemande; }
            set { datedemande = value; }
        }

        public Client() {

        }

        public Client(int id,string demanderef, string nom, string prenom , string adresse, DateTime datedemande) {
            this.id = id ;
            this.demanderef = demanderef ;
            this.nom = nom ;
            this.prenom = prenom ;
            this.adresse = adresse ;
            this.datedemande = datedemande ;
        }

        public List<Client> findAll() {
            List<Client> retour = new List<Client>();
            MySqlConnection connexion = Connect.GetMySqlConnection() ;
            connexion.Open();
            String sql = "select * from client";
            MySqlCommand cmd = new MySqlCommand(sql, connexion);
            var reader = cmd.ExecuteReader();
            Client client = new Client();
            while (reader.Read()){
                retour.Add(new Client(
                    Convert.ToInt16(reader["id"]),
                    Convert.ToString(reader["ref"]),
                    Convert.ToString(reader["nom"]),
                    Convert.ToString(reader["prenom"]),
                    Convert.ToString(reader["adresse"]),
                    (DateTime) reader["datedemande"]
                ));
            }
            return retour;
        }

        public Client findById(int id) {
            List<Client> retour = new List<Client>();
            MySqlConnection connexion = Connect.GetMySqlConnection() ;
            connexion.Open();
            String sql = "select * from client where id="+id;
            MySqlCommand cmd = new MySqlCommand(sql, connexion);
            var reader = cmd.ExecuteReader();
            Client client = new Client();
            while (reader.Read()){
                retour.Add(new Client(
                    Convert.ToInt16(reader["id"]),
                    Convert.ToString(reader["ref"]),
                    Convert.ToString(reader["nom"]),
                    Convert.ToString(reader["prenom"]),
                    Convert.ToString(reader["adresse"]),
                    (DateTime) reader["datedemande"]
                ));
            }
            return retour.ElementAt(0);
        }
    }
}