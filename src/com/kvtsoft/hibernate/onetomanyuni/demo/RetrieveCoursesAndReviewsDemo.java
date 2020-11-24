package com.kvtsoft.hibernate.onetomanyuni.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetomanyuni.entity.Course;
import com.kvtsoft.hibernate.onetomanyuni.entity.Instructor;
import com.kvtsoft.hibernate.onetomanyuni.entity.InstructorDetail;
import com.kvtsoft.hibernate.onetomanyuni.entity.Review;

public class RetrieveCoursesAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int id = 10;

		try {

			// start a transaction
			session.beginTransaction();

			// get the course
			Course course = session.get(Course.class, id);

			if (course != null) {

				// print the course
				System.out.println("course: " + course);
				// print the course review
				System.err.println("reviews: " + course.getReview());
				// commit the transaction

			} else {
				System.out.println("COurse id: " + id + " not found");
			}

			session.getTransaction().commit();

			System.out.println("\nObject has been retrive successfully");

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
