package com.kvtsoft.hibernate.onetoone.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetoone.entity.Instructor;
import com.kvtsoft.hibernate.onetoone.entity.InstructorDetail;

public class RetrieveCascadeOneToOneBi {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		int id = 1;
		try {

			// start transaction
			session.beginTransaction();

			Instructor instructor = session.get(Instructor.class, id);

			// retrieve Instructor details
			// InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

			if (instructor != null) {
				// get instructor data from instructorDetail object
				System.out.println("\nRetrieving intructor and detail... ");

				// will fetch instructor_details along with "instructor" object,
				// only if "instructor" property is mappedBy in "insrtuctor_detail" entity
				// for bi-directional mapping
				// Instructor instructor = instructorDetail.getInstructor();

				// System.out.println("\nInstructorDetail: " + instructorDetail);
				System.out.println("\nGet assocaited Instructor: " + instructor);

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
