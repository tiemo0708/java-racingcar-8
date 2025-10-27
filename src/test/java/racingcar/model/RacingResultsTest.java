package racingcar.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RacingResultsTest {

    // 빈 RoundResults는 기록되지 않아야 한다.
    @Test
    void recordRoundResult_shouldIgnoreEmpty() {
        RacingResults rs = new RacingResults();
        rs.recordRoundResult(new RoundResults(List.of()));
        assertEquals(0, rs.size());
    }

    // 기록된 라운드 결과가 전체 조회에 반영되어야 한다.
    @Test
    void getAllRoundResults_shouldReturnAll() {
        RacingResults rs = new RacingResults();
        rs.recordRoundResult(new RoundResults(List.of("woni : -")));
        rs.recordRoundResult(new RoundResults(List.of("woni : --", "pobi : -")));

        var all = rs.getAllRoundResults();
        assertEquals(2, all.size());
        assertEquals(List.of("woni : -"), all.get(0));
        assertEquals(List.of("woni : --", "pobi : -"), all.get(1));
    }

    // 최신 라운드 결과가 올바르게 반환되어야 한다(없으면 빈 RoundResults).
    @Test
    void getLatestRoundResults_shouldReturnLatestOrEmpty() {
        RacingResults rs = new RacingResults();
        assertTrue(rs.getLatestRoundResults().isEmpty());

        rs.recordRoundResult(new RoundResults(List.of("woni : -")));
        assertFalse(rs.getLatestRoundResults().isEmpty());
        assertEquals("woni : -", rs.getLatestRoundResults().get(0));
    }
}