package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    ParkingTicket parkFromParkingLots(Car car) {
        Optional<ParkingLot> lotOptional = parkingLots
                .stream().max(Comparator.comparingDouble(p -> 1.0 * p.getAvailableParkingPosition() / p.getCapacity()));

        return lotOptional.map(parkingLot -> parkingLot.park(car)).orElse(null);

    }
}
