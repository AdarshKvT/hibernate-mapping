package com.kvtsoft.hibernate.onetomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kvtsoft.hibernate.onetomany.entity.Course;
import com.kvtsoft.hibernate.onetomany.entity.Instructor;
import com.kvtsoft.hibernate.onetomany.entity.InstructorDetail;

public class CreateInstructor {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create the objects
			Instructor instructor = new Instructor("Sachin", "kavtiyal", "sachink@kohana");
			InstructorDetail instructorDetail = new InstructorDetail("https://yoututbe.com/sasuke", "coding");

			// associate the objects in memory
			instructor.setInstructorDetail(instructorDetail);

			// start a transmission
			//
			// Note: this will also save the details object
			// because of CascadeType.ALL
			//
			System.out.println("\nSaving intructor... ");
			session.beginTransaction();

			// save the instructor
			session.save(instructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("\nObject has been saved successfully");
			System.out.println("Saved instructor. Generated id: " + instructor.getId());
			System.out.println("Saved instructor_details. Generated id: " + instructorDetail.getId());

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
