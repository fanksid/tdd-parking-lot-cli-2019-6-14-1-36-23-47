package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.SmartParkingBoy;
import com.oocl.cultivation.SuperSmartParkingBoy;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperSmartParkingBoyFacts {

    @Test
    void should_park_car_into_large_capacity_parking_lot_when_smart_boy_park() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());
        ParkingLot parkingLotTwo = new ParkingLot(5);
        parkingLotTwo.park(new Car());
        parkingLotTwo.park(new Car());
        parkingLotTwo.park(new Car());
        ArrayList<ParkingLot> lots = new ArrayList<>();
        lots.add(parkingLot);
        lots.add(parkingLotTwo);
        SuperSmartParkingBoy smartParkingBoy = new SuperSmartParkingBoy(lots);

        Car car = new Car();
        ParkingTicket ticket = smartParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals(2, parkingLotTwo.getAvailableParkingPosition());
        assertEquals(0, parkingLot.getAvailableParkingPosition());
        assertSame(car, smartParkingBoy.fetch(ticket));
    }
}
