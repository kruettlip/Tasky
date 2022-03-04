package ch.fhnw.oop2.le;

import java.util.Arrays;
import java.util.List;

public class FlatMap {

	public static void main(String[] args) {
		
		List<String> words = Arrays.asList("An",  "Analogy",  "with",  "Structured",  "Programming");
		
		// Wir wollen hier eine Liste mit allen Buchstaben haben, die in words 
		// verwendet wurden. Die Liste soll keine Duplikate aufweisen.
		
		
		long n = words.stream()
			.map(word -> word.split(""))  // Gibt String[] pro Wort zurück
			.distinct()
			.count();
		
		// Das sind fünf Listen mit den Buchstaben der jeweiligen Wörter.
		System.out.println(n);  
		
		
		long m = words.stream()
			.map(word -> word.split(""))  // Liste von Arrays von Strings (warum nicht Methoden-Referenz?)
			.flatMap(Arrays::stream)      // INHALT der Arrays in ein Stream von Strings
			.map(String::toLowerCase)     // 19 -> 18, wegen Aa
			.distinct()                   // Keine Dubletten
			.count();                     // Zählen
		
		// Für die Wörter wurden 18 Buchstaben aus dem Alphabet verwendet.
		System.out.println(m);
	}
}

