package com.sci.razvan.proiectfinal.model;

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

    @Size(min=0, max=100)
    private String impresion;

    @Size(min=3, max=30)//mesage = "invalid")
    @Lob
    @Column(name ="photo1")
    private byte[] photoPath1;

    @Size(min=3, max=30)//mesage = "invalid")
    @Lob
    @Column(name ="photo2")
    private byte[] photoPath2;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    public Trip() {
    }

    public Trip(int id, @Size(min = 3, max = 30) String tripName,
                @Size(min = 3, max = 30) LocalDate dateFrom, LocalDate dateTo,
                @Size(min = 0, max = 100) String impresion, @Size(min = 3, max = 30) byte[] photoPath1,
                @Size(min = 3, max = 30) byte[] photoPath2, Users users) {
        this.id = id;
        this.tripName = tripName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.impresion = impresion;
        this.photoPath1 = photoPath1;
        this.photoPath2 = photoPath2;
        this.users = users;
    }

    public Trip(int id, @Size(min = 3, max = 30) String tripName,
                @Size(min = 3, max = 30) LocalDate dateFrom, LocalDate dateTo,
                @Size(min = 0, max = 100) String impresion, Users users) {
        this.id = id;
        this.tripName = tripName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.impresion = impresion;
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

    public byte[] getPhotoPath1() {
        return photoPath1;
    }

    public void setPhotoPath1(byte[] photoPath1) {
        this.photoPath1 = photoPath1;
    }

    public byte[] getPhotoPath2() {
        return photoPath2;
    }

    public void setPhotoPath2(byte[] photoPath2) {
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
                ", photoPath1=" + Arrays.toString(photoPath1) +
                ", photoPath2=" + Arrays.toString(photoPath2) +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trips = (Trip) o;
        return id == trips.id &&
                Objects.equals(tripName, trips.tripName) &&
                Objects.equals(dateFrom, trips.dateFrom) &&
                Objects.equals(dateTo, trips.dateTo) &&
                Objects.equals(impresion, trips.impresion) &&
                Arrays.equals(photoPath1, trips.photoPath1) &&
                Arrays.equals(photoPath2, trips.photoPath2) &&
                Objects.equals(users, trips.users);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, tripName, dateFrom, dateTo, impresion, users);
        result = 31 * result + Arrays.hashCode(photoPath1);
        result = 31 * result + Arrays.hashCode(photoPath2);
        return result;
    }
}
