using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.OleDb;
using MySql.Data.MySqlClient;

namespace service_web.Models
{
    public class Connect
    {

        public static OleDbConnection GetConnection(){
            OleDbConnection connexion = new OleDbConnection("Provider=Microsoft.ACE.OLEDB.12.0;Data Source=C:\\Users\\Tsiory\\Documents\\Devis\\C#\\service_web\\base.mdb");
            return connexion;
        }

        public static MySqlConnection GetMySqlConnection(){
            MySqlConnection connection;
            string server = "localhost";
            string database = "devis";
            string uid = "root";
            string password = "";
            string connectionString;
            connectionString = "SERVER=" + server + ";" + "DATABASE=" +
            database + ";" + "UID=" + uid + ";" + "PASSWORD=" + password + ";";
            connection = new MySqlConnection(connectionString);
            return connection;
        }
    }
}