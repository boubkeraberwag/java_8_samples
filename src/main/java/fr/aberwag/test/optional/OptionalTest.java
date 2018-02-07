package fr.aberwag.test.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

/*
 * public final class Optional<T> extends Object
 * 
 * A container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.
 * 
 * Additional methods that depend on the presence or absence of a contained value are provided,
 * such as orElse() (return a default value if value not present) and ifPresent() (execute a block of code if the value is present).
 * 
 * This is a value-based class; 
 * use of identity-sensitive operations (including reference equality (==), 
 * identity hash code, or synchronization) on instances of Optional may have unpredictable results and should be avoided.
 * 
 * Methods
 * 		static <T> Optional<T> empty()
 * 				Returns an empty Optional instance.
 * 		boolean equals(Object obj)
 * 				Indicates whether some other object is "equal to" this Optional.
 * 		Optional<T> filter(Predicate<? super T> predicate)
 * 				If a value is present, and the value matches the given predicate, return an Optional describing the value, otherwise return an empty Optional.
 * 		<U> Optional<U> flatMap(Function<? super T,Optional<U>> mapper)
 * 				If a value is present, apply the provided Optional-bearing mapping function to it, return that result, otherwise return an empty Optional.
 * 		T get()
 * 				If a value is present in this Optional, returns the value, otherwise throws NoSuchElementException.
 * 		int hashCode()
 * 				Returns the hash code value of the present value, if any, or 0 (zero) if no value is present.
 * 		void ifPresent(Consumer<? super T> consumer)
 * 				If a value is present, invoke the specified consumer with the value, otherwise do nothing.
 * 		boolean isPresent()
 * 				Return true if there is a value present, otherwise false.
 * 		<U> Optional<U> map(Function<? super T,? extends U> mapper)
 * 				If a value is present, apply the provided mapping function to it, and if the result is non-null, return an Optional describing the result.
 * 		static <T> Optional<T> of(T value)
 * 				Returns an Optional with the specified present non-null value.
 * 		static <T> Optional<T> ofNullable(T value)
 * 				Returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
 * 		T orElse(T other)
 * 				Return the value if present, otherwise return other.
 * 		T orElseGet(Supplier<? extends T> other)
 * 				Return the value if present, otherwise invoke other and return the result of that invocation.
 * 		<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)
 * 				Return the contained value, if present, otherwise throw an exception to be created by the provided supplier.
 * 		String toString()
 * 				Returns a non-empty string representation of this Optional suitable for debugging.
 */
public class OptionalTest {

	@Test
	public void test1() {
		Person wassyl = new Person("Wassyl", "ABERWAG", 2);
		assertEquals("Wassyl", wassyl.getFirstName());
		assertEquals("ABERWAG", wassyl.getLastName());
		assertEquals(2, wassyl.getAge());
	}

	@Test(expected = NullPointerException.class)
	public void test2() {
		Person wassyl = null;
		assertEquals("Wassyl", wassyl.getFirstName());
	}

	@Test
	public void test3() {
		Person p = null;
		Optional<Person> sanya = Optional.ofNullable(p);
		sanya.ifPresent(u -> assertEquals("Sanya", u.getAge()));
	}

	@Test
	public void test4() {
		Person p = null;
		Optional<Person> sanya = Optional.ofNullable(p);
		assertFalse(sanya.isPresent());
	}

	@Test
	public void test5() {
		Person p = null;
		Optional<Person> sanya = Optional.ofNullable(p);
		assertEquals(Optional.empty(), sanya);
	}
}