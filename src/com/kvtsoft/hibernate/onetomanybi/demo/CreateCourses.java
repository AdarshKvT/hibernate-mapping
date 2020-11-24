package com.kvtsoft.hibernate.onetomanybi.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetomanybi.entity.Course;
import com.kvtsoft.hibernate.onetomanybi.entity.Instructor;
import com.kvtsoft.hibernate.onetomanybi.entity.InstructorDetail;

public class CreateCourses {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int id = 1;

		try {

			// start a transaction
			session.beginTransaction();

			// get the instructor form db
			Instructor theInstructor = session.get(Instructor.class, id);

			// create some courses
			Course courseOne = new Course("Game");
			Course courseTwo = new Course("Physics");

			// add courses to instructor
			// adding course to the course list along with the instructor
			System.out.println("\nadding course to the course list along with the instructor");
			System.out.println("\nadding courseOne to the Instructor");
			theInstructor.add(courseOne);
			System.out.println("\nadding courseTwo to the Instructor");
			theInstructor.add(courseTwo);

			// save the courses
			System.out.println("\nSaving courseOne... " + courseOne);
			session.save(courseOne);
			System.out.println("\nSaving courseTwo... " + courseOne);
			session.save(courseTwo);

			System.out.println("session.commit");
			// commit transaction
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
