package fr.aberwag.test.consumer;

import java.util.function.BiConsumer;

import org.junit.Test;

import fr.aberwag.test.Loggable;

/*
 * @FunctionalInterface
 * public interface BiConsumer<T,U>
 * 
 * Represents an operation that accepts two input arguments and returns no result. 
 * This is the two-arity specialization of Consumer. 
 * Unlike most other functional interfaces, BiConsumer is expected to operate via side-effects.
 * This is a functional interface whose functional method is accept(Object, Object).
 * 
 * Methods
 * 		void accept(T t, U u)
 * 				Performs this operation on the given arguments.
 * 		default BiConsumer<T,U> andThen(BiConsumer<? super T,? super U> after)
 * 				Returns a composed BiConsumer that performs, in sequence, this operation followed by the after operation.
 */
public class BiConsumerTest implements Loggable {

	@Test
	public void test() {
		BiConsumer<String, String> biConsumer = (x, y) -> {
			System.out.print(x);
			System.out.println(y);
		};
		biConsumer.accept("Wassyl", " Sanya");
		biConsumer.andThen(biConsumer).accept("Wassyl", " Sanya");
	}
}