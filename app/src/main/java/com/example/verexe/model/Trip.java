package com.example.verexe.model;

import java.time.LocalDate;
import java.util.UUID;

public class Trip {
    private UUID _id;
    private LocalDate dateDepart;
    private Float price;
    private String title;
    private int blank;
    private Location departure;
    private Location arrival;
    private String image;
    private Boolean online;
    private float rating;
    private long totalHour;

    public Trip() {
    }

    public Trip(UUID _id, LocalDate dateDepart, Float price, String title, int blank, Location departure, Location arrival, String image, Boolean online, float rating, long totalHour) {
        this._id = _id;
        this.dateDepart = dateDepart;
        this.price = price;
        this.title = title;
        this.blank = blank;
        this.departure = departure;
        this.arrival = arrival;
        this.image = image;
        this.online = online;
        this.rating = rating;
        this.totalHour = totalHour;
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBlank() {
        return blank;
    }

    public void setBlank(int blank) {
        this.blank = blank;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getArrival() {
        return arrival;
    }

    public void setArrival(Location arrival) {
        this.arrival = arrival;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    public long getTotalHour() {
        return totalHour;
    }
    public void setTotalHour(long totalHour) {
        this.totalHour = totalHour;
    }
}
