package ch.fhnw.oop2.ab.ab2_7;

/**
 * Diese Klasse modelliert einen Trader.
 *
 */
public final class Trader {
	
	private final String name;
	private       String city;

	/**
	 * Erzeugt einen Trader mit Name und Stadt.
	 * 
	 * @param name  Der Name des Traders
	 * @param city  Die Stadt in welcher der Trader arbeitet
	 */
	public Trader(String name, String city){
		this.name = name;
		this.city = city;
	}

	public String getName(){
		return this.name;
	}

	public String getCity(){
		return this.city;
	}

	public void setCity(String newCity){
		this.city = newCity;
	}

	@Override
	public String toString(){
		return "Trader:" + this.name + " in " + this.city;
	}
}