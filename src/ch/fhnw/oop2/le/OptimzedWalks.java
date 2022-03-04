package ch.fhnw.oop2.le;

import java.util.stream.Stream;

public class OptimzedWalks {

	public static void main(String[] args) {
		
		Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
			.filter(n -> { 
				System.out.println("filtering: " + n); 
				return n % 2 == 0; })
			.map(n -> { 
				System.out.println("mapping: " + n); 
				return n * n; })
			.limit(3)
			.forEach(System.out::println);

	}
}


