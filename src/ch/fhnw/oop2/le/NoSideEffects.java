package ch.fhnw.oop2.le;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class NoSideEffects {

	public static void main(String[] args) {
		
		List<String> collection = Arrays.asList("A", "B", "C");
		
		List<String> filteredCollection = collection.stream()
			.filter(s -> s.startsWith("A"))
			.collect(toList());

		print(filteredCollection);
		print(collection);
	}
	
	private static void print(List<?> list) {
		System.out.println("----------------------");
		for(Object o : list) {
			System.out.println(o);
		}
	}

}
