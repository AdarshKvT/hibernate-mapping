package com.kvtsoft.hibernate.onetoone.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetoone.entity.Instructor;
import com.kvtsoft.hibernate.onetoone.entity.InstructorDetail;

public class DeleteCascadeOneToOne {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		int id = 2;

		try {

			// start transaction
			session.beginTransaction();

			// retrieve the object which need to be deleted from db
			Instructor instructor = session.get(Instructor.class, id);
			// InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

			// delete the instructor
			// Note: will also delete associated "detail" object
			// because of CascadeType.All
			//
			// session.createQuery("delete from Instructor where id='" + id +
			// "'").executeUpdate();

			if (instructor != null) {
				System.out.println("\nDeleting intructor... ");
				session.delete(instructor);

				// bi-directional cascade deleting
				// session.delete(instructorDetail);

				System.out.println("\nObject has been deleted successfully");

				// commit transaction
				session.getTransaction().commit();

			} else {
				System.out.println("\nInstructor with id: " + id + " not found");
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
