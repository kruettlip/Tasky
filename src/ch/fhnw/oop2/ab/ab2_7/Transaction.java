package ch.fhnw.oop2.ab.ab2_7;

/**
 * Die Klasse modelliert eine Transaktion. Eine Transaktion ist ein Börsengeschäft, also
 * z.B. eine Aktie verkaufen oder kaufen. 
 *
 */
public final class Transaction {
	
	private final Trader trader;
	private final int    year;
	private final int    value;

	/**
	 * Erzeugt eine Transaktion.
	 * 
	 * @param trader  Der Trader, welcher die Transaktion tätigt
	 * @param year  Das Jahr in dem Transaktion getätigt wird
	 * @param value  Der Geldbetrag, welcher in die Transaktion investiert wird
	 */
	public Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public int getYear() {
		return this.year;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "{" + this.trader + ", " +
		       "year: " + this.year + ", " +
		       "value:" + this.value + "}";
	}
}