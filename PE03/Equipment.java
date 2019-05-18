/**
 * Ellie Parobek
 * ISTE330-01
 * Represent and store contents of the EQUIPMENT table in travel.sql.
 */
import java.sql.*;
import java.util.ArrayList;

public class Equipment{
   // Equipment attributes.
   private int equipId;
   private String equipmentName;
   private String equipmentDescription;
   private int equipmentCapacity;
   
   /**
   * Default constructor.
   */
   public Equipment(){
      equipId = 0;
      equipmentName = null;
      equipmentDescription = null;
      equipmentCapacity = 0;
   }
   
   /**
   * Constructor for EquipID.
   *
   * @param id: the id to be set
   */
   public Equipment(int id){
      equipId = id;
      equipmentName = null;
      equipmentDescription = null;
      equipmentCapacity = 0;
   }
   
   /**
   * Constructor for all attributes.
   *
   * @param id: the id to be set, name: the name to be set, description: the 
   *            description to be set, capacity: the capacity to be set
   */
   public Equipment(int id, String name, String description, int capacity){
      equipId = id;
      equipmentName = name;
      equipmentDescription = description;
      equipmentCapacity = capacity;
   }
   
   /**
   * Accessor for EquipID.
   *
   * @return int:EquipID
   */
   public int getEquipId(){
      return equipId;
   }
   
   /**
   * Accessor for EquipmentName.
   *
   * @return String:EquipmentName
   */
   public String getEquipmentName(){
      return equipmentName;
   }
   
   /**
   * Accessor for EquipmentDescription.
   *
   * @return String:EquipmentDescription
   */
   public String getEquipmentDescription(){
      return equipmentDescription;
   }
   
   /**
   * Accessor for EquipmentCapacity.
   *
   * @return int:EquipmentCapacity
   */
   public int getEquipmentCapacity(){
      return equipmentCapacity;
   }
   
   /**
   * Mutator for EquipID.
   *
   * @param id: new EquipID
   */
   public void setEquipId(int id){
      equipId = id;
   }
   
   /**
   * Mutator for EquipmentName.
   *
   * @param name: new EquipmentName
   */
   public void setEquipmentName(String name){
      equipmentName = name;
   }
   
   /**
   * Mutator for EquipmentDescription.
   *
   * @param description: new EquipmentDescription
   */
   public void setEquipmentDescription(String description){
      equipmentDescription = description;
   }
   
   /**
   * Mutator for EquipmentCapacity.
   *
   * @param capacity: new EquipmentCapacity
   */
   public void setEquipmentCapacity(int capacity){
      equipmentCapacity = capacity;
   }
   
   /**
   * Retrieve database values using the EquipID.
   */
   public void fetch(){
      // MySQL connection.
      MySQLDatabase mySQL = new MySQLDatabase();
      boolean connected = mySQL.connect();
      if(connected == false){
         System.out.println("MySQL Error: Could not connect to the database.");
         return;
      }
      
      ArrayList<String> list = new ArrayList<>();
      list = mySQL.getData("SELECT equipmentName, equipmentDescription, equipmentCapacity FROM equipment WHERE equipID = " + equipId + ";", 3);
      setEquipmentName(list.get(0));
      setEquipmentDescription(list.get(1));
      setEquipmentCapacity(Integer.parseInt(list.get(2)));
      
      // MySQL disconnection.
      boolean disconnected = mySQL.close();
      if(disconnected == false || connected == false){
         System.out.println("MySQL Error: Could not close the database.\n");
         return;
      }
   }
   
   /**
   * Update database values using the EquipID.
   *
   * @return int: the number of rows changed
   */
   public int put(){
      // MySQL connection.
      MySQLDatabase mySQL = new MySQLDatabase();
      boolean connected = mySQL.connect();
      if(connected == false){
         System.out.println("MySQL Error: Could not connect to the database.");
         return -1;
      }
      
      int result = mySQL.setData("UPDATE Equipment SET EquipmentName = '" + equipmentName + "', EquipmentDescription = '" + equipmentDescription + "', EquipmentCapacity = " + equipmentCapacity + " WHERE EquipID = " + equipId + ";");
      
      // MySQL disconnection.
      boolean disconnected = mySQL.close();
      if(disconnected == false || connected == false){
         System.out.println("MySQL Error: Could not close the database.\n");
         return -1;
      }
      
      return result;
   }
   
   /**
   * Create a new record in the database.
   *
   * @return int: the number of rows changed
   */
   public int post(){
      // MySQL connection.
      MySQLDatabase mySQL = new MySQLDatabase();
      boolean connected = mySQL.connect();
      if(connected == false){
         System.out.println("MySQL Error: Could not connect to the database.");
         return -1;
      }
      
      int result = mySQL.setData("INSERT INTO Equipment VALUES(" + equipId + ", '" + equipmentName + "', '" + equipmentDescription + "', " + equipmentCapacity + ");");
   
      // MySQL disconnection.
      boolean disconnected = mySQL.close();
      if(disconnected == false || connected == false){
         System.out.println("MySQL Error: Could not close the database.\n");
         return -1;
      }
      
      return result;
   }
   
   /**
   * Remove a record from the database using the EquipID.
   *
   * @return int: the number of rows changed
   */
   public int delete(){
      // MySQL connection.
      MySQLDatabase mySQL = new MySQLDatabase();
      boolean connected = mySQL.connect();
      if(connected == false){
         System.out.println("MySQL Error: Could not connect to the database.");
         return -1;
      }
      
      int result = mySQL.setData("DELETE FROM Equipment WHERE EquipID = " + equipId + ";");
      
      // MySQL disconnection.
      boolean disconnected = mySQL.close();
      if(disconnected == false || connected == false){
         System.out.println("MySQL Error: Could not close the database.\n");
         return -1;
      }
      
      return result;
   }
}