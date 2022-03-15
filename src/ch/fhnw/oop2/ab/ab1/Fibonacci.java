package ch.fhnw.oop2.ab.ab1;

import java.util.stream.Stream;

public class Fibonacci {
    public static void main(String[] args) {
        Stream.iterate(new Tuple<Integer>(0, 1), (t) -> new Tuple<Integer>(t.t2, t.t1 + t.t2))
        .limit(30)
        .map(t -> t.t2)
        .forEach(System.out::println);
    }
}
