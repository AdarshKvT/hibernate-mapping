package com.kvtsoft.hibernate.onetomanyuni.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetomanyuni.entity.Course;
import com.kvtsoft.hibernate.onetomanyuni.entity.Instructor;
import com.kvtsoft.hibernate.onetomanyuni.entity.InstructorDetail;
import com.kvtsoft.hibernate.onetomanyuni.entity.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int id = 1;

		try {

			// start a transaction
			session.beginTransaction();

			// create a course
			Course tempCourse = new Course("Pacman- How to score one Million points");

			// creating reviews
			List<Review> reviewList = new ArrayList<>();
			reviewList.add(new Review("Great course ... loved it"));
			reviewList.add(new Review("Cool course, job well done"));
			reviewList.add(new Review("what a dump course"));

			// setting reviews to the Course
			tempCourse.setReview(reviewList);

			// save the course ... and leverage the cascade all
			System.out.println("saving tempCourses ");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReview());

			session.save(tempCourse);

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

//tempCourse.addReview(new Review("Great course ... loved it"));
//tempCourse.addReview(new Review("Cool course, job well done"));
//tempCourse.addReview(new Review("what a dump course"));
