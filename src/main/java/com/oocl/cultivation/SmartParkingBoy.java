package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkFromParkingLots(Car car) {
        Optional<ParkingLot> lotOptional = parkingLots
                .stream().max(Comparator.comparingInt(ParkingLot::getAvailableParkingPosition));

        return lotOptional.map(parkingLot -> parkingLot.park(car)).orElse(null);

    }
}
