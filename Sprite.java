import java.awt.*;
import java.util.Random;

/**
 * Klasa reprezentujaca sprite'a w grze
 * Sprite zawiera pozycje na ekranie, polozenie do ktorego ma wrocic, predoksc, informacje o widocznosci i typie zwierzecia
 * @author Magdalena Fabich
 */
public class Sprite
{
	/** Typ reprezentowanego zwierzecia */
	private Animal type;

	/** Pozycja na ekranie - double do dokladniejszych obliczen przemieszczenia obiektu */
	private double posX, posY;
	/** Wysokosc i szerokosc sprite */
	private final int width, height;
	/** Polozenie na OY do ktorego obiekt ma wrocic po wyjsciu za ekran */
	private final int origin;
	/** Domyslna predkosc [px/s], minus oznacza, ze skierowana w gore ekranu */
	private double speedY = -200;
	/** Zmienna reprezentujaca czy obiekt jest widoczny */
	private boolean visible;

	/**
	 * Konstruktor obiektu
	 * Ustawienie wymaganych parametrow klasy
	 * @param posX polozenie X na ekranie
	 * @param posY polozenie Y na ekranie
	 */
	public Sprite(double posX, double posY)
	{
		type = Animal.randomAnimal();
		this.width = Main.IMG_SIZE;
		this.height = Main.IMG_SIZE;
		this.origin = Main.HEIGHT;
		this.posX = posX;
		this.posY = posY;
		visible = true;

	}

	/**
	 * Metoda modyfyfikujaca predkosc obiektu
	 * @param alpha modyfikator predkosci
	 */
	public void modifySpeed(double alpha)
	{
		speedY *= alpha;
	}

	/**
	 * Metoda aktualizujaca polozenie obiektu
	 * Jezeli obiekt wyjechal za ekran przeniosi go do wskazanego w konstruktorze polozenia, zmieniany jest typ zwierzecia w celu zapewnienia wiekszej dynamiki rozgrywki
	 * @param time czas jaki uplynal od ostatniej aktualizacji [s]
	 */
	public void update(double time)
	{
		posY += speedY * time;

		if(posY + height <= 0)
		{
			visible = true;
			posY = origin;
			type = Animal.randomAnimal();
		}
	}

	/**
	 * Metoda rysujaca sprite'a na ekranie
	 * @param g2d obiekt odpowiedzalny za narysowanie sprite'a
	 */
	public void draw(Graphics2D g2d)
	{
		if(visible)
			g2d.drawImage(Main.images[type.getId()], (int)posX, (int)posY, null);
	}

	/**
	 * Metoda sprawdzajaca czy sprite zostal klikniety
	 * @param x polozenie x klikniecia myszy
	 * @param y polozenie y klikniecia myszy
	 * @param gameMode typ zwierzecia, ktorego trafienie daje punkty
	 * @return ilosc punktow
	 */
	public int hit(int x, int y, Animal gameMode)
	{
		if((int)posX <= x && (int)posX + width >= x
			&& (int)posY <= y && (int)posY + height >= y
			&& visible)
		{
			//Jezeli sprite'a zostal klinkniety to przestan go wyswietlac
			visible = false;
			if(type == gameMode)
				//Domyslne zachowanie klikniecia to odjeciecie 1 punktu, zwrocenie 2 punktow rekomensuje domsylna strate
				return 2;
		}

		return 0;
	}
}
