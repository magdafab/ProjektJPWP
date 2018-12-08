/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Magda
 */
public class Obrazki {

    public static long czasgry=Long.MAX_VALUE;

    public final static long liczbapoziomow=3;
 
    public static Image obraztla;

    //public static Image menuImage;
  
    public static Image menuobrazek;
 
    //public static Image logoImage;
  
    //public static Image cursorImage;
 
    public static Image[] zwierzatka;
    
    public static boolean pauza=false;
    
    //public static boolean levelPause=false;
    
    public static long startgryczas;
    
    public static int aktualnypoziom=1;
    
    public static boolean koniecgry=false;
    
    public static int maxliczbaobiektow=12;
    
    public static int szerokoscpola=1024;
    
    public static int wysokoscpola=768;
    
    
    public static void loadInitialImages() {
        
        obraztla = loadImage("obrazy/tlogry1.jpg");
        //menuImage=loadImage("images/menu_1024.png");
        menuobrazek =loadImage("obrazy/menugra.png");
        //logoImage=loadImage("images/domestic_logo_url.png");
        //cursorImage=loadImage("images/target_32_r.png");


        zwierzatka= new Image[3];
        zwierzatka[0]=loadImage("obrazy/ptakmaly.png");
        zwierzatka[1]=loadImage("obrazy/rybamala.png");
        zwierzatka[2]=loadImage("obrazy/wiewiorkamala.png");
       // balloons[3]=loadImage("images/b_brown_300.png");
       // balloons[4]=loadImage("images/b_green_300.png");
    }
    
    public static Image loadImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }
    
}
