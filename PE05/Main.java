/**
* Ellie Parobek
* ISTE-330-01 PE05
* Main class for testing the updated MySQLDatabase class.
*/
import java.sql.*;
import java.util.ArrayList;

public class Main{
   
   /**
   * Main method to test nupdated MySQLDatabase class.
   * @param: String args[] - nothing
   */
   public static void main(String args[]) throws DLException{
      MySQLDatabase sql = new MySQLDatabase();
      sql.connect();
      sql.descTable("SELECT * FROM trip;");
      System.out.println("\n");
      sql.descTable("SELECT equipmentName, equipmentDescription FROM equipment;");
      System.out.println("\n");
      sql.descTable("SELECT date, tripnum, arrivalloccode, departureloccode, equipmentName FROM trip JOIN equipment USING(equipid) LIMIT 2;");
      sql.close();
   }
}