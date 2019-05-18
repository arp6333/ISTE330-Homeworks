/**
 * Ellie Parobek
 * ISTE330-01
 * Use the methods in the Equipment and MySQLDatabase classes.
 */
import java.sql.*;
import java.util.ArrayList;

public class Main{
   public static void main(String args[]){
      // Part a
      Equipment equipment1 = new Equipment();
      equipment1.setEquipId(568);
      equipment1.fetch();
      System.out.println("Part a.\nEquipID: " + equipment1.getEquipId() + "\nEquipmentName: " + equipment1.getEquipmentName() + "\nEquipmentDescription: " + equipment1.getEquipmentDescription() + "\nEquipmentCapacity: " + equipment1.getEquipmentCapacity());
      
      // Part b
      Equipment equipment2 = new Equipment(250, "Bus 250", "Coach", 40);
      int result = equipment2.post();
      System.out.println("\nPart b.\nNumber of records inserted: " + result);
      
      // Part c
      equipment2.setEquipmentCapacity(50);
      result = equipment2.put();
      System.out.println("\nPart c.\nNumber of records updated: " + result);
      
      // Part d
      equipment2.fetch();
      System.out.println("\nPart d.\nEquipID: " + equipment2.getEquipId() + "\nEquipmentName: " + equipment2.getEquipmentName() + "\nEquipmentDescription: " + equipment2.getEquipmentDescription() + "\nEquipmentCapacity: " + equipment2.getEquipmentCapacity());
      
      // Part e
      result = equipment2.delete();
      System.out.println("\nPart e.\nNumber of records deleted: " + result);
      
      // Part f
      try{
         equipment2.fetch();
      }
      catch(IndexOutOfBoundsException ioe){
         System.out.println("\nPart f.\nError: No data found for this EquipID.");
      }
   }
}