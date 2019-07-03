package com.oocl.cultivation.test;

import com.google.common.collect.Lists;
import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingManager;
import com.oocl.cultivation.ParkingTicket;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ParkingManagerFacts {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    void should_find_parking_boy_when_manager_add_boy_into_manage_list() {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        ParkingLot parkingLotThree = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(Lists.newArrayList(parkingLot, parkingLotTwo));

        ParkingManager parkingManager = new ParkingManager(Lists.newArrayList(parkingLotThree), Lists.newArrayList());

        parkingManager.addBoy(parkingBoy);

        assertTrue(CollectionUtils.isNotEmpty(parkingManager.getParkingBoys()));
        assertTrue(parkingManager.getParkingBoys().contains(parkingBoy));
    }

    @Test
    void should_park_and_fetch_car_by_parking_boy_when_manager_park_and_pick_by_boy() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        given(parkingBoy.park(any())).willReturn(new ParkingTicket());
        given(parkingBoy.fetch(any())).willReturn(car);

        ParkingManager parkingManager = new ParkingManager(Lists.newArrayList(parkingLot), Lists.newArrayList(parkingBoy));

        ParkingTicket ticket = parkingManager.parkByBoy(car);
        Car pickedCar = parkingManager.fetchByBoy(ticket);

        Mockito.verify(parkingBoy).park(car);
        Mockito.verify(parkingBoy).fetch(any());
        Assertions.assertSame(car, pickedCar);
    }

    @Test
    void should_park_and_fetch_car_by_manager_when_manager_park_and_pick_car() {
        ParkingLot parkingLot = new ParkingLot(2);

        ParkingManager parkingManager = new ParkingManager(Lists.newArrayList(parkingLot), Lists.newArrayList());

        Car car = new Car();
        ParkingTicket ticket = parkingManager.park(car);
        Car pickedCar = parkingManager.fetch(ticket);

        Assertions.assertSame(car, pickedCar);
    }

    @Test
    void should_output_error_msg_when_parking_boy_fail_to_operation_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
        ParkingLot parkingLotTwo = new ParkingLot(1);
        parkingLotTwo.park(new Car());
        ParkingBoy parkingBoy = new ParkingBoy(Lists.newArrayList(parkingLot, parkingLotTwo));

        ParkingManager parkingManager = new ParkingManager(Lists.newArrayList(), Lists.newArrayList(parkingBoy));

        parkingManager.parkByBoy(new Car());

        assertEquals("The parking lot is full.\n", systemOut());
    }
}
