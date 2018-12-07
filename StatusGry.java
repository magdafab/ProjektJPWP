/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 *
 * @author Magda
 */
public class StatusGry {
    
    public int liczbapunktow;

    public int poziom;
 
    public double czas; //czas gry na poziom
    
    public void resetowaniegry(){
        liczbapunktow=0;
        poziom=1;
        czas=0.0;
    }
  
    public void resetowaniepunktow(){
        liczbapunktow=0;
    }

    public void nastepnypoziom(){
        poziom++;
    }

    
}
