/**
* Ellie Parobek
* ISTE330-01
* MySQLDatabase class for connecting to a database using Java and MySQL.
*/
import java.sql.*;

public class MySQLDatabase{
   private String uri;
   private String driver;
   private String user;
   private String password;
   private Connection conn;
   // Variable to have true for connected, false for not connected to a database.
   public boolean connected;
   
   /**
   * Constructor for variables.
   */
   public MySQLDatabase(){
      uri = "jdbc:mysql://127.0.0.1/travel?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      driver = "com.mysql.cj.jdbc.Driver";
      user = "root";
      password = "student";
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