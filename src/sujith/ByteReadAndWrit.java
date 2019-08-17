/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sujith;

/**
 *
 * @author Sujith
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.zip.CRC32;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import static sujith.Split.splitFile;


public class ByteReadAndWrit {

   static  Hashtable h=new Hashtable();
    
    static int i=0;
    /**
     *
     * @param SourceFileName
     * @param CHUNK_SIZE
     * @return
     * @throws IOException
     */
    public static ArrayList<String> readAndFragment ( String SourceFileName, int CHUNK_SIZE ) throws IOException, NoSuchAlgorithmException, SQLException
 {
  //log ("File Is Reading "+ SourceFileName );
  File willBeRead = new File ( SourceFileName );
  int FILE_SIZE = (int) willBeRead.length();
  ArrayList<String> nameList = new ArrayList<String> ();
  
  System.out.println("Total File Size: "+FILE_SIZE);
  FileCheckSumSHA ss=new FileCheckSumSHA();
  ss.checksum(SourceFileName,0);
  
  int NUMBER_OF_CHUNKS = 0;
  byte[] temporary = null;
  
  try {
   InputStream inStream = null;
   int totalBytesRead = 0;
   
   try {
    inStream = new BufferedInputStream ( new FileInputStream( willBeRead ));
    
    while ( totalBytesRead < FILE_SIZE )
    {
     String PART_NAME ="data"+NUMBER_OF_CHUNKS+".bin";
     int bytesRemaining = FILE_SIZE-totalBytesRead;
     if ( bytesRemaining < CHUNK_SIZE ) // Remaining Data Part is Smaller Than CHUNK_SIZE
               // CHUNK_SIZE is assigned to remain volume
     {
      CHUNK_SIZE = bytesRemaining;
      System.out.println("CHUNK_SIZE: "+CHUNK_SIZE);
     }
     temporary = new byte[CHUNK_SIZE]; //Temporary Byte Array
     int bytesRead = inStream.read(temporary, 0, CHUNK_SIZE);
    String hash=getSHA256Hash(temporary);
    System.out.println(hash);
    h.put(++i,hash);
    ins s=new ins();
    s.login(i,hash);
    
    System.out.println(h);
     if ( bytesRead > 0) // If bytes read is not empty
     {
      totalBytesRead += bytesRead;
      NUMBER_OF_CHUNKS++;
     }
     
     write(temporary, "D://"+PART_NAME);
     nameList.add("D://"+PART_NAME);
     System.out.println("Total Bytes Read: "+totalBytesRead);
    }
    
   }
   finally {
    inStream.close();
   }
  }
  catch (FileNotFoundException ex)
  {
   ex.printStackTrace();
  }   
  catch (IOException ex)
  {
   ex.printStackTrace();
  }
  return nameList;
 }
 
 static void  write(byte[] DataByteArray, String DestinationFileName){
    try {
      OutputStream output  = null;
      try {
        output = new BufferedOutputStream(new FileOutputStream(DestinationFileName));
        output.write( DataByteArray );
        System.out.println("Writing Process Was Performed");
      }
      finally {
        output.close();
      }
    }
    catch(FileNotFoundException ex){
     ex.printStackTrace();
      System.out.println("Writing Process hhh Was Performed");
    }
    catch(IOException ex){
     ex.printStackTrace();
      System.out.println("Writing Process  ssss Was Performed");
    }
 }
 
 public static void mergeParts ( ArrayList<String> nameList, String DESTINATION_PATH )
 {
     
      System.out.println("Writingwfefefefe Process Was Performed");
  File[] file = new File[nameList.size()];
  byte AllFilesContent[] = null;
  
  int TOTAL_SIZE = 0;
  int FILE_NUMBER = nameList.size();
  int FILE_LENGTH = 0;
  int CURRENT_LENGTH=0;
  
  for ( int i=0; i<FILE_NUMBER; i++)
  {
   file[i] = new File (nameList.get(i));
   TOTAL_SIZE+=file[i].length();
  }
  
  try {
   AllFilesContent= new byte[TOTAL_SIZE]; // Length of All Files, Total Size
   InputStream inStream = null;
   
   for ( int j=0; j<FILE_NUMBER; j++)
   {
    inStream = new BufferedInputStream ( new FileInputStream( file[j] ));
    FILE_LENGTH = (int) file[j].length();
    inStream.read(AllFilesContent, CURRENT_LENGTH, FILE_LENGTH);
    CURRENT_LENGTH+=FILE_LENGTH;
    inStream.close();
   }
   
  }
  catch (FileNotFoundException e)
  {
   System.out.println("File not found " + e);
  }
  catch (IOException ioe)
  {
   System.out.println("Exception while reading the file " + ioe);
  }
  finally 
  {
   write (AllFilesContent,DESTINATION_PATH);
  }
  
  System.out.println("Merge was executed successfully.!");
  
 }
 
 private static String getSHA256Hash(byte[] data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash;
            hash = digest.digest(Arrays.toString(data).getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
  private static  String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
 
 
 public static void main(String[] args) throws IOException,NoSuchAlgorithmException, SQLException {
     ArrayList<String> read;
     String key = "Mary has one CAT";
              System.out.println("1.Edit 2.Upload");
              Scanner s=new Scanner(System.in);
              int gg=s.nextInt();
              if(gg==2)
              {
              
                String fi="F:\\Cloud\\p2.c";String extension="";
		File inputFile = new File(fi);
                //String extension=Files.getFileExtension(new File(fi));
                int i=fi.lastIndexOf('.');
                if(i>=0)
                   extension =fi.substring(i+1);
                System.out.println(extension);
		File encryptedFile = new File("F:\\Cloud\\doc.encrypted");
		File decryptedFile = new File("F:\\Cloud\\doc."+extension);
		
		try {
			CryptoUtils.encrypt(key, inputFile, encryptedFile);
			CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
		} catch (CryptoException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace(); 
		}
     read = readAndFragment("F:\\Cloud\\doc.encrypted",(1024*1024));
     mergeParts(read,"F:\\Cloud\\doco001.encrypted");
              }
              else
              {
                  String ssd;
                  
                  System.out.println("enter the file name :");
                  ssd=s.next();
                   
                  FileCheckSumSHA as=new FileCheckSumSHA ();
                  String st1=as.checksum("F:\\Cloud\\"+ssd,1);
                  System.out.println("\n\n\n"+st1);
                  openn o=new openn();
             
                  int nn=o.editt(ssd);
                  if(nn == 1)
                  {
                      insert aaa=new insert();
                      aaa.delete(st1);
                      as.checksum("F:\\Cloud\\"+ssd,0);
                  
                  }
                   
                  
              }
    }

}