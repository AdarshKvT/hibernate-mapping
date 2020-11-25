package com.kvtsoft.hibernate.manytomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.manytomany.entity.Course;
import com.kvtsoft.hibernate.manytomany.entity.Instructor;
import com.kvtsoft.hibernate.manytomany.entity.InstructorDetail;
import com.kvtsoft.hibernate.manytomany.entity.Review;
import com.kvtsoft.hibernate.manytomany.entity.Student;

public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// create a course
			Course course = new Course("Spiderman: web shhoting");

			System.out.println("\nsaving the course");
			session.save(course);

			System.out.println("saved the course: " + course);

			// create the students
			Student studentOne = new Student("Sachin", "Kavtiyal", "sachinkavtiyal@konoha");
			Student studentTwo = new Student("Sasuke", "Uchiha", "sasukeuchiha@konoha");

			System.out.println("studentOne: " + studentOne);
			// add student to the course
			course.addStudent(studentOne);
			System.out.println("studentTwo: " + studentTwo);
			course.addStudent(studentTwo);

			// save the students
			System.out.println("\nSaving students ...");
			session.save(studentOne);
			session.save(studentTwo);
			System.out.println("\nSaved Students: " + course.getStudent());

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("\nObject has been saved successfully");

		} catch (Exception e) {
			System.out.println("\nAn error occured. Cannot save the object !!");
			e.printStackTrace();

		} finally {
			// handle leak connection issue
			System.out.println("\nClosing the factory and session connection");
			session.close();
			factory.close();
		}

	}

}
