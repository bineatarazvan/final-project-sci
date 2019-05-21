package com.sci.razvan.proiectfinal.model;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min=3, max=30)//mesage = "invalid")
    @Column(name ="tripname")
    private String tripName;

    @Column(name ="datefrom")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @Column(name ="dateto")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    private String impresion;

//    @Size(min=3, max=30)//mesage = "invalid")
//    @Lob
//    @Column(name ="photo1")
//    private byte[]             System.out.println("binding has erore trip!!!!!!!!!!!!!!!!!!!!!!!");;
//
//    @Size(min=3, max=30)//mesage = "invalid")
//    @Lob
//    @Column(name ="photo2")
//    private byte[] photoPath2;

    @Column(name ="photo1")
    private String photoPath1;

    @Column(name ="photo2")
    private String photoPath2;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    public Trip() {
    }

    public Trip(int id, @Size(min = 3, max = 30) String tripName,
                LocalDate dateFrom, LocalDate dateTo, String impresion, Users users) {
        this.id = id;
        this.tripName = tripName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.impresion = impresion;
        this.users = users;
    }

    public Trip(int id, @Size(min = 3, max = 30) String tripName, LocalDate dateFrom,
                LocalDate dateTo, String impresion, String photoPath1, Users users) {
        this.id = id;
        this.tripName = tripName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.impresion = impresion;
        this.photoPath1 = photoPath1;
        this.users = users;
    }

    public Trip(int id, @Size(min = 3, max = 30) String tripName,
                LocalDate dateFrom, LocalDate dateTo, String impresion, String photoPath1,
                String photoPath2, Users users) {
        this.id = id;
        this.tripName = tripName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.impresion = impresion;
        this.photoPath1 = photoPath1;
        this.photoPath2 = photoPath2;
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getImpresion() {
        return impresion;
    }

    public void setImpresion(String impresion) {
        this.impresion = impresion;
    }

    public String getPhotoPath1() {
        return photoPath1;
    }

    public void setPhotoPath1(String photoPath1) {
        this.photoPath1 = photoPath1;
    }

    public String getPhotoPath2() {
        return photoPath2;
    }

    public void setPhotoPath2(String photoPath2) {
        this.photoPath2 = photoPath2;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", tripName='" + tripName + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", impresion='" + impresion + '\'' +
                ", photoPath1='" + photoPath1 + '\'' +
                ", photoPath2='" + photoPath2 + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                Objects.equals(tripName, trip.tripName) &&
                Objects.equals(dateFrom, trip.dateFrom) &&
                Objects.equals(dateTo, trip.dateTo) &&
                Objects.equals(impresion, trip.impresion) &&
                Objects.equals(photoPath1, trip.photoPath1) &&
                Objects.equals(photoPath2, trip.photoPath2) &&
                Objects.equals(users, trip.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tripName, dateFrom, dateTo, impresion, photoPath1, photoPath2, users);
    }

//    public String getBase64Photo(){
//        return Base64.encodeBase64String(this.photoPath1);
//    }
}
