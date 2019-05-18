/**
* Ellie Parobek
* ISTE330-01
* Connect to a database using the MySQL and MSSQL class files.
*/
import java.sql.*;

public class DatabaseConnect{
   /**
   * Main method attempts to create and close MySQL and MSSQL connections.
   *
   * @param- String[] args: nothing.
   */
   public static void main(String[] args){
      // MySQL connection.
      MySQLDatabase mySQL = new MySQLDatabase();
      boolean connected = mySQL.connect();
      if(connected == false){
         System.out.println("MySQL Error: Could not connect to the database.");
      }
      else{
         System.out.println("MySQL: Database open.");
      }
      
      // MySQL disconnection.
      boolean disconnected = mySQL.close();
      if(disconnected == false || connected == false){
         System.out.println("MySQL Error: Could not close the database.\n");
      }
      else{
         System.out.println("MySQL: Database closed.\n");
      }
      
      // MSSQL connection
      SQLServerDatabase msSQL = new SQLServerDatabase();
      connected = msSQL.connect();
      if(connected == false){
         System.out.println("MSSQL Error: Could not connect to the database.");
      }
      else{
         System.out.println("MSSQL: Database open.");
      }
      
      // MSSQL disconnection.
      disconnected = msSQL.close();
      if(disconnected == false || connected == false){
         System.out.println("MSSQL Error: Could not close the database.");
      }
      else{
         System.out.println("MSSQL: Database closed.");
      }
   }
}