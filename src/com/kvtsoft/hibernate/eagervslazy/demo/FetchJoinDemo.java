package com.kvtsoft.hibernate.eagervslazy.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.kvtsoft.hibernate.eagervslazy.entity.Course;
import com.kvtsoft.hibernate.eagervslazy.entity.Instructor;
import com.kvtsoft.hibernate.eagervslazy.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		int id = 1;

		try {

			// start transaction
			session.beginTransaction();

			// get the instructor from db
			Query<Instructor> query = session.createQuery(
					"select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id=:instructorId",
					Instructor.class);

			// set the parameter
			query.setParameter("instructorId", id);

			// execute query and get instructor
			Instructor instructor = query.getSingleResult();

			if (instructor != null) {

				System.out.println("\nRetrieving courses attached to the instructor");
				// retrieve courses of instructor using eager fetchType
				System.out.println("\nInstructor: " + instructor);

				System.out.println("\nCourses: " + instructor.getCourses());

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
