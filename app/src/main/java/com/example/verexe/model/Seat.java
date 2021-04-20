package com.example.verexe.model;

public class Seat {
    private int seat_type;
    private int seat_number;
    private String seat_code;
    private int row_num;
    private int col_num;
    private int row_span;
    private int col_span;
    private SeatType is_available;
    private Float fare;
    private Fare fares;

    public Seat(int seat_type, int seat_number, String seat_code, int row_num, int col_num, int row_span, int col_span, SeatType is_available, Float fare, Fare fares) {
        this.seat_type = seat_type;
        this.seat_number = seat_number;
        this.seat_code = seat_code;
        this.row_num = row_num;
        this.col_num = col_num;
        this.row_span = row_span;
        this.col_span = col_span;
        this.is_available = is_available;
        this.fare = fare;
        this.fares = fares;
    }

    public Seat() {
    }

    public int getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(int seat_type) {
        this.seat_type = seat_type;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public String getSeat_code() {
        return seat_code;
    }

    public void setSeat_code(String seat_code) {
        this.seat_code = seat_code;
    }

    public int getRow_num() {
        return row_num;
    }

    public void setRow_num(int row_num) {
        this.row_num = row_num;
    }

    public int getCol_num() {
        return col_num;
    }

    public void setCol_num(int col_num) {
        this.col_num = col_num;
    }

    public int getRow_span() {
        return row_span;
    }

    public void setRow_span(int row_span) {
        this.row_span = row_span;
    }

    public int getCol_span() {
        return col_span;
    }

    public void setCol_span(int col_span) {
        this.col_span = col_span;
    }

    public SeatType getIs_available() {
        return is_available;
    }

    public void setIs_available(SeatType is_available) {
        this.is_available = is_available;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }

    public Fare getFares() {
        return fares;
    }

    public void setFares(Fare fares) {
        this.fares = fares;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat_type=" + seat_type +
                ", seat_number=" + seat_number +
                ", seat_code='" + seat_code + '\'' +
                ", row_num=" + row_num +
                ", col_num=" + col_num +
                ", row_span=" + row_span +
                ", col_span=" + col_span +
                ", is_available=" + is_available +
                ", fare=" + fare +
                ", fares=" + fares +
                '}';
    }
}
