package racingcar.validator;

import racingcar.error.ErrorMessages;

public class TryCountValidator implements InputValidator
{
    private static final int MIN_TRIALS_NUMBER = 1;

    @Override
    public void validate(String numberOfTrialsInput) {
        if (numberOfTrialsInput == null || numberOfTrialsInput.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.TRIALS_NOT_A_NUMBER);
        }
    }

    public void validateNumberOfTrials(int numberOfTrials) {
        if (numberOfTrials < MIN_TRIALS_NUMBER) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_TRIALS_NUMBER);
        }
    }
}
