package fr.aberwag.test.consumer;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

/*
 * @FunctionalInterface
 * public interface Consumer<T>
 * 
 * Represents an operation that accepts a single input argument and returns no result.
 * Unlike most other functional interfaces, Consumer is expected to operate via side-effects.
 * This is a functional interface whose functional method is accept(Object).
 * 
 * Methods
 * 		void accept(T t)
 * 				Performs this operation on the given argument.
 * 		default Consumer<T> andThen(Consumer<? super T> after)
 * 				Returns a composed Consumer that performs, in sequence, this operation followed by the after operation.
 */
public class ConsumerTest {

	@Test
	public void test1() {
		Consumer<String> c = (x) -> assertEquals("wassyl & sanya", x.toLowerCase());
		c.accept("WASSYL & SANYA");
	}

	@Test
	public void test2() {
		int x = 100;
		Consumer<Integer> myConsumer = (y) -> {
			assertEquals(100, x);
			assertEquals(x, (int) y);
		};
		myConsumer.accept(x);
	}

	@Test
	public void test3() {
		List<Student> students = Arrays.asList(new Student("John", 3), new Student("Mark", 4));

		acceptAllEmployee(students, e -> System.out.println(e.getName()));
		acceptAllEmployee(students, e -> {
			e.setGpa(e.getGpa() * 1.5);
		});
		acceptAllEmployee(students, e -> System.out.println(e.getName() + ": " + e.getGpa()));
	}

	public void acceptAllEmployee(List<Student> student, Consumer<Student> printer) {
		for (Student e : student) {
			printer.accept(e);
		}
	}
}