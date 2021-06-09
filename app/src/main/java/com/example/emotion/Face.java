package com.example.emotion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Face implements Serializable {

    private String email;
    private String name;
    private boolean happy;
    private boolean normal;
    private boolean unHappy;
    private LocalDateTime date;

    public Face(){

    }

    public Face(String email, String name, boolean happy, boolean normal, boolean unHappy, LocalDateTime date) {

        this.email = email;
        this.name = name;
        this.happy = happy;
        this.normal = normal;
        this.unHappy = unHappy;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public boolean isUnHappy() {
        return unHappy;
    }

    public void setUnHappy(boolean unHappy) {
        this.unHappy = unHappy;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Face{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", happy=" + happy +
                ", normal=" + normal +
                ", unHappy=" + unHappy +
                ", date=" + date +
                '}';
    }
}

