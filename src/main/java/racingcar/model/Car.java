package racingcar.model;

import racingcar.error.ErrorMessage;

public class Car {
    private static final int INITIAL_POSITION = 0;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private final MoveStrategy moveStrategy;
    private int position;

    public Car(String name) {
        this(name, new DefaultMoveStrategy());
    }

    public Car(String name, MoveStrategy moveStrategy) {
        validateName(name);
        this.name = name.trim();
        this.moveStrategy = moveStrategy;
        this.position = INITIAL_POSITION;
    }

    private void validateName(String name) {
        String trimmed = name.trim();
        if (trimmed.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_TOO_LONG);
        }
    }

    public void move(int randomValue) {
        if (moveStrategy.shouldMove(randomValue)) {
            position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}


