import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Klasa odpowiedzalna za wyswietlanie rozgrywki
 * @author Magdalena Fabich
 */
public class GamePanel extends JPanel
{
	/** Kolor tla */
	private final Color color;
	/** instancja klasy odpowiadajacej za przetwarzanie logiki gry */
	private final Game game;
	/**
	 * Konstruktor klasy pola graficznego gry
	 * Dodanie oblsugi klikniecia myszy
	 * @param width szerokosc panelu gry
	 * @param height wysokosc panelu gry
	 * @param g instancja klasy odpowiadajacej za przetwarzanie logiki gry
	 */
	public GamePanel(int width, int height, Game g)
	{
		game = g;
		color = new Color(255,230,230);
		setPreferredSize(new Dimension(width, height));

		//Obsluga myszy, oblsuga zdarzenia mouseRelesed okazuje sie byc bardziej responsywna niz mousePressed
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) //bardziej responsywne od mouseClicked
			{
				//Sprawdzenie czy lewy przycisk myszy
				if(e.getButton() == MouseEvent.BUTTON1)
					//Przekazanie oblsugi zdarzenia grze
					game.click(e.getX(), e.getY());
			}
		});
	}

	/**
	 * Metoda odpwiadajaca za wyswietlanie grafiki
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		//Wlaczenie antyaliasingu
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//Rysowanie tla
		g2d.setColor(color);
		g2d.fillRect(0,0, getWidth(), getHeight());

		//Przekazanie obslugi rysowania grze
		game.draw(g2d);
	}
}
