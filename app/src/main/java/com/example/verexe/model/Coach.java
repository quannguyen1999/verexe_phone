package com.example.verexe.model;

import java.util.List;
import java.util.UUID;

public class Coach {
    private UUID id;

    private int countSeatEmpty;

    private List<Floor> floor;

    public Coach() {
    }

    public Coach(UUID id, int countSeatEmpty, List<Floor> floor) {
        this.id = id;
        this.countSeatEmpty = countSeatEmpty;
        this.floor = floor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getCountSeatEmpty() {
        return countSeatEmpty;
    }

    public void setCountSeatEmpty(int countSeatEmpty) {
        this.countSeatEmpty = countSeatEmpty;
    }

    public List<Floor> getFloor() {
        return floor;
    }

    public void setFloor(List<Floor> floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", countSeatEmpty=" + countSeatEmpty +
                ", floor=" + floor +
                '}';
    }
}
