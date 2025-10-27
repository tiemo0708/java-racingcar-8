package racingcar.parser;

import racingcar.error.ErrorMessages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultInputParser implements InputParser {
    private static final String CAR_NAME_DELIMITER = ",";

    @Override
    public List<String> parseCarNames(String carNamesInput) {
        List<String> names = Arrays.stream(carNamesInput.split(CAR_NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());

        // 빈 이름 존재 시 예외 발생
        if (names.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(ErrorMessages.CAR_NAME_NULL_OR_EMPTY);
        }
        return names;
    }

    @Override
    public int parseNumberOfTrials(String numberOfTrialsInput) {
        try {
            return Integer.parseInt(numberOfTrialsInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.TRIALS_NOT_A_NUMBER);
        }
    }
}
