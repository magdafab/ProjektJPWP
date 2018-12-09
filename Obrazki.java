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
  
    public static Image menuobrazek;
 
    public static Image[] zwierzatka;
    
    public static boolean pauza=false;
    
    public static long startgryczas;
    
    public static int aktualnypoziom=1;
    
    public static boolean koniecgry=false;
    
    public static int maxliczbaobiektow=12;
    
    public static int szerokoscpola=1024;
    
    public static int wysokoscpola=768;
    
    
    public static void loadInitialImages() {
        
        obraztla = loadImage("obrazy/tlogry2.jpg");
        menuobrazek =loadImage("obrazy/menugra.png");


        zwierzatka= new Image[3];
        zwierzatka[0]=loadImage("obrazy/ptakmaly.png");
        zwierzatka[1]=loadImage("obrazy/rybamala.png");
        zwierzatka[2]=loadImage("obrazy/wiewiorkamala.png");

    }
    
    public static Image loadImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }
    
}
