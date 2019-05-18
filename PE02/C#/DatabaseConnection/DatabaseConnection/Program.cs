using System;


namespace DatabaseConnection
{
    class Program
    {
        static void Main(string[] args)
        {
            // MySQL connection.
            MySQLDatabase mySQL = new MySQLDatabase();
            Boolean connected = mySQL.connect();
            if (connected == false)
            {
                Console.WriteLine("MySQL Error: Could not connect to the database.");
            }
            else
            {
                Console.WriteLine("MySQL: Database open.");
            }

            // MySQL disconnection.
            Boolean disconnected = mySQL.close();
            if (disconnected == false || connected == false)
            {
                Console.WriteLine("MySQL Error: Could not close the database.\n\n");
            }
            else
            {
                Console.WriteLine("MySQL: Database closed.\n\n");
            }

            // MSSQL connection
            SQLServerDatabase msSQL = new SQLServerDatabase();
            connected = msSQL.connect();
            if (connected == false)
            {
                Console.WriteLine("MSSQL Error: Could not connect to the database.");
            }
            else
            {
                Console.WriteLine("MSSQL: Database open.");
            }

            // MSSQL disconnection.
            disconnected = msSQL.close();
            if (disconnected == false || connected == false)
            {

                Console.WriteLine("MSSQL Error: Could not close the database.");
            }
            else
            {
                Console.WriteLine("MSSQL: Database closed.");
            }
            Console.WriteLine("\n\nPress any key to exit");
            Console.ReadKey();
        }
    }
}
