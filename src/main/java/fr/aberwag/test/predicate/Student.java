package fr.aberwag.test.predicate;

public class Student {
	public int id;
	public long gpa;
	public String name;

	Student(int id, long g, String name) {
		this.id = id;
		this.gpa = g;
		this.name = name;
	}

	@Override
	public String toString() {
		return id + ">" + name + ": " + gpa;
	}
}