package com.mycompany.demo;


import com.mycompany.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.sound.midi.Soundbank;

public class AddMoreCoursesToStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .addAnnotatedClass(Course.class)
                                            .addAnnotatedClass(Review.class)
                                            .addAnnotatedClass(Student.class)
                                            .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {

            session.beginTransaction();
            int myID = 2;
            Student student = session.get(Student.class, myID);
            System.out.println("Loaded: "+student.toString());
            System.out.println("List of courses for - "+student.getFirstName()+": " +student.getCourses());

            Course course1 = new Course("Hibernate hands on course!");
            Course course2 = new Course("AWS Core");

            course1.addStudent(student);
            course2.addStudent(student);

            System.out.println("Saving courses ...");
            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();
            System.out.println("Done");





        } catch (Exception ex)  {
            ex.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
