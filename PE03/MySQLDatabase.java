/**
* Ellie Parobek
* ISTE330-01
* MySQLDatabase class for connecting and modifying a database using Java and MySQL.
*/
import java.sql.*;
import java.util.ArrayList;

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
      password = "kururuSuu90!";
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
   
   /**
   * Execute an SQL query and put the data in a Result Set.
   *
   * @param String query: an SQL query, int fields: number
   *        of fields for the query
   * @return ArrayList: the data from the query
   */
   public ArrayList getData(String query, int fields){
      try{
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         ArrayList<String> array = new ArrayList<>();
         while(rs.next()){
            for(int i = 1; i <= fields; i++){
               array.add(rs.getString(i));
            }
         }
         return array;
      }
      catch(SQLException e){
         return null;
      }
   }
   
   /**
   * Used for updating, deleting, and inserting data.
   *
   * @param String query: an SQL query
   * @return int: number of rows effected
   */
   public int setData(String query){
      try{
         PreparedStatement ps = conn.prepareStatement(query);
         int rows = ps.executeUpdate(query);
         return rows;
      }
      catch(SQLException sqle){
         return -1;
      }
   }
}