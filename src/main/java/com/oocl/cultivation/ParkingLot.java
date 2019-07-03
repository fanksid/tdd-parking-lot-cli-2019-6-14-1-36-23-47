package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

    public ParkingTicket park(Car car) {
        if (getAvailableParkingPosition() == 0) {
            return null;
        }
        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);
        return ticket;
    }

    public Car pick(ParkingTicket ticket) {
        return cars.remove(ticket);
    }
}
