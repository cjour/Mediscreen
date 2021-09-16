package com.cjour.mediscreen.model;
import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name= "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String firstname;
    private String name;
    private Date birthdate;
    private String genre;
    private String address;
    private String phoneNumber;

    public Patient(){}

    public Patient(String firstname, String name, Date birthdate, String genre, String address, String phoneNumber) {
        super();
        this.firstname = firstname;
        this.name = name;
        this.birthdate = birthdate;
        this.genre = genre;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}


