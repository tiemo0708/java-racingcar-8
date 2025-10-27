package racingcar.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class CarsTest {

    // 유효한 자동차 이름 목록으로부터 Cars 객체 생성 테스트
    @Test
    void testValidCarNames() {
        assertDoesNotThrow(() -> Cars.fromNames("pobi,woni,jun"));
    }

    // 중복된 자동차 이름이 포함된 경우 예외 발생 테스트
    @Test
    void testDuplicateCarNames() {
        assertThrows(IllegalArgumentException.class, () -> Cars.fromNames("pobi,woni,pobi"));
        assertThrows(IllegalArgumentException.class, () -> Cars.fromNames("pobi,pobi,pobi"));
    }

    // 자동차 이름이 너무 긴 경우 예외 발생 테스트
    @Test
    void testCarNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> Cars.fromNames("pobi,verylongname,jun"));
    }
}