package fr.aberwag.test.predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

import fr.aberwag.test.Loggable;

/*
 * @FunctionalInterface
 * public interface Predicate<T>
 * 
 * Represents a predicate (boolean-valued function) of one argument.
 * This is a functional interface whose functional method is test(Object).
 * 
 * Methods
 * 		default Predicate<T> and(Predicate<? super T> other)
 * 				Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
 * 		static <T> Predicate<T> isEqual(Object targetRef)
 * 				Returns a predicate that tests if two arguments are equal according to Objects.equals(Object, Object).
 * 		default Predicate<T> negate()
 * 				Returns a predicate that represents the logical negation of this predicate.
 * 		default Predicate<T> or(Predicate<? super T> other)
 * 				Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.
 * 		boolean test(T t)
 * 				Evaluates this predicate on the given argument.
 */
public class PredicateTest implements Loggable {

	@Test
	public void test1() {
		Predicate<String> i = (s) -> s.length() > 5;
		assertTrue(i.test("Wassyl & Sanya"));
	}

	@Test
	public void test2() {
		List<Box> inventory = Arrays.asList(new Box(80, "green"), new Box(155, "green"), new Box(120, "red"));

		List<Box> greenApples = filter(inventory, PredicateTest::isGreenApple);
		greenApples.forEach(b -> logger().info("Box weight : {} - Box color {}", b.getWeight(), b.getColor()));
		assertThat(greenApples.size(), is(2));
		logger().info("----------------------------------------------------------------------------------------");
		List<Box> heavyApples = filter(inventory, PredicateTest::isHeavyApple);
		heavyApples.forEach(b -> logger().info("Box weight : {} - Box color {}", b.getWeight(), b.getColor()));
		assertThat(heavyApples.size(), is(1));
		logger().info("----------------------------------------------------------------------------------------");
		List<Box> greenApples2 = filter(inventory, (Box a) -> "green".equals(a.getColor()));
		greenApples2.forEach(b -> logger().info("Box weight : {} - Box color {}", b.getWeight(), b.getColor()));
		assertThat(greenApples, is(greenApples2));
		logger().info("----------------------------------------------------------------------------------------");
		List<Box> heavyApples2 = filter(inventory, (Box a) -> a.getWeight() > 150);
		heavyApples2.forEach(b -> logger().info("Box weight : {} - Box color {}", b.getWeight(), b.getColor()));
		assertThat(heavyApples, is(heavyApples2));
		logger().info("----------------------------------------------------------------------------------------");
		List<Box> weirdApples = filter(inventory, (Box a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));
		weirdApples.forEach(b -> logger().info("Box weight : {} - Box color {}", b.getWeight(), b.getColor()));
		assertThat(weirdApples.size(), is(0));
	}

	@Test
	public void test3() {
		List<Student> employees = Arrays.asList(new Student(1, 3, "John"), new Student(2, 3, "Jane"),
				new Student(3, 4, "Jack"));

		// with predicate
		employees.forEach(l -> logger().info("Student id : {} - Student gpa : {} - Student name : {}",
				new String[] { String.valueOf(l.id), String.valueOf(l.gpa), l.name }));
		assertThat(findStudents(employees, createCustomPredicateWith(3)).size(), is(1));

		// with function definition, both are same
		Function<Double, Predicate<Student>> customFunction = threshold -> (e -> e.gpa > threshold);
		assertThat(findStudents(employees, customFunction.apply(3D)).size(), is(1));
	}

	private static Predicate<Student> createCustomPredicateWith(double threshold) {
		return e -> e.gpa > threshold;
	}

	private static List<Student> findStudents(List<Student> employees, Predicate<Student> condition) {
		List<Student> result = new ArrayList<>();
		for (Student e : employees) {
			if (condition.test(e)) {
				result.add(e);
			}
		}
		return result;
	}

	public static boolean isGreenApple(Box apple) {
		return "green".equals(apple.getColor());
	}

	public static boolean isHeavyApple(Box apple) {
		return apple.getWeight() > 150;
	}

	public List<Box> filter(List<Box> inventory, Predicate<Box> p) {
		List<Box> result = new ArrayList<>();
		for (Box apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
}