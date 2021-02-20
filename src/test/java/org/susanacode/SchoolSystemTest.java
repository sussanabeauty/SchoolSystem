package org.susanacode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import org.junit.jupiter.api.*;

import org.susanacode.model.Course;
import org.susanacode.model.Professor;
import org.susanacode.model.StudentName;
import org.susanacode.model.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


class SchoolSystemTest {
    private static SessionFactory sessionFactory;
    private Session session;



    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("session factory Begin\n");

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        sessionFactory.close();
        System.out.println("session factory closed\n");
    }


    @BeforeEach
    void setUp() throws Exception {

        session = sessionFactory.openSession();
        System.out.println("session Begin \n");

    }

    @AfterEach
    void tearDown()  throws Exception {

        session.close();
        System.out.println("session closed \n");
    }

    @Test
    void testCreate(){
        System.out.println("Running test Create...");
        session.beginTransaction();

        int id = 0;

        //Class Instances Declaration
        StudentName name = new StudentName("Andy","Walt","Brown" );

        Students student1 = new Students();
        student1.setName(name);
        student1.setDOB(2015, 7, 28);
        student1.setGender("male");

        Professor professor = new Professor();
        professor.setFullName("George Hurts");

        Course course1 = new Course();
        course1.setCourseName("Biology");
        course1.getStudents().add(student1);
        course1.setProfessor(professor);
        student1.getCourses().add(course1);
        professor.setCourse(course1);

        id = (Integer) session.save(student1);
        id = (Integer) session.save(course1);
        id = (Integer) session.save(professor);

        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

    @Test
    void testRead(){
        System.out.println("Running testRead...");


        Query query = session.createQuery("from Students");
        List<Students> studList = query.getResultList();

        Assertions.assertTrue(studList.isEmpty());


//
//        List<Course> courseList = new ArrayList<>();
//        assertTrue(courseList.isEmpty());

//        List<Professor> professorList = new ArrayList<>();
//        assertTrue(professorList.isEmpty());

    }




    @Test
    void testUpdate(){

        System.out.println("Running test Update...");


        int id = 1;

        session.beginTransaction();
        //update a course and professor;
        Course changeCourse = new Course();
        changeCourse.setId(id);
        changeCourse.setCourseName("Communication");

        Professor changeProf = new Professor();
        changeProf.setId(1);
        changeProf.setFullName("Mick Mouse");
        changeProf.setCourse(changeCourse);
        changeCourse.setProfessor(changeProf);

        //do update here
        session.update(changeCourse);
        session.update(changeProf);


        session.getTransaction().commit();

        Course updateCourse = session.find(Course.class, id);

        assertEquals("Communication", updateCourse.getCourseName());



    }

    @Test
    void testDelete(){

        System.out.println("Running testDelete...");

        session.beginTransaction();

        int id = 0;

        //delete from the course and professor table by id;
        Course deleteCourse = new Course();
        deleteCourse.setId(1);

        Professor deleteProf = new Professor();
        deleteProf.setId(1);


        //do update here
        session.delete(deleteProf);
        session.delete(deleteCourse);

        session.getTransaction().commit(); //commit Transaction

        assertTrue(id < 0);
    }
}