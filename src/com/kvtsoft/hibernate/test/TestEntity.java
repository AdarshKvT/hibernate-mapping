package com.kvtsoft.hibernate.test;

import java.util.Calendar;

public class TestEntity {
	private int yearOfBirth;
	private int age;

	public TestEntity(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;

		this.age = Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
