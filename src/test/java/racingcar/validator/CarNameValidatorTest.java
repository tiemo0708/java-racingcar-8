package racingcar.validator;

import org.junit.jupiter.api.Test;
import racingcar.error.ErrorMessages;

import static org.junit.jupiter.api.Assertions.*;

class CarNameValidatorTest {

    private final CarNameValidator validator = new CarNameValidator();

    // 유효한 자동차 이름 입력 시 예외가 발생하지 않아야 한다.
    @Test
    void validate_shouldNotThrow_forValidCarNames() {
        assertDoesNotThrow(() -> validator.validate("pobi,woni,jun"));
    }

    // null 입력 시 IllegalArgumentException이 발생해야 한다.
    @Test
    void validate_shouldThrow_forNullInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(null));
        assertEquals(ErrorMessages.CAR_NAME_NULL_OR_EMPTY, exception.getMessage());
    }

    // 빈 문자열 입력 시 IllegalArgumentException이 발생해야 한다.
    @Test
    void validate_shouldThrow_forEmptyInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(""));
        assertEquals(ErrorMessages.CAR_NAME_NULL_OR_EMPTY, exception.getMessage());
    }

    // 공백만 있는 입력 시 IllegalArgumentException이 발생해야 한다.
    @Test
    void validate_shouldThrow_forWhitespaceInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate("   "));
        assertEquals(ErrorMessages.CAR_NAME_NULL_OR_EMPTY, exception.getMessage());
    }
}