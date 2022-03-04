package ch.fhnw.oop2.ab.ab1;

/**
 * Die Klasse Tuple verpackt zwei Werte. Sie ist also ein einfacher
 * Datentyp (ein Tupel). Die Daten sind immutable.
 *
 */
public class Tuple<T> {
	
	public final T t1;
	public final T t2;
	
	/**
	 * Erzeugt ein neues Tupel.
	 * 
	 * @param t1
	 * @param t2
	 */
	public Tuple(T t1, T t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
	
	@Override
	public String toString() {
		return "(" + t1 + ", " + t2 + ")";
	}
}
