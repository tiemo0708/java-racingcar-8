package racingcar.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CarsTest {

    // 유효한 자동차 이름 목록으로부터 Cars 객체 생성 테스트
    @Test
    void testValidCarNames() {
        assertDoesNotThrow(() -> Cars.fromNames(List.of("pobi", "woni", "jun")));
    }

    // 중복된 자동차 이름이 포함된 경우 예외 발생 테스트
    @Test
    void testDuplicateCarNames() {
        assertThrows(IllegalArgumentException.class, () -> Cars.fromNames(List.of("pobi", "woni", "pobi")));
        assertThrows(IllegalArgumentException.class, () -> Cars.fromNames(List.of("pobi", "pobi", "pobi")));
    }

    // 자동차 이름이 너무 긴 경우 예외 발생 테스트
    @Test
    void testCarNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> Cars.fromNames(List.of("pobi", "verylongname", "jun")));
    }
}