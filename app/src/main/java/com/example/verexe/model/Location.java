package com.example.verexe.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalTime;

public class Location  implements Parcelable {
    private int idProject;
    private String nameProject;
    private String nameProvince;
    private String nameDistrict;
    private String timeBegin;
    private int hour;
    private int minute;

    public Location(int idProject, String nameProject, String nameProvince, String nameDistrict, String timeBegin, int hour, int minute) {
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

    protected Location(Parcel in) {
        idProject = in.readInt();
        nameProject = in.readString();
        nameProvince = in.readString();
        nameDistrict = in.readString();
        timeBegin = in.readString();
        hour = in.readInt();
        minute = in.readInt();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

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

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
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

    @Override
    public String toString() {
        return "Location{" +
                "idProject=" + idProject +
                ", nameProject='" + nameProject + '\'' +
                ", nameProvince='" + nameProvince + '\'' +
                ", nameDistrict='" + nameDistrict + '\'' +
                ", timeBegin='" + timeBegin + '\'' +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idProject);
        parcel.writeString(nameProject);
        parcel.writeString(nameProvince);
        parcel.writeString(nameDistrict);
        parcel.writeString(timeBegin);
        parcel.writeInt(hour);
        parcel.writeInt(minute);
    }
}
