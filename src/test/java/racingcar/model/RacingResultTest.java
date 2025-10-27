package racingcar.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RacingResultTest {

    // 한 라운드 결과를 보관하고 getter로 동일 데이터가 반환되어야 한다.
    @Test
    void holdsRoundResults() {
        RoundResults rr = new RoundResults(List.of("pobi : -", "woni : "));
        RacingResult r = new RacingResult(rr);
        assertEquals(2, r.getRoundResults().size());
        assertEquals("pobi : -", r.getRoundResults().get(0));
    }
}