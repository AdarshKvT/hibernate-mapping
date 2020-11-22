package com.kvtsoft.hibernate.test;

public class Test {

	public static void main(String[] args) {
		TestEntity bob = new TestEntity(2000);

		System.out.println("Bob's age: " + bob.getAge());

		//bob.age = 100; // This would be BAD!
	}
}
