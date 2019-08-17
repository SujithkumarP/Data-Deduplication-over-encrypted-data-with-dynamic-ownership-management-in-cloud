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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sujith
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class FileCheckSumSHA {

  
    public static String checksum(String filepath,int d) throws IOException, NoSuchAlgorithmException, SQLException {

        // file hashing with DigestInputStream
        MessageDigest md = MessageDigest.getInstance("SHA-256"); //SHA, MD2, MD5, SHA-256, SHA-384...
        //String hex = checksum("F:\\Cloud\\file.jpg", md);
       // System.out.println(hex);
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
            while (dis.read() != -1) ; //empty loop to clear the data
            md = dis.getMessageDigest();
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        if(d==1)
        {
            return result.toString();
        }
        else
        {
        insert as=new insert();
        as.login(1,result.toString());
        return result.toString();
        }
    }

}