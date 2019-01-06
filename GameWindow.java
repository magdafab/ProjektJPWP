import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiadajaca za obsluge okienka gry i glownej petli programu
 *
 * @author Magdalena Fabich
 */
public class GameWindow extends JFrame
{
	/** instancja klasy odpowiadajacej za przetwarzanie logiki gry*/
	private final Game game;
	/**
	 * Konstruktor klasy
	 * Ustawienie parametrow i rozpoczecie petli programu
	 * @param width szerokosc okienka
	 * @param gameHeight wysokosc panelu gry
	 * @param statusHeight wysokosc paska statusu
	 * @param x pozycja x lewego gornego naroznika okienka
	 * @param y pozycja y lewego gornego naroznika okienka
	 * @param windowTitle tytul okienka
	 * @param g instancja klasy odpowiadajacej za przetwarzanie logiki gry
	 */
	public GameWindow(int width, int gameHeight, int statusHeight, int x, int y, String windowTitle, Game g)
	{
		super(windowTitle);

		//Rozmieszczenie elementow interfejsu
		setLayout(new BorderLayout());
		add(new GamePanel(width, gameHeight - statusHeight, g), BorderLayout.CENTER);
		add(new StatusPanel(width, statusHeight, g), BorderLayout.SOUTH);
		pack();

		game = g;

		//Ustawienie wlasciwosci okienka
		setLocation(x, y);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		//ropoczecie petli programu
		gameLoop();
	}

	/**
	 * Metoda zawierajaca glowna petle gry
	 * Cyklicznie przetwarza logike gry i odswieza obraz
	 */
	private void gameLoop()
	{
		game.start();
		while(game.isActive())
		{
			game.update();
			repaint();
		}
	}
}
