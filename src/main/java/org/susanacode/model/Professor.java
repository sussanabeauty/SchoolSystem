package org.susanacode.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name="professor")
public class Professor {

    @Id
    @Column(name = "id", updatable=false, nullable=false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy="increment")
    private int id;

    @Column(name="professor_name")
    private String fullname;

    @Column(name="professor_email")
    private String email;

    @OneToOne(fetch=FetchType.LAZY)
    private Course course;


    public Professor(String fullname) {
        this.fullname = fullname;
        this.email = this.fullname.toLowerCase().replaceAll("\\s", "") + "@prof.edu";
    }

    public Professor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String name) {
        this.fullname = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", name='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", course=" + course +
                '}';
    }
}
