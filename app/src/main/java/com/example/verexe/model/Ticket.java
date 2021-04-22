package com.example.verexe.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Ticket {
    private String _id;

    private Location fromLocation;

    private Location toLocation;

    private UserBus userBus;

    private UUID idCoach;

    private List<Seat> seatList;

    private Float totalPrice;

    private String note;

    private TypeTicket typeTicket;

    private List<Seat> seatHadDecline;

    private LocalDate createDate;

    public Ticket() {
    }

    public Ticket(String _id, Location fromLocation, Location toLocation, UserBus userBus, UUID idCoach, List<Seat> seatList, Float totalPrice, String note, TypeTicket typeTicket, List<Seat> seatHadDecline, LocalDate createDate) {
        this._id = _id;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.userBus = userBus;
        this.idCoach = idCoach;
        this.seatList = seatList;
        this.totalPrice = totalPrice;
        this.note = note;
        this.typeTicket = typeTicket;
        this.seatHadDecline = seatHadDecline;
        this.createDate = createDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

    public UserBus getUserBus() {
        return userBus;
    }

    public void setUserBus(UserBus userBus) {
        this.userBus = userBus;
    }

    public UUID getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(UUID idCoach) {
        this.idCoach = idCoach;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }

    public List<Seat> getSeatHadDecline() {
        return seatHadDecline;
    }

    public void setSeatHadDecline(List<Seat> seatHadDecline) {
        this.seatHadDecline = seatHadDecline;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "_id='" + _id + '\'' +
                ", fromLocation=" + fromLocation +
                ", toLocation=" + toLocation +
                ", userBus=" + userBus +
                ", idCoach=" + idCoach +
                ", seatList=" + seatList +
                ", totalPrice=" + totalPrice +
                ", note='" + note + '\'' +
                ", typeTicket=" + typeTicket +
                ", seatHadDecline=" + seatHadDecline +
                ", createDate=" + createDate +
                '}';
    }
}
