package racingcar.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.error.ErrorMessages;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultInputParserTest {

    private DefaultInputParser parser;

    @BeforeEach
    void setUp() {
        parser = new DefaultInputParser();
    }

    // 쉼표로 구분된 이름을 올바르게 파싱해야 한다 (trim 포함)
    @Test
    void parseCarNames_shouldSplitAndTrim() {
        List<String> names = parser.parseCarNames("  pobi , woni,  jun  ");
        assertEquals(List.of("pobi", "woni", "jun"), names);
    }

    // 빈 이름 토큰이 포함된 경우 예외를 던져야 한다 (예: "pobi,,jun")
    @Test
    void parseCarNames_shouldThrow_whenEmptyTokenExists() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> parser.parseCarNames("pobi, ,jun"));
        assertEquals(ErrorMessages.CAR_NAME_NULL_OR_EMPTY, e.getMessage());

        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
                () -> parser.parseCarNames("pobi,,jun"));
        assertEquals(ErrorMessages.CAR_NAME_NULL_OR_EMPTY, e2.getMessage());
    }

    // 숫자 문자열을 정수로 파싱해야 한다 (양끝 공백 허용)
    @Test
    void parseNumberOfTrials_shouldParseIntWithTrim() {
        assertEquals(5, parser.parseNumberOfTrials("5"));
        assertEquals(10, parser.parseNumberOfTrials(" 10 "));
    }

    // 숫자가 아닌 입력은 IllegalArgumentException을 던져야 한다
    @Test
    void parseNumberOfTrials_shouldThrow_whenNotNumber() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> parser.parseNumberOfTrials("12a"));
        assertEquals(ErrorMessages.TRIALS_NOT_A_NUMBER, e.getMessage());
    }
}