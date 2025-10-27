package racingcar.domain;

import org.junit.jupiter.api.Test;
import racingcar.model.Car;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    // Car 이름이 유효할 때 예외가 발생하지 않는지 테스트
    void testValidCarName() {
        assertDoesNotThrow(() -> new Car("pobi"));
        assertDoesNotThrow(() -> new Car("woni"));
        assertDoesNotThrow(() -> new Car("jun"));
    }

    @Test
    // Car 이름이 5자를 초과할 때 예외가 발생하는지 테스트
    void testCarNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Car("verylongname"));
        assertThrows(IllegalArgumentException.class, () -> new Car("abcdef"));
    }

    @Test
    // Car 이름에 공백이 포함되어 있을 때 trim되어 정상적으로 생성되는지 테스트
    void testCarNameWithSpaces() {
        // 공백이 있는 경우 trim되어 정상 생성
        Car car = new Car("  pobi  ");
        assertEquals("pobi", car.getName());
    }

    @Test
    // Car가 이동 조건(4 이상)에 따라 position이 올바르게 변경되는지 테스트
    void testCarMovement() {
        Car car = new Car("pobi");
        assertEquals(0, car.getPosition());

        car.move(4); // 4 이상이면 이동
        assertEquals(1, car.getPosition());

        car.move(3); // 4 미만이면 이동하지 않음
        assertEquals(1, car.getPosition());
    }
}
