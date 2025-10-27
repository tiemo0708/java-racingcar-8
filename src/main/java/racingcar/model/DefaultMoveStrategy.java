package racingcar.model;

public class DefaultMoveStrategy implements MoveStrategy {
    private static final int MOVE_THRESHOLD = 4;

    @Override
    public boolean shouldMove(int randomValue) {
        return randomValue >= MOVE_THRESHOLD;
    }
}