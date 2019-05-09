package com.sci.razvan.proiectfinal.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="trip")

public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min=3, max=30)//mesage = "invalid")
    @Column(name ="tripname")
    private String tripName;
    @Size(min=3, max=30)//mesage = "invalid")
    @Column(name ="datefrom")
    private LocalDate dateFrom;
    @Column(name ="dateto")
    private LocalDate dateTo;
    @Size(min=0, max=100)
    private String impresion;
    @Size(min=3, max=30)//mesage = "invalid")
    @Column(name ="photo1_path")
    private String photoPath1;
    @Size(min=3, max=30)//mesage = "invalid")
    @Column(name ="photo2_path")
    private String photoPath2;

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
        return "Trips{" +
                "id=" + id +
                ", tripName='" + tripName + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", impresion='" + impresion + '\'' +
                ", photoPath1='" + photoPath1 + '\'' +
                ", photoPath2='" + photoPath2 + '\'' +
                '}';
    }
}
