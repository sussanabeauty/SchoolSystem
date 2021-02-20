package org.susanacode;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.susanacode.model.Course;
import org.susanacode.model.Professor;
import org.susanacode.model.StudentName;
import org.susanacode.model.Students;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    protected SessionFactory sessionFactory;

    public static void main( String[] args ) {

        App myApp = new App();
        myApp.getConfiguration();

        //call method to create and save entity data into database
        //myApp.saveEntity();

        //read from the tables to a json format
        //myApp.readStudentsList();
        //myApp.readData();


        //delete a column from the table
        //myApp.updateData();

        //read from the tables
        //myApp.deleteData();

        myApp.exit();

    }

    public List<Students> readStudentsList(){
        Session session = null;
        List<Students> studentList = null;
        try{
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Students");

            studentList = query.list();

            for (Students s : studentList){
                System.out.println(s.getName()+ ", " +s.getDOB()+ ", " +s.getGender());
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            session.close();
        }


        return studentList;
    }

    protected void deleteData() {
        //start your session
        Session session = sessionFactory.openSession();
        //start transaction
        session.beginTransaction();

        //delete from the course and professor table by id;
        Course deleteCourse = new Course();
        deleteCourse.setId(3);

        Professor deleteProf = new Professor();
        deleteProf.setId(3);


        //do update here
        session.delete(deleteProf);
        session.delete(deleteCourse);

        session.getTransaction().commit(); //commit Transaction
        session.close(); //close session

    }

    protected void updateData() {

        //start your session
        Session session = sessionFactory.openSession();
        //start transaction
        session.beginTransaction();

        //update a course and professor;
        Course changeCourse = new Course();
        changeCourse.setId(3);
        changeCourse.setCourseName("Communication");

        Professor changeProf = new Professor("Mick Mouse");
        changeProf.setId(3);
        changeProf.setCourse(changeCourse);
        changeCourse.setProfessor(changeProf);

        //do update here
        session.update(changeCourse);
        session.update(changeProf);


        session.getTransaction().commit(); //commit Transaction
        session.close(); //close session


    }

    protected void readData() {
        //start your session
        Session session = sessionFactory.openSession();


         Query query = session.createQuery
                ("SELECT s.name, s.DOB, s.gender, c.courseName, p.fullname, p.email " +
                        "FROM Students s, Course c INNER JOIN c.professor p");


        List<Object[]> students = query.list();
        System.out.println("-------------------------------------------------");
        for(Object[] obj : students){
             System.out.println(Arrays.toString(obj));

        }
        System.out.println("-------------------------------------------------");

        //close session
        session.close();

    }




    //save to the database table
    protected void saveEntity() {

        //start your session
        Session session = sessionFactory.openSession();
        //start transaction
        session.beginTransaction();

        /*************************************************/

        //Class Instances Declaration
        StudentName name1 = new StudentName("Joelle","Barnes","White");
        StudentName name2 = new StudentName("Janice", "Park", "Hui");
        StudentName name3 = new StudentName("Louis", "", "Adams");
        StudentName name4 = new StudentName("Adams", "", "Jones" );

        Students stu1 = new Students();

        stu1.setName(name1);
        stu1.setDOB(2015, 7, 28);
        stu1.setGender("female");


        Students stu2 = new Students();
        stu2.setName(name2);  //use embeddable object
        stu2.setDOB(2013, Calendar.MARCH, 25);
        //stu2.setDOB(2013, 3, 25);
        stu2.setGender("female");


        Students stu3 = new Students();
        stu3.setName(name3);
        stu3.setDOB(2015, 10, 13);
        stu3.setGender("male");


        Students stu4 = new Students();
        stu4.setName(name4);  //use embeddable object
        stu4.setDOB(2012, 9, 30);
        stu4.setGender("male");


        Professor prof1 = new Professor("George Hurts");
        Professor prof2 = new Professor("Sara Michell");
        Professor prof3 = new Professor("Annie Gown");


        Course course1 = new Course();
        course1.setCourseName("Biology");
        course1.getStudents().add(stu1);
        course1.getStudents().add(stu2);
        course1.getStudents().add(stu3);
        course1.setProfessor(prof1);

        prof1.setCourse(course1);


        //add student course
        stu1.getCourses().add(course1);
        stu2.getCourses().add(course1);
        stu3.getCourses().add(course1);


        Course course2 = new Course();
        course2.setCourseName("Mathematics");
        course2.getStudents().add(stu1);
        course2.getStudents().add(stu2);
        course2.getStudents().add(stu3);
        course2.getStudents().add(stu4);
        course2.setProfessor(prof2);
        prof2.setCourse(course2);


        //add student course
        stu1.getCourses().add(course2);
        stu2.getCourses().add(course2);
        stu3.getCourses().add(course2);
        stu4.getCourses().add(course2);


        Course course3 = new Course();
        course3.setCourseName("Spanish");
        course3.getStudents().add(stu1);
        course3.getStudents().add(stu4);
        course3.setProfessor(prof3);
        prof3.setCourse(course3);

        //add student course
        stu1.getCourses().add(course3);
        stu4.getCourses().add(course3);



        /************************************************************/

        //saving the data into the database
        session.save(stu1);
        session.save(stu2);
        session.save(stu3);
        session.save(stu4);
        session.save(prof1);
        session.save(prof2);
        session.save(prof3);
        session.save(course1);
        session.save(course2);
        session.save(course3);

        session.getTransaction().commit();
        session.close();

    }

//
//    //calling the retrieve table
//    private static JSONObject retrieveData() {
//
//        //call sessionfactory object to access session
//        SessionFactory sf = getConfiguration();
//        Session session = sf.openSession();
//        session.beginTransaction();
//
//        Query q1 = session.createQuery("SELECT s FROM Students s INNER JOIN FETCH s.courses c");
//        List<Students> studRecord = q1.list();
//
//        System.out.println("**************************************************************");
//        for (Students s1: studRecord){
//            System.out.println(s1.getName() + " " + s1.getAge() + s1.getGender() + s1.getCourses());
//
//        }
//
//        System.out.println("**************************************************************");
//
//        //read the the records with a hibernate fetch statement
//       // Query query = session.createQuery("from Students s inner join Course c where s.id = c.id");
//        Query query = session.createQuery("from Students");
//        List<Students> stud = query.list();
//
//        System.out.println("---------------" + stud);
//        for (Students s: stud){
//            System.out.println(s);
//        }
//
//
//
//
//        //create a json object
//        JSONObject jsonObj = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        jsonArray.add(stud);
//        jsonObj.put("Student_Records", jsonArray);
//
//        session.getTransaction().commit();
//        session.close();
//
//        return jsonObj;
//    }







    //connections
    protected void getConfiguration(){

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try{
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        }catch(Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    protected void exit(){
        sessionFactory.close();
    }


    //using hibernate Programmatic Configuration

    protected void getConfiguration_without_using_hibernate_configurationXML(){

        Configuration config = new Configuration();

        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/schoolsystem");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        config.setProperty("hibernate.useUnicode", "true");
//        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.hbm2ddl.auto","update");

        config.addAnnotatedClass(Students.class);
        config.addAnnotatedClass(Course.class);
        config.addAnnotatedClass(Professor.class);
        sessionFactory = config.buildSessionFactory();

    }









}
