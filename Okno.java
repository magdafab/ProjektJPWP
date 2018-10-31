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
    
    
    Okno(String nazwaOkna){
        super(nazwaOkna);
        setSize(800,200);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p = new JPanel(new FlowLayout());
        JProgressBar pasek = new JProgressBar();
        pasek.setMinimum(5);
        JLabel etykieta = new JLabel("Wynik to: ");
 
        p.add(pasek);
        p.add(etykieta);
        add(p, BorderLayout.NORTH);
    
}
}
