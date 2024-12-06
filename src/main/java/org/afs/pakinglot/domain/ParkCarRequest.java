package org.afs.pakinglot.domain;

public class ParkCarRequest {

    private String strategy;

    private String carPlate;

    public ParkCarRequest(String strategy, String carPlate) {
        this.strategy = strategy;
        this.carPlate = carPlate;
    }

    public String getStrategy() {
        return strategy;
    }

    public String getCarPlate() {
        return carPlate;
    }
}
