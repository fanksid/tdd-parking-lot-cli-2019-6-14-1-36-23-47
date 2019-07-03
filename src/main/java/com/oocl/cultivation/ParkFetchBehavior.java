package com.oocl.cultivation;

public interface ParkFetchBehavior {
    Car pickCarFromParkingLots(ParkingTicket ticket);

    ParkingTicket parkFromParkingLots(Car car);
}
