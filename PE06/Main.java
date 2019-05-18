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
      ArrayList<String> list = new ArrayList<>();
      // Original getData
      System.out.println("Original\n");
      list = sql.getData("SELECT * FROM equipment;", -1);
      for (int i = 0; i < list.size(); i++) {
	      System.out.println(list.get(i));
		}
      // getData with false
      System.out.println("\nFalse\n");
      list = sql.getData("SELECT * FROM equipment;", false);
      for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
      // getData with true
      System.out.println("\nTrue\n");
      list = sql.getData("SELECT * FROM equipment;", true);
      for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
      sql.close();
   }
}