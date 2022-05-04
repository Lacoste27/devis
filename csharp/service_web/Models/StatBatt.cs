using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;
using service_web.Models;

namespace service_web.Models
{
    public class StatBatt
    {
        private int nombre;
        private int voltage;
        private int amperage;

        public StatBatt()
        {

        }

        public StatBatt(int nombre, int  voltage, int amperage){
            this.nombre = nombre ;
            this.voltage = voltage ;
            this.amperage = amperage ;
        }

        public int Nombre
        {
            get { return nombre; }
            set { nombre = value; }
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

        public static List<StatBatt> findAll()
        {
            List<StatBatt> retour = new List<StatBatt>();
            OleDbConnection connexion = Connect.GetConnection();
            connexion.Open();
            OleDbCommand commande = connexion.CreateCommand();
            String sql = "select * from stat";
            commande.CommandText = sql;
            OleDbDataReader result = commande.ExecuteReader();
            while (result.Read())
            {   
                retour.Add(new StatBatt(
                    Convert.ToInt16(result["nombre"]),
                    Convert.ToInt16(result["amperage"]),
                    Convert.ToInt16(result["voltage"])
                ));
            }
            connexion.Close();
            return retour;
        }
    }
}