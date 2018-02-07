package fr.aberwag.test.function;

import static org.junit.Assert.assertEquals;

import java.util.function.BiFunction;

import org.junit.Test;

/*
 * @FunctionalInterface
 * public interface BiFunction<T,U,R>
 * 
 * Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of Function.
 * This is a functional interface whose functional method is apply(Object, Object).
 * 
 * Methods
 * 		R apply(T t, U u)
 * 				Applies this function to the given arguments.
 * 		default <V> BiFunction<T,U,V> andThen(Function<? super R,? extends V> after)
 * 				Returns a composed function that first applies this function to its input, and then applies the after function to the result
 */
public class BiFunctionTest {

	@Test
	public void test1() {
		BiFunction<String, String, String> bi = (x, y) -> x + y;
		assertEquals("Wassyl Sanya", bi.apply("Wassyl", " Sanya"));
	}

	@Test
	public void test2() {
		Calculator calculator = new Calculator();
		assertEquals(15, calculator.calculate((a, b) -> (long) (a * b), 3, 5).longValue());
	}

}