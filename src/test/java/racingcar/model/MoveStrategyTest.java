package racingcar.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoveStrategyTest {

    // DefaultMoveStrategy가 4 이상일 때 이동해야 하는지 검증
    @Test
    void testDefaultMoveStrategy() {
        MoveStrategy strategy = new DefaultMoveStrategy();

        assertTrue(strategy.shouldMove(4));
        assertTrue(strategy.shouldMove(5));
        assertTrue(strategy.shouldMove(9));
        assertFalse(strategy.shouldMove(3));
        assertFalse(strategy.shouldMove(0));
    }

    // Car가 DefaultMoveStrategy에 따라 올바르게 이동하는지 검증
    @Test
    void testCarWithDefaultStrategy() {
        Car car = new Car("test");

        // 초기 위치는 0
        assertEquals(0, car.getPosition());

        // 4 이상이면 이동 (DefaultMoveStrategy)
        car.move(4);
        assertEquals(1, car.getPosition());

        car.move(3);
        assertEquals(1, car.getPosition()); // 이동하지 않음
    }

    // Cars 객체가 이름 목록으로부터 올바르게 생성되는지 검증
    @Test
    void testCarsWithDefaultStrategy() {
        Cars cars = Cars.fromNames("pobi,crong");

        assertEquals(2, cars.size());

        List<Car> carList = cars.getCars();
        assertEquals("pobi", carList.get(0).getName());
        assertEquals("crong", carList.get(1).getName());
    }
}
