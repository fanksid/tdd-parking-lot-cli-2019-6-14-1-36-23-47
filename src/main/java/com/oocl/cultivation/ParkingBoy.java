package com.oocl.cultivation;

import java.util.Objects;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = parkingLot.park(car);
        resetErrorMsg(ticket);
        return ticket;
    }

    private void resetErrorMsg(ParkingTicket ticket) {
        if (Objects.nonNull(ticket)) {
            lastErrorMessage = null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = parkingLot.pick(ticket);
        arrangeErrorMsg(car);
        return car;
    }

    private void arrangeErrorMsg(Car car) {
        if (Objects.isNull(car)) {
            lastErrorMessage = "Unrecognized parking ticket.";
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
