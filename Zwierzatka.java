/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.*;
import java.io.*;

/**
 *
 * @author Magda
 */
public class Zwierzatka {
    
    public int poczx;

    public int poczy;

    public int obecnX;

    public int obecnY;

    public int szerok;

    public int wysok;
    
    public int dx;

    //private double angle;

   // public int ampl;

   // public double freq;

    public int rodzaj;

    //public final static double w=2*Math.PI;

    public int szerokoscpola;

    public int wysokoscpola;
    
    public boolean trafiony;

    public Image obrazek;
    
    public Zwierzatka(int poczx, int poczy, Image[] obrazki){
        this.poczx=poczx;
        this.poczy=poczy;
        obecnX=poczx;
        obecnY=poczy;
        this.dx=10;
        szerokoscpola=1024;
        wysokoscpola=768;
        trafiony=false;
        
        //this.ampl=ampl;
        //this.freq=freq;
        //losujemy zwierzaki
        rodzaj=(int)(0.4+Math.random()*obrazki.length);
        if(rodzaj>=obrazki.length) rodzaj=obrazki.length-1;
        obrazek=obrazki[rodzaj];
        szerok=obrazek.getWidth(null);
        wysok=obrazek.getHeight(null);
       
        //setOmega(this.freq);
    }
    
    public void trafionobalon(){
        if(!trafiony){
            trafiony=true;
            //playSound(new File("sounds/balloon_boom.wav"));
        }
    }
    
    public void ustawpozycje(int cX, int cY){
        obecnX=cX;
        obecnY=cY;
    }
    
    public void ustawrozmiarobrazu(int gWidth, int gHeight){
       szerokoscpola=gWidth;
       wysokoscpola=gHeight;
    }

    public void przesunox(int cX){
        obecnX=cX;
    }    
    
    public Point pobierzpozycjeobiektu(){
        return new Point(obecnX,obecnY);
    }
    
    public void obliczdroge(){
       //liniowo
        obecnX=obecnX+dx;
        if(obecnX>szerokoscpola) { 
            obecnX=0;
        }
    }
    
    public boolean porownaniewspolrzednych(int x, int y){
       
        scaleWidthHeight((double)szerokoscpola);
        if(y>=obecnY && y<obecnY+wysok){
            if(x>=(szerokoscpola-obecnX) && x<(szerokoscpola-obecnX+szerok)){
                return true;
            }
        }
        
        return false;
    }
    
    public void scaleWidthHeight(double scale){
        szerok=(int)(obrazek.getWidth(null)*(1.0-obecnX/scale));
        wysok=(int)(obrazek.getHeight(null)*(1.0-obecnY/scale));
    }
     
     
}
