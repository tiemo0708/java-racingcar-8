package racingcar.model;

import racingcar.generator.RandomNumberGenerator;

import java.util.List;

public class CarManager {
    private final RandomNumberGenerator randomNumberGenerator;
    private Cars cars;

    public CarManager(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void initializeCars(List<String> carNames) {
        this.cars = Cars.fromNames(carNames);
    }

    public void playRound() {
        cars.playRound(randomNumberGenerator);
    }

    public List<String> getCurrentRoundResults() {
        return cars.getCurrentRoundResults();
    }

    public List<String> getWinners() {
        return cars.getWinners();
    }
}