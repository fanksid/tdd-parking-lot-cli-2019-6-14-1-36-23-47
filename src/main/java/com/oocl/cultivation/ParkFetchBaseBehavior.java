package com.oocl.cultivation;

import java.util.List;
import java.util.Objects;

public abstract class ParkFetchBaseBehavior implements ParkFetchBehavior {
    abstract List<ParkingLot> getParkingLots();

    @Override
    public Car pickCarFromParkingLots(ParkingTicket ticket) {
        for (ParkingLot parkingLot : getParkingLots()) {
            Car car = parkingLot.pick(ticket);
            if (Objects.nonNull(car)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public ParkingTicket parkFromParkingLots(Car car) {
        for (ParkingLot parkingLot : getParkingLots()) {
            ParkingTicket ticket = parkingLot.park(car);
            if (Objects.nonNull(ticket)) {
                return ticket;
            }
        }
        return null;
    }
}
