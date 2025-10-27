package racingcar.parser;

import java.util.List;
public interface InputParser {
    List<String> parseCarNames(String carNamesInput);
    int parseNumberOfTrials(String numberOfTrialsInput);
}
