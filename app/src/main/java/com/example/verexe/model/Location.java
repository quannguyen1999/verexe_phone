package com.example.verexe.model;

import java.time.LocalTime;

public class Location {
    private int idProject;
    private String nameProject;
    private String nameProvince;
    private String nameDistrict;
    private LocalTime timeBegin;
    private int hour;
    private int minute;

    public Location(int idProject, String nameProject, String nameProvince, String nameDistrict, LocalTime timeBegin, int hour, int minute) {
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.nameProvince = nameProvince;
        this.nameDistrict = nameDistrict;
        this.timeBegin = timeBegin;
        this.hour = hour;
        this.minute = minute;
    }

    public Location() {
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}