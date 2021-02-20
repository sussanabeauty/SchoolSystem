package org.susanacode.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "student")
public class Students
{
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy="increment")
    @Column(name = "id", updatable=false, nullable=false)
    private int id;

   @Column(name = "student_name")
    private  StudentName name;


    @Column(name="BirthDate")
    private LocalDate DOB;
   
    @ManyToMany //(cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    //@Column(name = "course_id")
    private Set<Course> courses = new HashSet<>();


    @Column(name = "gender")
    private String gender;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public StudentName getName() {
        return name;
    }

    public void setName(StudentName name) {
        this.name = name;
    }

    public LocalDate getDOB() { return DOB; }


    public void setDOB(int iYear, int iMonth, int iDay) {
        this.DOB = LocalDate.of(iYear, iMonth, iDay);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name=" + name +
                ", DOB='" + DOB + '\'' +
                ", courses=" + courses +
                ", gender='" + gender + '\'' +
                '}';
    }
}
