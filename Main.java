import javax.swing.*;
import java.awt.*;

/**
 * Glowna klasa programu
 * Zawiera stale reprezentujace domyslne parametry
 * @author Magdalena Fabich
 */
public class Main
{
	/** Domyslna wysokosc panelu graficznego */
	public static final int WIDTH=1024;
	/** Domyslna szerokosc panelu graficznego*/
	public static final int HEIGHT=768;
	/** Domyslna wysokosc paska statusu */
	public static final int STATUS_HEIGHT = 50;
	/** Domyslny tytul okna */
	public static final String WINDOW_TITLE = "ZWIERZATKA";
	/** Tablica assetow graficznych */
	public static final Image[] images = new Image[Animal.values().length];
	/** Domyslna wysokosc i szerokosc sprite'a */
	public static final int IMG_SIZE = 150;
	/** Domyslna liczba sprite'ow w wierszu siatki */
	public static final int SPRITES_IN_ROW = 4;
	/** Domyslna liczba sprite'ow w klomunie siatki */
	public static final int SPRITES_IN_COL= 3;
	/** Domyslna liczba poziomow */
	public static final int LEVELS = 3;
	/** Domyslny modyfikator predkosci */
	public static final double ALPHA = 2;

	/**
	 * Glowna metoda programu
	 * Laduje zasoby i uruchamia gre
	 * @param args parametry wywolania programu - nieuzywane
	 */
	public static void main(String[] args)
	{
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

		//wspolrzedne srodka ekranu
		int x = (screenWidth - WIDTH) / 2;
		int y = (screenHeight - HEIGHT) / 2;

		//Ladowanie zasobow graficznych
		for (Animal a : Animal.values())
			images[a.getId()] = new ImageIcon(a.getFileName()).getImage().getScaledInstance(IMG_SIZE, IMG_SIZE, Image.SCALE_DEFAULT);

		//Utworzenie nowego okna gry i nowej instancji gry
		new GameWindow(WIDTH, HEIGHT, STATUS_HEIGHT, x, y, WINDOW_TITLE, new Game());
	}
}
