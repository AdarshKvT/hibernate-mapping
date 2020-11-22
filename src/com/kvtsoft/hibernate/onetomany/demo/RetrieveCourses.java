package com.kvtsoft.hibernate.onetomany.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetomany.entity.Course;
import com.kvtsoft.hibernate.onetomany.entity.Instructor;
import com.kvtsoft.hibernate.onetomany.entity.InstructorDetail;

public class RetrieveCourses {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		int id = 1;

		try {

			// start transaction
			session.beginTransaction();

			Instructor instructor = session.get(Instructor.class, id);

			if (instructor != null) {

				System.out.println("\nRetrieving courses attached to the instructor");
				// retrieve courses of instructor
				List<Course> course = instructor.getCourses();

				System.out.println("\nInstructor courses: " + course);

			} else {
				System.out.println("\nInstructor detail with id: " + id + " not found");

			}

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("\nAn error occured. Cannot delete the object !!");
			e.printStackTrace();

		} finally {

			// handle leak connection issue
			System.out.println("\nClosing the factory and session connection");
			session.close();
			factory.close();
		}
	}

}
