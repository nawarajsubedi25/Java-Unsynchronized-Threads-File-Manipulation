import java.io.*;
import java.util.*;
/**
  * Discription : A class that simple read the file which is contains the lines of threads.
  * @author     : Nawaraj Subedi
  * @since      : Feb 19,2017
  */ 

public class SUBEDI_RAJ_MyThread extends Thread 
{
   PrintWriter writer;
   String line;
   Scanner sc;
   int count=1;
   String readFile,makeFile="";
   public SUBEDI_RAJ_MyThread ( String rFile, String mFile, String str)
   {
      super (str);
      readFile=rFile;
      makeFile=mFile;
   }
   public void run()
   {
      System.out.println("MyThread[" + getName() + "]: BEGIN!");
      File infile=new File (readFile);
   
      try {
         sc=new Scanner(infile);
         // Make the file 
         writer= new PrintWriter(makeFile);
         while(sc.hasNextLine())
         {
            line="Thread[" + getName() + "]: Line[" + count+"]:"+sc.nextLine();
            writer.println(line); 
            count++;              
         } 
         // Close the counter
         writer.close();         
      } 
      catch(FileNotFoundException e)
      {
         System.out.println("Alert: File "+infile.getPath()+" doesn't exit !!");
         e.printStackTrace();
      }
      
      catch(Exception e)
      {
         System.out.println("Alert: File "+infile.getPath()+ " can't be opend !!");
         e.printStackTrace();
      }
      //close the scanner
      finally 
      {
         if(sc!=null)
         {
            sc.close();
         }
      }
      System.out.println("MyThread[" + getName() + "]: END!");
   
   }
}   



