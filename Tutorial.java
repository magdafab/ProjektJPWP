import javax.swing.*;
import java.awt.*;

/**
 * Klasa reprezentujaca okienko z podpisami dla obrazkow zwierzat
 * @author Magdalena Fabich
 */
public class Tutorial extends JFrame
{
	/**
	 * Konstruktor klasy
	 * Inicjializuje okienko, wyswietla w siatce wszystkie obrazki
	 * W danym rzedzie znajduja sie zwierzeta danego typu + ich podpis
	 * @param x pozycja x lewego gornego naroznika okienka
	 * @param y pozycja y lewego gornego naroznika okienka
	 */
	public Tutorial(int x, int y)
	{
		super(Main.WINDOW_TITLE + " - Tutorial");

		Font font = new Font("Dialog", Font.BOLD, 24);
		JLabel[] labels = new JLabel[Animal.values().length];
		setLayout(new GridLayout(Animal.values().length, Animal.MAMMAL.getFileNames().length + 1));

		for(Animal a : Animal.values())
		{
			labels[a.getId()] = new JLabel(a.toString());
			labels[a.getId()].setFont(font);
			add(labels[a.getId()]);

			for(int i = 0; i < a.getFileNames().length; i++)
				add(new JLabel(new ImageIcon(Main.images[a.getId()][i])));

		}
		pack();

		setLocation(x, y);
		setResizable(false);
		setVisible(true);
	}
}
