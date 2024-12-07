// src/main/java/org/afs/pakinglot/domain/exception/CarNotFoundException.java
package org.afs.pakinglot.domain.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String carPlate) {
        super("Car with plate number " + carPlate + " not found.");
    }
}
