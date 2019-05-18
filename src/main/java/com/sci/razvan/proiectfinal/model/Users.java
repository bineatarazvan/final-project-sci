package com.sci.razvan.proiectfinal.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")

public class Users {
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
    private String username;
    @Size(min=8, max=1024)
    private String password;
    @Size(min=3, max=30)//mesage = "invalid")
    private String city;
    @Size(min=3, max=30)//mesage = "invalid")
    private String address;
    @Size(min=10, max=14)//mesage = "invalid")
    private String phone;
    @Size(min=3, max=14)//mesage = "invalid")
    private String role;

    public Users(int id, @Size(min = 3, max = 30) String firstName, @Size(min = 3, max = 30) String lastName, @Size(min = 1, max = 30) String username, @Size(min = 8, max = 12) String password, @Size(min = 3, max = 30) String city, @Size(min = 3, max = 30) String address, @Size(min = 10, max = 14) String phone, @Size(min = 3, max = 14) String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }

    public Users(@Size(min = 1, max = 30) String username, @Size(min = 8, max = 12) String password, @Size(min = 3, max = 14) String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Users(@Size(min = 1, max = 30) String username, @Size(min = 8, max = 12) String password) {
        this.username = username;
        this.password = password;
    }

    public Users() {
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
 /*
    public void removeTrips(Trip trip){
        tripsSet.remove(trip);
        trip.setUsers(null);
    }
    public void addTrips(Trip trip){
        tripsSet.add(trip);
        trip.setUsers(this);
    }
    */
}