package fr.aberwag.test.function;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

import org.junit.Test;

/*
 * @FunctionalInterface
 * public interface Function<T,R>
 * 
 * Represents a function that accepts one argument and produces a result.
 * This is a functional interface whose functional method is apply(Object).
 * 
 * Methods
 * 		R apply(T t)
 * 				Applies this function to the given arguments.
 * 		default <V> Function<T,V> andThen(Function<? super R,? extends V> after)
 * 				Returns a composed function that first applies this function to its input, and then applies the after function to the result.
 * 		default <V> Function<V,R> compose(Function<? super V,? extends T> before)
 * 				Returns a composed function that first applies the before function to its input, and then applies this function to the result.
 * 		static <T> Function<T,T> identity()
 * 				Returns a function that always returns its input argument.
 */
public class FunctionTest {

	@Test
	public void test1() {
		Function<Integer, String> converter = (i) -> Integer.toString(i);

		assertEquals(1, converter.apply(3).length());
		assertEquals(2, converter.apply(30).length());
		assertEquals(3, converter.apply(300).length());
	}

	@Test
	public void test2() {
		Integer result = calc((a) -> (a * 2), 10);
		assertEquals(20, result.intValue());
	}

	private Integer calc(Function<Integer, Integer> function, Integer i) {
		return function.apply(i);
	}
}
