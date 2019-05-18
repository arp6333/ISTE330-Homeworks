/**
* Ellie Parobek
* ISTE330-01
* SQLDatabase class for connecting to a database using Java and MSSQL.
*/
import java.sql.*;

public class SQLServerDatabase{
   private String uri;
   private String driver;
   private String user;
   private String password;
   private Connection conn;
   
   public boolean connected;
   
   /**
   * Constructor for variables.
   */
   public SQLServerDatabase(){
      uri = "jdbc:sqlserver://theodore.ist.rit.edu;databaseName=Jobs";
      driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
      user = "330User";
      password = "330Password";
      conn = null;
      
      connected = false;
   }
   
   /**
   * Load the drivers and connect to the database.
   *
   * @return boolean: Whether the connect was successful or not.
   */
   public boolean connect(){
      // Load the driver
      try {
         Class.forName(driver);
      }
      catch(ClassNotFoundException cnfe){
         return false;
      }
      
      // Open the database
      try{
         conn = DriverManager.getConnection(uri, user, password);
         connected = true;
         return true;
      }
      catch(SQLException sqle){
         return false;
      }
   }
   
   /**
   * Close the database.
   *
   * @return boolean: Whether the disconnect was successful or not.
   */
   public boolean close(){
      // Check if actually connected.
      if(connected == false){
         return false;
      }
      
      // Close the database.
      try{
         conn.close();
         return true;
      }
      catch(SQLException sqle){
         return false;
      }
   }
}