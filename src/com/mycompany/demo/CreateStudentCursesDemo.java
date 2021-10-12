package com.mycompany.demo;


import com.mycompany.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentCursesDemo {

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

            Course tempCourse = new Course("Spring 5 - step by step guide");
            session.save(tempCourse);
            System.out.println("Course saved: "+tempCourse.toString());

            Student student1 = new Student("Evgeniy", "Dmitriev", "edmitr@gmail.com");
            Student student2 = new Student("Anna", "Ivanenko", "anniv@ciklum.com");

            tempCourse.addStudent(student1);
            tempCourse.addStudent(student2);

            session.save(student1);
            session.save(student2);
            System.out.println("Saved students: "+tempCourse.getStudents());


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
