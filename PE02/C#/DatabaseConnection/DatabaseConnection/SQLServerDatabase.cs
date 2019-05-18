using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DatabaseConnection
{
    internal class SQLServerDatabase
    {
        private string sqlsrvConnStr;
        private MySqlConnection conn;
        public Boolean connected;

        /*
         * Constructor for creating variables.  
         */
        public SQLServerDatabase()
        {
            sqlsrvConnStr = "Server=theodore.ist.rit.edu;Database=Jobs;User Id=330User;Password=330Password;";
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
                conn = new MySqlConnection(sqlsrvConnStr);
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