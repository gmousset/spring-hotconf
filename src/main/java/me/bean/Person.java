package me.bean;

public class Person {

	private String firstname;
	private String lastname;
	private int age;
	
	public Person() {
		super();
	}
	
	public Person(String fn, String ln, int a) {
		super();
		this.firstname = fn;
		this.lastname = ln;
		this.age = a;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}
