package ch.fhnw.oop2.le;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TraverseOnlyOnce {

	public static void main(String[] args) {
	
		List<String> languages = Arrays.asList("Java", "Scala", "Lisp", "Scheme");
		
		Stream<String> stream = languages.stream();
		
		long n = stream.count();  // n = 4
		System.out.println(n);
		stream.forEach(System.out::println);
	}
}

