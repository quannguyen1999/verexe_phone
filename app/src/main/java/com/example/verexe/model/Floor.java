package com.example.verexe.model;

import java.util.List;

public class Floor {
    private int coach_num;
    private String coach_name;
    private int num_rows;
    private int num_cols;
    private List<Seat> seats;

    public Floor(int coach_num, String coach_name, int num_rows, int num_cols, List<Seat> seats) {
        this.coach_num = coach_num;
        this.coach_name = coach_name;
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        this.seats = seats;
    }

    public Floor() {
    }

    public int getCoach_num() {
        return coach_num;
    }

    public void setCoach_num(int coach_num) {
        this.coach_num = coach_num;
    }

    public String getCoach_name() {
        return coach_name;
    }

    public void setCoach_name(String coach_name) {
        this.coach_name = coach_name;
    }

    public int getNum_rows() {
        return num_rows;
    }

    public void setNum_rows(int num_rows) {
        this.num_rows = num_rows;
    }

    public int getNum_cols() {
        return num_cols;
    }

    public void setNum_cols(int num_cols) {
        this.num_cols = num_cols;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "coach_num=" + coach_num +
                ", coach_name='" + coach_name + '\'' +
                ", num_rows=" + num_rows +
                ", num_cols=" + num_cols +
                ", seats=" + seats +
                '}';
    }
}
