package com.kvtsoft.hibernate.onetomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetomany.entity.Course;
import com.kvtsoft.hibernate.onetomany.entity.Instructor;
import com.kvtsoft.hibernate.onetomany.entity.InstructorDetail;

public class DeleteCourse {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int id = 2;

		try {

			// start transaction
			session.beginTransaction();

			// retrieve the object which need to be deleted from db
			Course course = session.get(Course.class, id);

			if (course != null) {
				System.out.println("\nDeleting course only... ");

				// remove the associated object reference
				// break bi-directional link
				if (course != null) {
					session.delete(course);
					System.out.println("\nObject has been deleted successfully");
				}

				// commit transaction
				session.getTransaction().commit();

			} else {
				System.out.println("\nInstructor detail with id: " + id + " not found");
			}

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
