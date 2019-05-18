/**
* Ellie Parobek
* ISTE-330-01 PE05
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
      password = "student";
      conn = null;
      connected = false;
   }
   
   /**
   * Load the drivers and connect to the database.
   *
   * @return boolean: Whether the connect was successful or not.
   */
   public boolean connect() throws DLException{
      // Load the driver
      try {
         Class.forName(driver);
      }
      catch(ClassNotFoundException cnfe){
         throw new DLException(cnfe, "Cannot load driver", cnfe.getStackTrace()[0].toString(), "URI: " + uri, "User: " + user);
      }
      
      // Open the database
      try{
         conn = DriverManager.getConnection(uri, user, password);
         connected = true;
         return true;
      }
      catch(SQLException sqle){
         throw new DLException(sqle, "Cannot connect to DB", sqle.getStackTrace()[0].toString(), "URI: " + uri, "User: " + user);
      }
   }
   
   /**
   * Close the database.
   *
   * @return boolean: Whether the disconnect was successful or not.
   */
   public boolean close() throws DLException{
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
         throw new DLException(sqle, "Cannot disconnect from DB", sqle.getStackTrace()[0].toString(), "URI: " + uri, "User: " + user);
      }
   }
   
   /**
   * Execute an SQL query and put the data in a Result Set.
   *
   * @param String query: an SQL query, int fields: number
   *        of fields for the query
   * @return ArrayList: the data from the query
   */
   public ArrayList getData(String query, int fields) throws DLException{
      try{
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         ArrayList<String> array = new ArrayList<>();
         if(fields < 0){
            ResultSetMetaData rsmd = rs.getMetaData();
            fields = rsmd.getColumnCount(); 
         }
         while(rs.next()){
            for(int i = 1; i <= fields; i++){
               array.add(rs.getString(i));
            }
         }
         return array;
      }
      catch(SQLException sqle){
         new DLException(sqle, "Cannot getData()", sqle.getStackTrace()[0].toString(), "URI: " + uri, "User: " + user);
         return null;
      }
   }
   
   /**
   * Execute an SQL query and put the data in a Result Set without
   * a given number of fields.
   *
   * @param String query: an SQL query
   * @return ArrayList: the data from the query
   */
   public void getData(String query) throws DLException{
      getData(query, -1);
   }
   
   /**
   * Used for updating, deleting, and inserting data.
   *
   * @param String query: an SQL query
   * @return int: number of rows effected
   */
   public int setData(String query) throws DLException{
      try{
         PreparedStatement ps = conn.prepareStatement(query);
         int rows = ps.executeUpdate(query);
         return rows;
      }
      catch(SQLException sqle){
         throw new DLException(sqle, "Cannot setData()", sqle.getStackTrace()[0].toString(), "URI: " + uri, "User: " + user);
      }
   }
   
   /**
   * Describe a table 
   */
   public void descTable(String query) throws DLException{
      try{
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         ResultSetMetaData rsmd = rs.getMetaData();
         int columnsNumber = rsmd.getColumnCount();
         
         // Describe table information
         
         System.out.println("Table " + rsmd.getTableName(1) + " has " + columnsNumber + " fields.");
         System.out.printf("%s %s\n","..Column Name..", "..Column Type..");
         for(int i = 1; i <= columnsNumber; i++){
            System.out.printf("%-20s %s\n", rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
         }
         
         // Describe data in table
         
         System.out.println("\nmysql> " + query + "\n");
         // Format header
         String format = "";
         String formatedString = "";
         for(int i = 1; i <= columnsNumber; i++){
            int length = rsmd.getColumnName(i).length();
            length += 10;
            if(length > 25){
                length = 25;
            }
            format = "%-" + length + "s";
            formatedString += String.format(format, "| " + rsmd.getColumnName(i));
         }
         // Format line breaks
         for(int i = 0; i <= formatedString.length(); i++){
            System.out.print("-");
         }
         // Print header
         System.out.println("");
         System.out.print(formatedString);
         System.out.print("|\n");
         // Line break again
         for(int i = 0; i <= formatedString.length(); i++){
            System.out.print("-");
         }
         System.out.println("");
         // Print data
         String formatedData = "";
         while(rs.next()){
            for(int i = 1; i <= columnsNumber; i++){
               int length = rsmd.getColumnName(i).length();
               length += 10;
               if(length > 25){
                   length = 25;
               }
               format = "%-" + length + "s";
               formatedData += String.format(format, "| " + rs.getString(i));
            }
            System.out.print(formatedData);
            System.out.print("|\n");
            formatedData = "";
            // Line break again
            for(int i = 0; i <= formatedString.length(); i++){
               System.out.print("-");
            }
            System.out.println("");
         }
      }
      catch(SQLException sqle){
         new DLException(sqle, "Cannot descTable()", sqle.getStackTrace()[0].toString(), "URI: " + uri, "User: " + user);
      }
   }
}