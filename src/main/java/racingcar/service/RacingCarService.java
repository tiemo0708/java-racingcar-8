package racingcar.service;

import racingcar.model.RacingGame;

import java.util.List;

public interface RacingCarService {
    void startRace(List<String> carNames, int tryCount);
    RacingGame getRacingGame();
}
