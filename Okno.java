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
public class Okno extends JFrame{
    
    
    public Okno(int szerokosc, int wysokosc, int x, int y){
        super();
        setSize(szerokosc,wysokosc);
        setResizable(false);
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        stworzGUI(szerokosc,wysokosc);
        //setUndecorated(true); //ukryj ramkÄ™ okna i przyciski kontrolne
        //animationLoop(); //uruchom pÄ™tlÄ™ animacji gry
}
    
    private void stworzGUI(int szerokosc, int wysokosc){
        setLayout(new GridLayout(1,1));
        Obrazki.loadInitialImages();
        //Toolkit tk = Toolkit.getDefaultToolkit();
        add(new OknoGry(szerokosc,wysokosc));
    }
}
