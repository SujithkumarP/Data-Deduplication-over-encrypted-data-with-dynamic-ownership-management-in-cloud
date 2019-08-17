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


import java.io.File;


public class CryptoUtilsTest {
	public static void main(String[] args) {
		String key = "Mary has one CAT";
                String fi="F:\\Cloud\\file.jpg";String extension="";
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
	}
}

