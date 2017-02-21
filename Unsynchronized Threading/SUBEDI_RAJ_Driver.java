import java.io.*;
import java.util.*;
/**
  * Discription : A java Unsynchronized Threads File Manipulation program that simple read the input file 
                  which conatins instructions about the threads it need to create.
  * @author     : Nawaraj Subedi
  * @since      : Feb 19,2017
  */ 


public class SUBEDI_RAJ_Driver
{
   private static Scanner read;
    
   public static void main (String[] args) 
   {
      int noOfThread=0, count=0;
      Scanner sc=null;
      String finalOut="";
      File infile=new File ("in.txt");
      Thread t=null;
      System.out.println("Driver: BEGIN!");
      Thread[] threads=new Thread[0];
      try {
         sc=new Scanner(infile);
         String line=sc.nextLine();
         read=new Scanner(line);
         String title=read.next() ;
         if (title.equalsIgnoreCase("T"))
         {
         // No of threads 
            noOfThread=Integer.parseInt(read.next());
            threads= new Thread[noOfThread];           
         }
         while(sc.hasNextLine())
         {
            line =sc.nextLine();
            read=new Scanner(line);
            title=read.next() ;
            if ( title.equalsIgnoreCase("F"))
            {
               while( count <noOfThread)
               {
                  String rFile=read.next();
                  String mFile="t"+Integer.toString(count)+"_out.txt";
                  // Make array of objects 
                  threads[count]=new SUBEDI_RAJ_MyThread(rFile,mFile, ""+ count);
                  finalOut+=mFile+" ";
                  count++;
               }
               // Starts the thread 
               for(int i=0; i<noOfThread; i++) {
                  threads[i].start();
               }
               // Kill the alive threads 
               for(int i=0; i<noOfThread; i++)
               {
                  if (threads[i].isAlive())
                  {
                     threads[i].join();
                  }
               }
                                                   
            }           
         }
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
      finally {
         if(sc!=null){
            sc.close();
         }
      }
      /************************************************
       Call the output function to print the output.txt
       ***********************************************/
      OutputPrint(finalOut);
   
      System.out.println("Driver: END!");       
   }
/**
    * A Void method that read the each files each line one at a time and write it in the out.txt 
    * @param filename A string file which contains the information about the file. 
    */

   public static void OutputPrint(String filename)
   {
      PrintWriter writer;
      int maxLine=0,currentLine =0;
      String output = null;
      BufferedReader in = null;
      try{
         writer= new PrintWriter("out.txt");
         read=new Scanner(filename);
          
         /**********************************************
           This while loop find the maximum no of line
          *********************************************/
         while (read.hasNext())
         {
            BufferedReader reader = new BufferedReader(new FileReader(read.next()));
            int line = 0;
            while (reader.readLine() != null)
            {
               line++;
               if (line>maxLine)
               {
                  maxLine=line;
               }
            }
            reader.close();
         }
         /*******************************************************
          For loop runs until all the line is write in output.txt
                      after that it will stop 
          *******************************************************/
         for (int i=0;i<=maxLine; i++)
         {
            int startLine=i;
            int endLine=i;
            read=new Scanner(filename);
            /*****************************************************
             This loop write the specified line in the output.txt
             ****************************************************/
            while (read.hasNext())
            {
               String firstFile=read.next();
               in = new BufferedReader (new FileReader(firstFile));
               /************************************************************
                 Remove other line until we find the line we are looking for
                ************************************************************/ 
               while(currentLine<startLine)
               {
                  if (in.readLine()==null)
                  {         
                     break;
                  }
                  currentLine++;
               }  
               /**************************************
                Write the exact line what need to write
                **************************************/ 
               while(currentLine<=endLine) {
                  output = in.readLine();
                  if (output==null)
                  {
                  
                     break;
                  }   
                  //System.out.println(output);  
                  writer.println(output);   
                  currentLine++;
               }
               currentLine=0;
            }
         }
         writer.close();
      }
      catch(FileNotFoundException e)
      {
         System.out.println("Alert:");
         e.printStackTrace();
      }
      catch (Exception e)
      {
         System.out.print("Alert: "+e.getMessage());
         e.printStackTrace();
      }
      finally {
         if(read!=null){
            read.close();
         }
      }
   }
}