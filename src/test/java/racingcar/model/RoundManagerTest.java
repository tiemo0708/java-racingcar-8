package racingcar.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundManagerTest {

    // recordRoundResult로 전달한 현재 라운드 결과가 기록되어야 한다.
    @Test
    void recordRoundResult_shouldAppend() {
        RoundManager rm = new RoundManager();
        rm.recordRoundResult(List.of("woni : -"));
        rm.recordRoundResult(List.of("woni : --", "pobi : -"));

        var all = rm.getAllRoundResults();
        assertEquals(2, all.size());
        assertEquals(List.of("woni : -"), all.get(0));
        assertEquals(List.of("woni : --", "pobi : -"), all.get(1));
    }

    // 최신 라운드 결과 조회가 정상 동작해야 한다.
    @Test
    void getLatestRoundResults_shouldReturnLatest() {
        RoundManager rm = new RoundManager();
        rm.recordRoundResult(List.of("woni : -"));
        rm.recordRoundResult(List.of("woni : --", "pobi : -"));

        var latest = rm.getLatestRoundResults();
        assertEquals(List.of("woni : --", "pobi : -"), latest);
    }
}