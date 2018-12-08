/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import javax.swing.*;
import java.awt.*;
import java.text.*;

/**
 *
 * @author Magda
 */
public class OknoGry extends JPanel {
    
    public int wysokoscpola;
    public int szerokoscpola;
    public int liczbaobiektow; //liczba w jednej lini
    public int przesuniecie; //przesuniecie miedzy liniami z obiekatami
    public StatusGry statusg;
    public int wysokoscmenu;
    public Font czcionkamenu;
    //public Font alertFont;
    private Zwierzatka [] zwierz;
    
        public OknoGry(int szerokosc, int wysokosc){
        
        statusg=new StatusGry();
        statusg.resetowaniegry();
        czcionkamenu=new Font("Dialog",Font.BOLD,36);
        //alertFont=new Font("Dialog",Font.BOLD,92);
        
        this.szerokoscpola=szerokosc;
        this.wysokoscpola=wysokosc;
        wysokoscmenu=50;
        
        liczbaobiektow=4;
        przesuniecie=wysokoscpola/(Obrazki.maxliczbaobiektow/liczbaobiektow);
        zwierz = new Zwierzatka[Obrazki.maxliczbaobiektow];
        
        
        startgry();
        }
    
        
        
    protected void paintComponent(Graphics gs){
        Graphics2D g=(Graphics2D)gs;
        //Ustaw tryb lepszej jakoĹ›ci grafiki (wygĹ‚adzanie/antyaliasing)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Narysuj tĹ‚o
        g.drawImage(Obrazki.obraztla, 0, 0, null);

        //Na tle obiektu pierwszego planu
        for(int i=0;i<zwierz.length;i++){
            zwierz[i].obliczdroge();
            if(!zwierz[i].trafiony)
                g.drawImage(zwierz[i].obrazek,zwierz[i].obecnX,wysokoscpola-zwierz[i].obecnY,(int)(zwierz[i].obrazek.getWidth(null)*(1.0-zwierz[i].obecnY/(double)wysokoscpola)), (int)(zwierz[i].obrazek.getHeight(null)*(1.0-zwierz[i].obecnY/(double)wysokoscpola)),null);
        }

       
        g.setColor(new Color(50,30,0));
        g.fillRect(0, wysokoscpola-wysokoscmenu, szerokoscpola, wysokoscmenu);
        //Ustaw kolor domyĹ›lny
        g.setColor(Color.white);
        //Ustaw czcionki do wypeĹ‚nienia paska Menu
        g.setFont(czcionkamenu);
          g.drawImage(Obrazki.menuobrazek,szerokoscpola-150,wysokoscpola-wysokoscmenu-30,null);
            g.setColor(Color.red);
            g.drawString("KONIEC GRY!",10,wysokoscpola-10);
            g.setColor(Color.white);
            g.drawString("O GRZE...",300, wysokoscpola-10);
            g.drawString("NOWA GRA!",550, wysokoscpola-10);
    }
    

    
    private void startgry(){
        statusg.resetowaniepunktow();
        Obrazki.startgryczas=System.currentTimeMillis();
        Obrazki.pauza=false;
        int offset=szerokoscpola/liczbaobiektow; 
        int inLine=0;    
        int yLine=0;
        for(int i=0; i<Obrazki.maxliczbaobiektow;i++){
         
            zwierz[i]=new Zwierzatka((((inLine%liczbaobiektow)+1)*offset)-Obrazki.zwierzatka[(i%Obrazki.zwierzatka.length)].getWidth(null),0,Obrazki.zwierzatka);
            zwierz[i].ustawrozmiarobrazu(szerokoscpola, wysokoscpola);

            if(inLine>=liczbaobiektow){
                yLine++;
                inLine%=liczbaobiektow;
            }
            inLine++;
            zwierz[i].przesunox(yLine*przesuniecie*-1);
        }
        
    }
    
}

    
