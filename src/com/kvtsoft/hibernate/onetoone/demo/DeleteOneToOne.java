package com.kvtsoft.hibernate.onetoone.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetoone.entity.Instructor;
import com.kvtsoft.hibernate.onetoone.entity.InstructorDetail;

public class DeleteOneToOne {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int id = 4;

		try {

			// start transaction
			session.beginTransaction();

			// retrieve the object which need to be deleted from db
			// Instructor instructor = session.get(Instructor.class, id);
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

			if (instructorDetail != null) {
				System.out.println("\nDeleting intructor detail... ");

				// remove the associated object reference
				// break bi-directional link
				if (instructorDetail.getInstructor() != null) {
					instructorDetail.getInstructor().setInstructorDetail(null);
				}

				// deleting only instructor detail while updating
				// foreign key of instructor_detail_id to null
				session.delete(instructorDetail);
				System.out.println("\nObject has been deleted successfully");

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
