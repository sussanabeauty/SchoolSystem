package org.susanacode.model;

import javax.persistence.Embeddable;

@Embeddable  //this embedded this class to the student class
public class StudentName {
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;



    public StudentName(String firstname, String middlename, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;

        this.email = this.firstname.toLowerCase() + this.middlename.toLowerCase()+"_"+
                this.lastname.toLowerCase()+ "@stu.edu";

   }

    public StudentName() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Override
    public String toString() {
        return "StudentName{" +
                "firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
