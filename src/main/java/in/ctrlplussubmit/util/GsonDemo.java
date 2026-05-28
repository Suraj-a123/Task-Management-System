package in.ctrlplussubmit.util;

import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonDemo {
	public static void main(String[] args) {
		Student s = new Student(101, "Afroz", 25);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String student = gson.toJson(s);
		//System.out.println("Json Students Data: "+student);
		Student s2 = gson.fromJson(student, Student.class);
		System.out.println("Students Object "+s2.toString());
	}
}
class Student{
	private int roll;
	private String name;
	private int age;
	public Student(int roll, String name, int age) {
		this.roll = roll;
		this.name = name;
		this.age = age;
		
	}
}