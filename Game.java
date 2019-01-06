import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Klasa odpowiadajaca za przetwarzanie logiki gry
 * @author Magdalena Fabich
 */
public class Game
{
	/** Czas trwania jednego poziomu w ms */
	private long levelTime;
	/** Liczba poziomow */
	private int levels;
	/** Modyfikator predkosci na wyzszym poziomie */
	private double alpha;
	/** Typ zwirzecie, ktorego trafienie da graczowi punkty */
	private Animal gameMode;

	/** aktualnie rozgrywany poziom */
	private int actualLevel;
	/** Ilosc punktow na danym poziomie i sumaryczna liczba punktow */
	private int points, total;
	/** Informacja o tym, czy gra jest aktywna; gra nieaktywna => koniec gry */
	private boolean active;
	/** Informacja o pauze; pauza => wstrzymanie oblsugi logiki*/
	private boolean pause;
	/** Tablica sprite'ow, ktore nalezy aktualizowac i rysowac*/
	private Sprite[] sprites;

	/** Sluchacz zdarzen, obiekt ktory nalezy powiadomic o zmianie stanu gry (liczba punktow, poziom itp.)*/
	private ActionListener statusChangeListner;

	/** Czas rozpoczecia poziomu i aktualny czas*/
	private long startTime, currentTime;

	/**
	 * Konstruktor klasy
	 * Ustawia wymagane parametry i laduje nowa gre
	 */
	public Game()
	{
		this.levelTime = levelTime;
		this.levels = Main.LEVELS;
		this.alpha = Main.ALPHA;
		this.gameMode = gameMode;
		active = true;
		actualLevel = 1;

		sprites = new Sprite[Main.SPRITES_IN_ROW * Main.SPRITES_IN_COL];

		newGame();
	}

	/**
	 * Metoda odpowiedzialna za aktualizacje stanu gry
	 */
	public void update()
	{
		long timeElapsed = System.currentTimeMillis() - currentTime;
		double delta = timeElapsed / 1000.0;
		if(!pause && currentTime - startTime < levelTime)
		{
			for (Sprite s : sprites)
				s.update(delta);
			currentTime += timeElapsed;
		}
		else
		{
			pause = true;
			statusChangeListner.actionPerformed(null);
		}
	}

	/**
	 * @return true jesli gra jest aktywna
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * @return liczba punktow uzyskanych przez gracza
	 */
	public int getPoints()
	{
		return points;
	}

	/**
	 * @return nr aktualnie rozgrywanego poziomu
	 */
	public int getActualLevel()
	{
		return actualLevel;
	}
	/**
	 * @return sumaryczna liczba punktow
	 */
	public int getTotal()
	{
		return total;
	}

	/**
	 * @return typ zwierzecia, ktorego trafienie daje punkty w danej rozgrywce
	 */
	public Animal getGameMode()
	{
		return gameMode;
	}

	/**
	 * Metoda ustawiajaca sluchacza zdarzen
	 * @param actionListener sluchacz, ktorego nalezy powiadomic o zmianie stanu gry
	 */
	public void setStatusChangeListner(ActionListener actionListener)
	{
		this.statusChangeListner = actionListener;
	}

	/**
	 * Metoda odpowiedzialan za rysowanie obiektow gry
	 * @param g2d obiekt odpowiedzialny za rysowanie
	 */
	public void draw(Graphics2D g2d)
	{
		for(Sprite s : sprites)
			s.draw(g2d);
	}

	/**
	 * Metoda przejmujaca oblusge klikniecia
	 * @param x pozycja X klikniecia
	 * @param y pozycja Y klikniecia
	 */
	public void click(int x, int y)
	{
		if(pause || !active)
			return;

		//domyslne zachowanie, odjecie 1 punktu; zalozenie, ze gracz nie trafil
		points -= 1;
		total -= 1;

		int p = 0;
		for(Sprite s : sprites)
			p += s.hit(x, y, gameMode); //jezeli trafil, gracz otrzyma 2 punkty, nagroda uwzglednia poczatkowa strate, wiec w rezultacie gracz zyskuje 1 punkt
		total += p;
		points += p;
		//powiadom sluchacza
		statusChangeListner.actionPerformed(null);

	}

	/**
	 * Metoda opdowiedzialna za wejscie na wyzszy poziom trudnosci
	 */
	public void levelUp()
	{
		actualLevel++;
		for(Sprite s : sprites)
			s.modifySpeed(alpha);
	}

	/**
	 * @return informacja mozliwe jest uruchomienie kolejnego poziomu
	 */
	public boolean isNextLevelAvailable()
	{
		return  pause && actualLevel < levels;
	}

	/**
	 * Rozpoczecie gry
	 * Zeruje liczbe punktow, ustawia czas rozpoczecia gry
	 * Powiadamia sluchacza
	 */
	public void start()
	{
		points = 0;
		pause = false;
		startTime = currentTime = System.currentTimeMillis();
		statusChangeListner.actionPerformed(null);
	}

	/**
	 * Pauzuje przetwarzenie logiki
	 * nie pauzuje uplywu czasu
	 */
	public void setPause()
	{
		pause = true;
	}

	/**
	 * Metoda odpowiedzialana za pobranie od gracza czasu gry i trybu rozgrywki oraz przygotowanie nowej gry
	 */
	public void newGame()
	{
		JTextField levelTimeField = new JTextField("30");
		JRadioButton mammalButton = new JRadioButton(Animal.MAMMAL.toString());
		JRadioButton fishButton = new JRadioButton(Animal.FISH.toString());
		JRadioButton birdButton = new JRadioButton(Animal.BIRD.toString());
		ButtonGroup radioGroup = new ButtonGroup();
		mammalButton.setSelected(true);
		radioGroup.add(mammalButton);
		radioGroup.add(fishButton);
		radioGroup.add(birdButton);
		Object[] message = {
				"Czas gry [s]", levelTimeField,
				"Ssak", mammalButton,
				"Ryba", fishButton,
				"Ptak", birdButton
		};

		JOptionPane.showMessageDialog(null, message);

		if(fishButton.isSelected())
			gameMode = Animal.FISH;
		else if (birdButton.isSelected())
			gameMode = Animal.BIRD;
		else if (mammalButton.isSelected())
			gameMode = Animal.MAMMAL;

		//Zamiana czasu w s na czas w ms
		levelTime = Integer.parseInt(levelTimeField.getText()) * 1000;

		// inicjalizacja sprite'ow, uwzglednia losowe przesuniecie wzdluz OY
		int dY;
		Random generator = new Random();
		for(int x = 0; x < Main.SPRITES_IN_ROW; x++)
		{
			dY = generator.nextInt(Main.IMG_SIZE) + 1;
			for (int y = 0; y < Main.SPRITES_IN_COL; y++)
				sprites[x * Main.SPRITES_IN_COL + y] = new Sprite(x * Main.WIDTH / Main.SPRITES_IN_ROW, y * Main.HEIGHT / Main.SPRITES_IN_COL + dY);
		}
		total = 0;
	}
}
