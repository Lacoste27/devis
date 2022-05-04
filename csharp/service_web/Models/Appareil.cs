using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;
using service_web.Models;
using MySql.Data.MySqlClient;


namespace service_web.Models
{
    public class Appareil
    {
        int id ;
        string nom ;
        TimeSpan heuredebut;
        TimeSpan heurefin ;
        int puissance ;
        int quantite ;
        int client_id;

        public Appareil() {

        }

        public Appareil(int id , string nom , TimeSpan heuredebut, TimeSpan heurefin, int puissance , int quantite ,int client_id) {
            this.id = id ;
            this.nom = nom ;
            this.heuredebut = heuredebut ;
            this.heurefin = heurefin ;
            this.puissance = puissance ;
            this.quantite = quantite ;
            this.client_id = client_id ;
        }

        public int Id{
            get { return id; }
            set { id = value; }
        }

        public string Nom {
            get {return nom;}
            set {nom = value;}
        }

        public TimeSpan Heuredebut
        {
            get { return heuredebut; }
            set { heuredebut = value; }
        }

         public TimeSpan Heurefin
        {
            get { return heurefin; }
            set { heurefin = value; }
        }

        public int Puissance {
            get {return puissance;}
            set {puissance = value;}
        }

        public int Quantite {
            get {return quantite;}
            set {quantite = value;}
        }

        public int Client_id {
            get {return client_id;}
            set {client_id = value;}
        }

        public List<Appareil> findAll() {
            List<Appareil> retour = new List<Appareil>();
            MySqlConnection connexion = Connect.GetMySqlConnection() ;
            connexion.Open();
            String sql = "select * from appareil";
            MySqlCommand cmd = new MySqlCommand(sql, connexion);
            var reader = cmd.ExecuteReader();
            Appareil appareil = new Appareil();
            while (reader.Read()){
                retour.Add(new Appareil(
                    Convert.ToInt16(reader["id"]),
                    Convert.ToString(reader["ref"]),
                    (TimeSpan)(reader["heuredebut"]),
                    (TimeSpan)(reader["heurefin"]),
                    Convert.ToInt16(reader["puissance"]),
                    Convert.ToInt16(reader["quantite"]),
                    Convert.ToInt16(reader["client_id"])
                ));
            }
            return retour;
        }

    }
}