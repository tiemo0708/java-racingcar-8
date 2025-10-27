package racingcar.service;



import racingcar.generator.RandomNumberGenerator;
import racingcar.model.RacingGame;

import java.util.List;

public class RacingCarServiceImpl implements RacingCarService {
    private final RacingGame racingGame;

    public RacingCarServiceImpl(RandomNumberGenerator randomNumberGenerator) {
        this.racingGame = new RacingGame(randomNumberGenerator);
    }

    @Override
    public void startRace(List<String> carNames, int tryCount) {
        racingGame.initialize(carNames, tryCount);
        while (racingGame.hasNextRound()) {
            racingGame.playRound();
        }
    }

    @Override
    public RacingGame getRacingGame() {
        return racingGame;
    }
}