using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;
using service_web.Models;

namespace service_web.Models
{
    public class BatterieSupprimer
    {
        private int id;
        private int voltage;
        private int amperage;

        public BatterieSupprimer()
        {

        }

        public BatterieSupprimer(int id, int  voltage, int amperage){
            this.id = id ;
            this.voltage = voltage ;
            this.amperage = amperage ;
        }

        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        public int Voltage
        {
            get { return voltage; }
            set { voltage = value; }
        }

        public int Amperage
        {
            get { return amperage; }
            set { amperage = value; }
        }

        public static void insert(BatterieSupprimer batterie) {
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            String sql = "INSERT INTO BatterieSupprimer(voltage,amperage) values ("+batterie.Voltage+","+batterie.Amperage+")";
            OleDbCommand commande = new OleDbCommand(sql, connexion);
            commande.ExecuteNonQuery() ;
        }
        
    }
}