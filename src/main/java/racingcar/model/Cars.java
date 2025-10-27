package racingcar.model;

import racingcar.error.ErrorMessage;
import racingcar.generator.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {
    private static final int MIN_RANDOM_VALUE = 0;
    private static final int MAX_RANDOM_VALUE = 9;

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public static Cars fromNames(String carNames) {
        return fromNames(carNames, new DefaultMoveStrategy());
    }

    public static Cars fromNames(String carNames, MoveStrategy moveStrategy) {
        String[] names = carNames.split(",");
        validateUniqueNames(names);

        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name.trim(), moveStrategy));
        }
        return new Cars(cars);
    }

    private static void validateUniqueNames(String[] names) {
        Set<String> uniqueNames = new HashSet<>();
        for (String name : names) {
            String trimmedName = name.trim();
            if (!uniqueNames.add(trimmedName)) {
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_DUPLICATE);
            }
        }
    }

    public void playRound(RandomNumberGenerator randomNumberGenerator) {
        for (Car car : cars) {
            int randomValue = randomNumberGenerator.generateNumber(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
            car.move(randomValue);
        }
    }

    public List<String> getCurrentRoundResults() {
        return cars.stream()
                .map(car -> car.getName() + " : " + "-".repeat(car.getPosition()))
                .collect(Collectors.toList());
    }

    public List<String> getWinners() {
        int maxPosition = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public int size() {
        return cars.size();
    }

    public boolean isEmpty() {
        return cars.isEmpty();
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }
}
