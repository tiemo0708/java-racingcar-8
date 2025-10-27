package racingcar.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RacingGameTest {

    // initialize로 이름/횟수를 설정하고 hasNextRound가 라운드 수만큼 true→false를 반환해야 한다.
    @Test
    void initialize_and_hasNextRound_shouldWork() {
        RacingGame game = new RacingGame(new SeqRng(4, 4, 4));
        game.initialize(List.of("pobi", "woni"), 2);

        assertTrue(game.hasNextRound());
        game.playRound();
        assertTrue(game.hasNextRound());
        game.playRound();
        assertFalse(game.hasNextRound());
    }

    // 각 라운드 진행 후 결과가 RoundManager에 기록되어 getAllRoundResults로 조회되어야 한다.
    @Test
    void playRound_shouldRecordResultsAndExposeAll() {
        RacingGame game = new RacingGame(new SeqRng(4, 3, 9, 0));
        game.initialize(List.of("pobi", "woni"), 2);

        game.playRound(); // pobi:move, woni:stop
        game.playRound(); // pobi:move, woni:stop

        var all = game.getAllRoundResults();
        assertEquals(2, all.size());
        assertEquals(List.of("pobi : -", "woni : "), all.get(0));
        assertEquals(List.of("pobi : --", "woni : "), all.get(1));
    }

    // 최종 우승자는 CarManager 위임으로 계산되어야 한다.
    @Test
    void getWinners_shouldReturnWinners() {
        RacingGame game = new RacingGame(new SeqRng(4, 3)); // 한 라운드만으로도 a가 우승
        game.initialize(List.of("pobi", "woni"), 1);
        game.playRound();
        var winners = game.getWinners();
        assertEquals(List.of("pobi"), winners);
    }

    // 테스트용 RNG
    static class SeqRng implements racingcar.generator.RandomNumberGenerator {
        private final int[] seq; private int i=0;
        SeqRng(int... seq){this.seq=seq;}
        @Override public int generateNumber(int min, int max){
            if (seq.length==0) return min;
            int v = seq[Math.min(i, seq.length-1)]; i++; return v;
        }
    }
}