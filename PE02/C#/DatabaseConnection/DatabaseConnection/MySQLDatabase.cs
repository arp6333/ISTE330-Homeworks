using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DatabaseConnection
{
    internal class MySQLDatabase
    {
        private string mysqlConnStr;
        private MySqlConnection conn;
        public Boolean connected;

        /*
         * Constructor for creating variables.  
         */
        public MySQLDatabase()
        {
            mysqlConnStr = "server=127.0.0.1;Database=travel;uid=root;pwd=student;SslMode=none";
            connected = false;
            conn = null;
        }

        /*
         * Connect to MySQL and open database.
         * 
         * @return Boolean: Whether the connect was successful or not.
         */
        public Boolean connect()
        {
            // Connect to MySQL.
            try
            {
                conn = new MySqlConnection(mysqlConnStr);
            }
            catch (MySqlException E)
            {
                return false;
            }

            // Open database.
            try
            {
                conn.Open();
                connected = true;
                return true;
            }
            catch (MySqlException E)
            {
                return false;
            }
        }

        /*
         * Disconnect and close the database.
         * 
         * @return Boolean: Whether the close was successful or not.
         */
        public Boolean close()
        {
            // Check if actually connected.
            if (connected == false)
            {
                return false;
            }
            // Close the database.
            try
            {
                conn.Close();
                return true;
            }
            catch (MySqlException E)
            {
                return false;
            }
        }
    }
}
