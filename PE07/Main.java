/**
* Ellie Parobek
* ISTE-330-01 PE07 Update
* Main class for testing the updated MySQLDatabase class.
*/
import java.sql.*;
import java.util.ArrayList;

public class Main{
   
   /**
   * Main method to test updated MySQLDatabase class.
   * @param: String args[] - ignore
   */
   public static void main(String args[]) throws DLException{
      // Part a, fetch data for an equipId
      Equipment equip = new Equipment();
      equip.setEquipId(568);
      equip.fetch();
      System.out.println("Part a, fetch data:\nEquipID: " + equip.getEquipId() + "\nEquipmentName: " + equip.getEquipmentName() + "\nEquipmentDescription: " + equip.getEquipmentDescription() + "\nEquipmentCapacity: " + equip.getEquipmentCapacity());
      
      // Part b, put then fetch that changed value
      System.out.println("\nPart b, fetch after put (changing capacity to 50):");
      equip.setEquipmentCapacity(50);
      int result = equip.put();
      System.out.println("Number of records updated: " + result);
      equip.fetch();
      System.out.println("EquipID: " + equip.getEquipId() + "\nEquipmentName: " + equip.getEquipmentName() + "\nEquipmentDescription: " + equip.getEquipmentDescription() + "\nEquipmentCapacity: " + equip.getEquipmentCapacity());
   }
}