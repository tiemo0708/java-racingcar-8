package racingcar.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarManagerTest {

    // initializeCars로 Cars가 생성되어야 한다.
    @Test
    void initializeCars_shouldCreateCars() {
        CarManager cm = new CarManager(new SeqRng(4, 4));
        cm.initializeCars(List.of("pobi", "woni"));
        assertEquals(List.of("pobi : ", "woni : "), cm.getCurrentRoundResults()); // 아직 play 전
    }

    // playRound는 RNG에 따라 이동을 수행하고 결과 포맷이 맞아야 한다.
    @Test
    void playRound_shouldMoveAndFormatResults() {
        CarManager cm = new CarManager(new SeqRng(4, 3, 9));
        cm.initializeCars(List.of("pobi", "woni", "jun"));
        cm.playRound();
        var results = cm.getCurrentRoundResults();
        assertTrue(results.contains("pobi : -"));
        assertTrue(results.contains("woni : "));
        assertTrue(results.contains("jun : -"));
    }

    // getWinners는 Cars 위임으로 우승자를 반환해야 한다.
    @Test
    void getWinners_shouldReturnWinnersFromCars() {
        CarManager cm = new CarManager(new SeqRng(4, 3)); // a:move, b:stop
        cm.initializeCars(List.of("pobi", "woni"));
        cm.playRound();
        var winners = cm.getWinners();
        assertEquals(1, winners.size());
        assertEquals("pobi", winners.getFirst());
    }

    // 테스트용 RNG: 주어진 시퀀스를 차례로 반환, 고갈 시 마지막 값을 반복
    static class SeqRng implements racingcar.generator.RandomNumberGenerator {
        private final int[] seq; private int i=0;
        SeqRng(int... seq){this.seq=seq;}
        @Override public int generateNumber(int min, int max){
            if (seq.length==0) return min;
            int v = seq[Math.min(i, seq.length-1)]; i++; return v;
        }
    }
}