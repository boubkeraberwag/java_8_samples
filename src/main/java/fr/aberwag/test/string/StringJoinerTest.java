package fr.aberwag.test.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.junit.Test;

import fr.aberwag.test.Loggable;

/*
 * public final class StringJoiner extends Object
 * 
 * StringJoiner is used to construct a sequence of characters separated by a delimiter and optionally starting with a supplied prefix and ending with a supplied suffix.
 * Prior to adding something to the StringJoiner, its sj.toString() method will, by default, return prefix + suffix. However, 
 * if the setEmptyValue method is called, the emptyValue supplied will be returned instead. 
 * This can be used, for example, when creating a string using set notation to indicate an empty set, i.e. 
 * "{}", where the prefix is "{", the suffix is "}" and nothing has been added to the StringJoiner.
 * 
 * Methods
 * 		StringJoiner add(CharSequence newElement)
 * 				Adds a copy of the given CharSequence value as the next element of the StringJoiner value.
 * 		int length()
 * 				Returns the length of the String representation of this StringJoiner.
 * 		StringJoiner merge(StringJoiner other)
 * 				Adds the contents of the given StringJoiner without prefix and suffix as the next element if it is non-empty.
 * 		StringJoiner setEmptyValue(CharSequence emptyValue)
 * 				Sets the sequence of characters to be used when determining the string representation of this StringJoiner and no elements have been added yet, that is, when it is empty.
 * 		String toString()
 * 				Returns the current value, consisting of the prefix, the values added so far separated by the delimiter, and the suffix, 
 * 				unless no elements have been added in which case, the prefix + suffix or the emptyValue characters are returned
 */
public class StringJoinerTest implements Loggable {

	@Test
	public void test1() {
		StringJoiner sj = new StringJoiner(":", "[", "]");
		sj.add("Wassyl").add("Sanya").add("ABERWAG");
		logger().info("StringJoiner value : {}", sj.toString());
		assertEquals("[Wassyl:Sanya:ABERWAG]", sj.toString());
	}

	@Test
	public void test2() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
		assertEquals("1, 2, 3, 4", numbers.stream().map(i -> i.toString()).collect(Collectors.joining(", ")));
	}

	@Test
	public void test3() {
		List<String> list = Arrays.asList("Wassyl", "Sanya", "ABERWAG");
		logger().info("Liste value : {}", String.join(", ", list));
		assertEquals("Wassyl, Sanya, ABERWAG", String.join(", ", list));
	}
}