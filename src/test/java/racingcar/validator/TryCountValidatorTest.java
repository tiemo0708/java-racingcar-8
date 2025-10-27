package racingcar.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.error.ErrorMessages;

import static org.junit.jupiter.api.Assertions.*;

class TryCountValidatorTest {

    private TryCountValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TryCountValidator();
    }

    // null 또는 빈 문자열 입력 시 예외 발생
    @Test
    void validate_shouldThrowException_whenInputIsNullOrEmpty() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(null));
        assertEquals(ErrorMessages.TRIALS_NOT_A_NUMBER, e1.getMessage());

        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(""));
        assertEquals(ErrorMessages.TRIALS_NOT_A_NUMBER, e2.getMessage());

        IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class,
                () -> validator.validate("   "));
        assertEquals(ErrorMessages.TRIALS_NOT_A_NUMBER, e3.getMessage());
    }

    // 정상 입력(공백이 아닌 문자열)은 예외가 발생하지 않아야 함
    @Test
    void validate_shouldNotThrow_whenInputIsValidString() {
        assertDoesNotThrow(() -> validator.validate("5"));
        assertDoesNotThrow(() -> validator.validate(" 10 "));
    }

    // 시도 횟수가 0 이하일 경우 예외 발생
    @Test
    void validateNumberOfTrials_shouldThrowException_whenLessThanMin() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
                () -> validator.validateNumberOfTrials(0));
        assertEquals(ErrorMessages.INVALID_TRIALS_NUMBER, e1.getMessage());

        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
                () -> validator.validateNumberOfTrials(-3));
        assertEquals(ErrorMessages.INVALID_TRIALS_NUMBER, e2.getMessage());
    }

    // 시도 횟수가 1 이상이면 정상적으로 통과해야 함
    @Test
    void validateNumberOfTrials_shouldNotThrow_whenValidNumber() {
        assertDoesNotThrow(() -> validator.validateNumberOfTrials(1));
        assertDoesNotThrow(() -> validator.validateNumberOfTrials(5));
    }
}