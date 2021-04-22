package com.example.verexe.model;

import java.util.List;
import java.util.UUID;

public class Intersection {
    private UUID uuid;
    private Float price;
    private List<Location> fromLocation;
    private List<Location> toLocation;

    public Intersection(UUID uuid, Float price, List<Location> fromLocation, List<Location> toLocation) {
        this.uuid = uuid;
        this.price = price;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public Intersection() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Location> getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(List<Location> fromLocation) {
        this.fromLocation = fromLocation;
    }

    public List<Location> getToLocation() {
        return toLocation;
    }

    public void setToLocation(List<Location> toLocation) {
        this.toLocation = toLocation;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "uuid=" + uuid +
                ", price=" + price +
                ", fromLocation=" + fromLocation +
                ", toLocation=" + toLocation +
                '}';
    }
}
