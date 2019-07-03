package com.oocl.cultivation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingManager extends ParkFetchBaseBehavior {
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

        System.out.println(getParkingBoysErrorMsg());
        return null;
    }

    private String getParkingBoysErrorMsg() {
        return parkingBoys.stream()
                .map(ParkingBoy::getLastErrorMessage)
                .collect(Collectors.joining());
    }

    public Car fetchByBoy(ParkingTicket ticket) {
        for (ParkingBoy parkingBoy : parkingBoys) {
            Car car = parkingBoy.fetch(ticket);
            if (Objects.nonNull(car)) {
                return car;
            }
        }
        System.out.println(getParkingBoysErrorMsg());
        return null;
    }

    public ParkingTicket park(Car car) {
        return super.parkFromParkingLots(car);
    }

    public Car fetch(ParkingTicket ticket) {
        return super.pickCarFromParkingLots(ticket);
    }

    @Override
    List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
