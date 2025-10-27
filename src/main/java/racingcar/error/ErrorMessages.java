package racingcar.error;

public class ErrorMessages {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static final String CAR_NAME_NULL_OR_EMPTY = ERROR_PREFIX + "자동차 이름은 비어 있을 수 없습니다.";
    public static final String CAR_NAME_TOO_LONG = ERROR_PREFIX + "자동차 이름은 5자를 넘을 수 없습니다: ";
    public static final String INVALID_TRIALS_NUMBER = ERROR_PREFIX + "시도 횟수는 0보다 커야 합니다.";
    public static final String TRIALS_NOT_A_NUMBER = ERROR_PREFIX + "시도 횟수는 유효한 정수여야 합니다.";
    public static final String CAR_NAME_DUPLICATE = ERROR_PREFIX + "자동차 이름은 중복 될 수 없습니다";

    private void ErrorMessages() {

    }
}
