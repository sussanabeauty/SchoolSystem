package org.susanacode.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "id", updatable=false, nullable=false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy="increment")
    private int id;

    @Column(name = "course_name")
    private String courseName;

    @ManyToMany(mappedBy="courses") // telling the program which of the classes will do the mapping
    //@Column(name = "student_id")
    private Set<Students> students = new HashSet<>();


    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public Set<Students> getStudents() {
        return students;
    }

    public void setStudents(Set<Students> students) {
        this.students = students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id + ", courseName='" + courseName + '\'' +
                ", students=" + students + ", professor=" + professor + '}';
    }
}
