import java.util.Random;

/**
 * Typ wyliczeniowy reprezentujacy istniejace w grze zwierzeta
 * Kazda wartosc zawiera pola ulatiwajace prace pozostalych komponentow (np. wczytanie odpiwedniego pliku graficzengo)
 * @author Magdalena Fabich
 */
public enum Animal
{
	FISH("Ryba", "fish.png", 0), BIRD("Ptak", "bird.png", 1), MAMMAL("Ssak", "mammal.png", 2);

	private String val;
	private String fileName;
	private int id;

	/**
	 * Konstruktor
	 */
	Animal(String val, String fileName, int id)
	{
		this.val = val;
		this.fileName = fileName;
		this.id = id;
	}

	/**
	 * @return reprezentacja tekstowa danej wartosci enum'a
	 */
	@Override
	public String toString()
	{
		return val;
	}

	/**
	 * @return indeks w tablicy obrazkow
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return nazwa pliku z obrazkiem
	 */
	public String getFileName()
	{
		return fileName;
	}

	/** Generator liczb losowych */
	private static final Random GENERATOR = new Random();

	/**
	 *
	 * @return losowa wartosc enum'a
	 */
	public static Animal randomAnimal()
	{
		return values()[GENERATOR.nextInt(values().length)];
	}
}
