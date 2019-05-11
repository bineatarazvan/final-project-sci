package com.sci.razvan.proiectfinal.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min=3, max=30)//mesage = "invalid")
    @Column(name ="firstname")
    private String firstName;
    @Size(min=3, max=30)//mesage = "invalid")
    @Column(name ="lastname")
    private String lastName;
    @Size(min=1, max=30)//mesage = "invalid")
    @Column(name ="username")
    private String userName;
    @Size(min=8, max=12)
    private String password;
    @Size(min=3, max=30)//mesage = "invalid")
    private String city;
    @Size(min=3, max=30)//mesage = "invalid")
    private String address;
    @Size(min=10, max=14)//mesage = "invalid")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
