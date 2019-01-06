import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiadajaca za wyswietlanie informacji o stanie gry
 * @author Magdalena Fabich
 */
public class StatusPanel extends JPanel
{
	/** Etykieta informacji o aktualnie rozgrywanym poziomie */
	private final JLabel levelLabel;
	/** Etykieta informacji o zdobytych punktach w czasie danego poziomu */
	private final JLabel pointsLabel;
	/** Etykieta informacji o somue zdobytych punktach w czasie danego poziomu */
	private final JLabel totalLabel;
	/** Etykieta informacji o aktualnie rozgrywanym trybie */
	private final JLabel modeLabel;
	/** Przycisk nowej gry */
	private final JButton newGameButton;
	/** Przycisk konca gry */
	private final JButton finishButton;
	/** Przycisk przejscia do nastepnego poziomu */
	private final JButton nextLevelButton;
	/** instancja klasy odpowiadajacej za przetwarzanie logiki gry */
	private final Game game;

	/**
	 *
	 * @param width szerokosc panelu statusu
	 * @param height wysokosc panelu statusu
	 * @param g instancja klasy odpowiadajacej za przetwarzanie logiki gry
	 */
	public StatusPanel(int width, int height, Game g)
	{
		game = g;

		setPreferredSize(new Dimension(width, height));
		Font font = new Font("Dialog", Font.BOLD, 24);
		setBackground(new Color(192, 192, 192));

		setLayout(new GridLayout(1, 7));

		levelLabel = new JLabel();
		levelLabel.setFont(font);
		levelLabel.setText("");

		pointsLabel = new JLabel();
		pointsLabel.setFont(font);
		pointsLabel.setText("");

		totalLabel = new JLabel();
		totalLabel.setFont(font);
		totalLabel.setText("");

		modeLabel = new JLabel();
		modeLabel.setFont(font);
		modeLabel.setText("");

		//Tworzenie przyciskow i przypisywanie im odpowiednich akcji po wcisnieciu
		newGameButton = new JButton("Nowa gra");
		newGameButton.setFont(font);
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				game.setPause();
				game.newGame();
				game.start();
			}
		});

		finishButton = new JButton("Zakoncz");
		finishButton.setFont(font);
		finishButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		nextLevelButton = new JButton("Dalej");
		nextLevelButton.setFont(font);
		nextLevelButton.setEnabled(false);
		nextLevelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				game.levelUp();
				game.start();
			}
		});

		add(levelLabel);
		add(pointsLabel);
		add(totalLabel);
		add(modeLabel);
		add(newGameButton);
		add(finishButton);
		add(nextLevelButton);

		//utworzenie sluchacza zdarzen, w celu pobierania informacji o stanie gry
		game.setStatusChangeListner(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				pointsLabel.setText("Punkty: " + game.getPoints());
				totalLabel.setText("Suma: " + game.getTotal());
				levelLabel.setText("Poziom: " + game.getActualLevel());
				modeLabel.setText("Tryb: " + game.getGameMode());
				nextLevelButton.setEnabled(game.isNextLevelAvailable());
			}
		});

	}
}
