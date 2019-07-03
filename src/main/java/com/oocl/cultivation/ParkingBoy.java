package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {

    final List<ParkingLot> parkingLots;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new ArrayList<ParkingLot>() {{
            add(parkingLot);
        }};
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        if (Objects.isNull(car)) {
            return null;
        }

        ParkingTicket ticket = parkFromParkingLots(car);
        resetErrorMsgByParkingTicket(ticket);
        return ticket;
    }

    ParkingTicket parkFromParkingLots(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            ParkingTicket ticket = parkingLot.park(car);
            if (Objects.nonNull(ticket)) {
                return ticket;
            }
        }
        return null;
    }

    private void resetErrorMsgByParkingTicket(ParkingTicket ticket) {
        if (Objects.nonNull(ticket)) {
            lastErrorMessage = null;
        } else {
            lastErrorMessage = "The parking lot is full.";
        }
    }

    public Car fetch(ParkingTicket ticket) {
        if (Objects.isNull(ticket)) {
            lastErrorMessage = "Please provide your parking ticket.";
            return null;
        }
        Car car = pickCarFromParkingLots(ticket);
        setInvalidTicketMsg(car);
        return car;
    }

    private Car pickCarFromParkingLots(ParkingTicket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            Car car = parkingLot.pick(ticket);
            if (Objects.nonNull(car)) {
                return car;
            }
        }
        return null;
    }

    private void setInvalidTicketMsg(Car car) {
        if (Objects.isNull(car)) {
            lastErrorMessage = "Unrecognized parking ticket.";
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
