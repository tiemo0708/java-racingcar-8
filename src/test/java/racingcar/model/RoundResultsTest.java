package racingcar.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundResultsTest {

    // 생성 시 입력 리스트를 복사하여 외부 변경의 영향을 받지 않아야 한다.
    @Test
    void constructor_shouldDefensivelyCopy() {
        var source = new java.util.ArrayList<>(List.of("pobi : -"));
        RoundResults rr = new RoundResults(source);
        source.add("woni : -");
        assertEquals(1, rr.size());
    }

    // getResults는 새로운 리스트를 반환해야 한다(불변성 보장).
    @Test
    void getResults_shouldReturnDefensiveCopy() {
        RoundResults rr = new RoundResults(List.of("pobi : -"));
        var copy = rr.getResults();
        copy.add("woni : -");
        assertEquals(1, rr.size());
    }

    // 비어있는지 여부 확인
    @Test
    void isEmpty_shouldReflectState() {
        RoundResults rr1 = new RoundResults(List.of());
        RoundResults rr2 = new RoundResults(List.of("pobi : -"));
        assertTrue(rr1.isEmpty());
        assertFalse(rr2.isEmpty());
    }
}