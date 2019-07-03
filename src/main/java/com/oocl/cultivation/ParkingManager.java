package com.oocl.cultivation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingManager {
    private final List<ParkingLot> parkingLots;

    private final List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        this.parkingLots = parkingLots;
        this.parkingBoys = parkingBoys;
    }


    public void addBoy(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public ParkingTicket parkByBoy(Car car) {
        for (ParkingBoy parkingBoy : parkingBoys) {
            ParkingTicket ticket = parkingBoy.park(car);
            if (Objects.nonNull(ticket)) {
                return ticket;
            }
        }

        System.out.println(parkingBoys.stream().map(ParkingBoy::getLastErrorMessage).collect(Collectors.joining()));
        return null;
    }

    public Car fetchByBoy(ParkingTicket ticket) {
        for (ParkingBoy parkingBoy : parkingBoys) {
            Car car = parkingBoy.fetch(ticket);
            if (Objects.nonNull(car)) {
                return car;
            }
        }
        System.out.println(parkingBoys.stream().map(ParkingBoy::getLastErrorMessage).collect(Collectors.joining()));
        return null;
    }

    public ParkingTicket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            ParkingTicket ticket = parkingLot.park(car);
            if (Objects.nonNull(ticket)) {
                return ticket;
            }
        }
        return null;
    }

    public Car fetch(ParkingTicket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            Car car = parkingLot.pick(ticket);
            if (Objects.nonNull(car)) {
                return car;
            }
        }
        return null;
    }
}
