/**
* Ellie Parobek
* ISTE-330-01 PE04
* Main class for testing the DLException class.
*/
import java.sql.*;
import java.util.ArrayList;

public class Main{
   
   /**
   * Main method to test successful and unsuccesful database calls.
   * @param: String args[] - ignore
   */
   public static void main(String args[]) throws DLException{
      // Variables for the non-existent database
      String uri = "thisdriverdoesnotexist";
      String driver = "com.mysql.cj.jdbc.Driver";
      String user = "root";
      String password = "sad";
      Connection conn = null;
      
      // Part a. Attempting to connect to the non-existent database
      try {
         Class.forName(driver);
      }
      catch(ClassNotFoundException cnfe){
         throw new DLException(cnfe, "Cannot load driver", "" + cnfe.getStackTrace(), "URI: " + uri, "User: " + user);
      }
      try{
         conn = DriverManager.getConnection(uri, user, password);
      }
      catch(SQLException sqle){
         System.out.println("a. Connection failure:");
         new DLException(sqle, "Cannot connect to DB", sqle.getStackTrace()[0].toString(), "URI: " + uri, "User: " + user);
      }
      
      // Part b. Successful connection to the travel database using equipment
      Equipment equipment1 = new Equipment(568);
      System.out.println("b. Connection Success:\nSuccessfully connected to DB\n");
      
      // Part c. Unsuccessful fetch
      System.out.println("c. Fetch failure test:");
      // MySQL connection (should be successful)
      boolean connected = false;
      MySQLDatabase mySQL = new MySQLDatabase();
      try{
         connected = mySQL.connect();
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
      }
      if(connected == false){
         System.out.println("MySQL Error: Could not connect to the database.");
      }
      
      // Try to do the fetch
      ArrayList<String> list = new ArrayList<>();
      try{
         list = mySQL.getData("SELECT equipmentNumber FROM equipment WHERE equipID = " + 568 + ";", 3); 
      }
      catch(IndexOutOfBoundsException e){
         new DLException(e, "fetch() ArrayList out of bounds error", e.getStackTrace()[0].toString());
      }
      
      // MySQL disconnection
      boolean disconnected = false;
      try{  
         disconnected = mySQL.close();
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
      }
      if(disconnected == false || connected == false){
         System.out.println("MySQL Error: Could not close the database.\n");
         return;
      }
      
      // Part d. Successful fetch 
      equipment1.fetch();
      System.out.println("d. Fetch success test:\nEquipID: " + equipment1.getEquipId() + "\nEquipmentName: " + equipment1.getEquipmentName() + "\nEquipmentDescription: " + equipment1.getEquipmentDescription() + "\nEquipmentCapacity: " + equipment1.getEquipmentCapacity());
   }
}