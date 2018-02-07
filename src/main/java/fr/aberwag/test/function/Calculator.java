package fr.aberwag.test.function;

import java.util.function.BiFunction;

public class Calculator {
	public Long calculate(BiFunction<Integer, Integer, Long> biCalc, Integer i1, Integer i2) {
		return biCalc.apply(i1, i2);
	}
}