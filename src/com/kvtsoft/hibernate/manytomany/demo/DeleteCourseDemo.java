package com.kvtsoft.hibernate.manytomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.manytomany.entity.Course;
import com.kvtsoft.hibernate.manytomany.entity.Instructor;
import com.kvtsoft.hibernate.manytomany.entity.InstructorDetail;
import com.kvtsoft.hibernate.manytomany.entity.Review;
import com.kvtsoft.hibernate.manytomany.entity.Student;

public class DeleteCourseDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int id = 2;
		try {

			// start a transaction
			session.beginTransaction();

			// get the student "sasuke" from database
			Student student = session.get(Student.class, id);

			if (student != null) {

				System.out.println("\nDeleting the course...");
				session.delete(student);

				// commit the transaction
				session.getTransaction().commit();

				System.out.println("\nObject has been deleted successfully");

			} else {
				System.out.println("\nObject id: " + id + " not found!!");
			}

		} catch (Exception e) {
			System.out.println("\nAn error occured.");
			e.printStackTrace();

		} finally {
			// handle leak connection issue
			System.out.println("\nClosing the factory and session connection");
			session.close();
			factory.close();
		}

	}

}
