/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sujith;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sujith
 */
public class Split {

    public static void splitFile(File f) throws IOException {
        int partCounter = 1;//I like to name parts from 001, 002, 003, ...
                            //you can change it to 0 if you want 000, 001, ...

        int sizeOfFiles = 1024 * 1024;// 1MB
        byte[] buffer = new byte[sizeOfFiles];

        String fileName = f.getName();

        //try-with-resources to ensure closing stream
        try (FileInputStream fis = new FileInputStream(f);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fileName, partCounter++);
                File newFile = new File(f.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                }
            }
        }
    }
    
    public static List<File> listOfFilesToMerge(File oneOfFiles) {
    String tmpName = oneOfFiles.getName();//{name}.{number}
    String destFileName = tmpName.substring(0, tmpName.lastIndexOf('.'));//remove .{number}
    File[] files = oneOfFiles.getParentFile().listFiles(
            (File dir, String name) -> name.matches(destFileName + "[.]\\d+"));
    Arrays.sort(files);//ensuring order 001, 002, ..., 010, ...
    return Arrays.asList(files);
}

    public static void main(String[] args) throws IOException {
        splitFile(new File("F:\\Cloud\\The Maari Swag - TamilTunes.com.mp3"));
    }
    
}
