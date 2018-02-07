package fr.aberwag.test.supplier;

public class Student {
	private String name;
	private double gpa;

	Student(String name, double g) {
		this.name = name;
		this.gpa = g;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

}
