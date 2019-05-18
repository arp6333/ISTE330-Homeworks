/**
 * Ellie Parobek
 * ISTE330-01 PE07 Update
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
   public void fetch() throws DLException{
      // MySQL connection.
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
      
      // Do the fetch
      ArrayList<String> list = new ArrayList<>();
      // ArrayList of data for prepared statement
      ArrayList<String> data = new ArrayList<>();
      data.add("" + 568);
      try{
         list = mySQL.getData("SELECT equipmentName, equipmentDescription, equipmentCapacity FROM equipment WHERE equipID = ?;", data);
         setEquipmentName(list.get(3));
         setEquipmentDescription(list.get(4));
         setEquipmentCapacity(Integer.parseInt(list.get(5)));  
      }
      catch(DLException dle){
         System.out.println("Error: " + dle.getMessage());
      }
      catch(IndexOutOfBoundsException e){
         new DLException(e, "fetch() ArrayList out of bounds error", e.getStackTrace()[0].toString());
      }
      
      // MySQL disconnection.
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
   }
   
   /**
   * Update database values using the EquipID.
   *
   * @return int: the number of rows changed
   */
   public int put(){
      // MySQL Connection
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
      
      // Do the put
      int result = -1;
      // ArrayList of data for prepared statement
      ArrayList<String> data = new ArrayList<>();
      data.add(equipmentName);
      data.add(equipmentDescription);
      data.add("" + equipmentCapacity);
      data.add("" + equipId);
      try{
         result = mySQL.setData("UPDATE Equipment SET EquipmentName = ?, EquipmentDescription = ?, EquipmentCapacity = ? WHERE EquipID = ?;", data);
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
         return -1;
      }
      
      // MySQL disconnection.
      boolean disconnected = false;
      try{  
         disconnected = mySQL.close();
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
      }
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
      // MySQL Connection
      boolean connected = false;
      MySQLDatabase mySQL = new MySQLDatabase();
      try{
         connected = mySQL.connect();
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
         return -1;
      }
      if(connected == false){
         System.out.println("MySQL Error: Could not connect to the database.");
         return -1;
      }
      
      // Do the post
      int result = -1;
      // ArrayList of data for prepared statement
      ArrayList<String> data = new ArrayList<>();
      data.add("" + equipId);
      data.add(equipmentName);
      data.add(equipmentDescription);
      data.add("" + equipmentCapacity);
      try{
         result = mySQL.setData("INSERT INTO Equipment VALUES(?, ?, ?, ?);", data);
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
      }
      
      // MySQL disconnection.
      boolean disconnected = false;
      try{  
         disconnected = mySQL.close();
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
      }
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
      // MySQL Connection
      boolean connected = false;
      MySQLDatabase mySQL = new MySQLDatabase();
      try{
         connected = mySQL.connect();
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
         return -1;
      }
      if(connected == false){
         System.out.println("MySQL Error: Could not connect to the database.");
         return -1;
      }
      
      // Do the delete
      int result = -1;
      // ArrayList of data for prepared statement
      ArrayList<String> data = new ArrayList<>();
      data.add("" + equipId);
      try{
         result = mySQL.setData("DELETE FROM Equipment WHERE EquipID = ?;", data);
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
      }
      
      // MySQL disconnection.
      boolean disconnected = false;
      try{  
         disconnected = mySQL.close();
      }
      catch(DLException dle){
         System.out.println(dle.getMessage());
      }
      if(disconnected == false || connected == false){
         System.out.println("MySQL Error: Could not close the database.\n");
         return -1;
      }
      
      return result;
   }
}