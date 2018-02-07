package fr.aberwag.test;

import java.util.Arrays;

public class Main implements Loggable {
	public static void main(String[] args) {
		for (int i : Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)) {
			new Main().logger().info("utiliser le logger dans une méthode statique");
		}
	}

}