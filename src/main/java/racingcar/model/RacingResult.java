package racingcar.model;

public class RacingResult {
    private final RoundResults roundResults;

    public RacingResult(RoundResults roundResults) {
        this.roundResults = roundResults;
    }

    public RoundResults getRoundResults() {
        return roundResults;
    }
}