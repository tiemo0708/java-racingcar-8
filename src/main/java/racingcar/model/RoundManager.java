package racingcar.model;

import java.util.List;

public class RoundManager {
    private final RacingResults racingResults;

    public RoundManager() {
        this.racingResults = new RacingResults();
    }

    public void recordRoundResult(List<String> currentRoundResults) {
        RoundResults roundResults = new RoundResults(currentRoundResults);
        racingResults.recordRoundResult(roundResults);
    }

    public List<List<String>> getAllRoundResults() {
        return racingResults.getAllRoundResults();
    }

    public List<String> getLatestRoundResults() {
        return racingResults.getLatestRoundResults().getResults();
    }
}