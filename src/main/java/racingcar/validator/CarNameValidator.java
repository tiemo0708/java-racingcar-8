package racingcar.validator;

import racingcar.error.ErrorMessages;

public class CarNameValidator implements InputValidator {
    @Override
    public void validate(String carNames) {
        if (isNullOrEmpty(carNames)) {
            throw new IllegalArgumentException(ErrorMessages.CAR_NAME_NULL_OR_EMPTY);
        }
    }

    private boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }
}