package org.afs.pakinglot.domain;

public class ParkCarRequest {

    private String strategy;

    private Car car;

    public ParkCarRequest(String strategy, Car car) {
        this.strategy = strategy;
        this.car = car;
    }

    public String getStrategy() {
        return strategy;
    }

    public Car getCar() {
        return car;
    }
}
