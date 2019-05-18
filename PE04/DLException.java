/**
* Ellie Parobek
* ISTE-330-01 PE04
* Exception class that uses error messages to log errors to a file.
*/
import java.io.*;

public class DLException extends Exception{
   /**
   * Constructor for just the error Exception.
   *
   * @param: Exception e - the error thrown
   */
   private BufferedWriter bw = null;
   public DLException(Exception e){
      try{
         bw = new BufferedWriter(new FileWriter("ErrorLog.txt", true));
      }
      catch(IOException ioe){
         System.out.println("Error creating file in DLException.java\nError: " + ioe);
      }
      writeLog(e, "");
   }
   
   /**
   * Constructor for the error and values related.
   *
   * @ param: Exception e - the error thrown, String... data - 
   *          the values related to the error
   */
   public DLException(Exception e, String... data){
      try{
         bw = new BufferedWriter(new FileWriter("ErrorLog.txt", true));
      }
      catch(IOException ioe){
         System.out.println("Error creating file in DLException.java\nError: " + ioe);
      }
      writeLog(e, data);
   }
   
   /**
   * Writes the error report log.
   *
   * @ param: Exception e - the error thrown, String... data - 
   *          the values related to the error
   */
   public void writeLog(Exception e, String... data){
      // Print to console for us to read
      System.out.println("Error: " + e);
      System.out.print("Log: ");
      for(int i = 0; i < data.length; i ++){
         System.out.println(data[i]);
      }
      System.out.println("");
      
      // Write to file
      try{
         bw.append("Error: " + e);
         bw.append(System.lineSeparator());
         bw.append("Log: ");
         for(int i = 0; i < data.length; i ++){
            bw.append(data[i]);
            bw.append(System.lineSeparator());
         }
         bw.append(System.lineSeparator());
         bw.close();
      }
      catch(IOException ioe){
         System.out.println("Error creating file in DLException.java\nError: " + ioe);
      }
   }
}