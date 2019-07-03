package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;

    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
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

    Car pick(ParkingTicket ticket) {
        return cars.remove(ticket);
    }

    int getCapacity() {
        return capacity;
    }
}
