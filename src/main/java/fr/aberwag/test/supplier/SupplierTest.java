package fr.aberwag.test.supplier;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import java.util.function.Supplier;

import org.junit.Test;

import fr.aberwag.test.Loggable;

/*
 * @FunctionalInterface
 * public interface Supplier<T>
 * 
 * Represents a supplier of results.
 * There is no requirement that a new or distinct result be returned each time the supplier is invoked.
 * This is a functional interface whose functional method is get().
 * 
 * Methods
 * 		T get()
 * 				Evaluates this predicate on the given argument.
 */
public class SupplierTest implements Loggable {

	@Test
	public void test1() {
		Supplier<String> i = () -> "Sanya & Wassyl";
		assertEquals("Sanya & Wassyl", i.get());
	}

	@Test
	public void test2() {
		SunPower power = new SunPower();
		SunPower p1 = produce(() -> power);
		SunPower p2 = produce(() -> power);
		assertEquals(p1, p2);
	}

	@Test
	public void test3() {
		assertThat(maker(Employee::new), instanceOf(Employee.class));
	}

	@Test
	public void test4() {
		Supplier<Student> studentGenerator = SupplierTest::employeeMaker;

		for (int i = 0; i < 10; i++) {
			logger().info("# {} : {}", i, studentGenerator.get());
		}

		assertNotEquals(studentGenerator.get(), studentGenerator.get());
	}

	private SunPower produce(Supplier<SunPower> supp) {
		return supp.get();
	}

	private Employee maker(Supplier<Employee> fx) {
		return fx.get();
	}

	private static Student employeeMaker() {
		return new Student("A", 2);
	}
}