/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sujith;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class openn {
  public  int  editt(String ssd){
      
    try {
      Desktop desktop = null;
      if (Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }

       desktop.open(new File("F:\\Cloud\\"+ssd));
       System.out.println("Is file edited 1.Y 2.N");
       Scanner s=new Scanner(System.in);
              int ss=s.nextInt();
              
              return ss;
              
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
      return 0;

  }
}
