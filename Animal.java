import java.util.Random;

/**
 * Typ wyliczeniowy reprezentujacy istniejace w grze zwierzeta
 * Kazda wartosc zawiera pola ulatiwajace prace pozostalych komponentow (np. wczytanie odpiwedniego pliku graficzengo)
 * @author Magdalena Fabich
 */
public enum Animal
{

    /**
     *
     */
    FISH("Ryba", new String[]{"fish.png", "shark.png", "clown.png"}, 0),

    /**
     *
     */
    BIRD("Ptak", new String[]{"bird.png", "hummingbird.png", "blue.png"}, 1),

    /**
     *
     */
    MAMMAL("Ssak", new String[]{"mammal.png", "wolf.png", "cat.png"}, 2);

	private String val;
	private String[] fileNames;
	private int id;

	/**
	 * Konstruktor
	 */
	Animal(String val, String[] fileNames, int id)
	{
		this.val = val;
		this.fileNames = fileNames;
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
	public String[] getFileNames()
	{
		return fileNames;
	}

	/** Generator liczb losowych */
	private static final Random GENERATOR = new Random();

	/**
	 * Losuje typ zwierzecia, a nastepnie losuje gatunek
	 * @return losowa wartosc enum'a
	 */
	public static Animal randomAnimal()
	{
		return values()[GENERATOR.nextInt(values().length)];
	}
}
