package racingcar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingResults {
    private final List<RacingResult> racingResults;

    public RacingResults() {
        this.racingResults = new ArrayList<>();
    }

    public RacingResults(List<RacingResult> racingResults) {
        this.racingResults = new ArrayList<>(racingResults);
    }

    public void recordRoundResult(RoundResults roundResults) {
        if (!roundResults.isEmpty()) {
            racingResults.add(new RacingResult(roundResults));
        }
    }

    public List<List<String>> getAllRoundResults() {
        return racingResults.stream()
                .map(RacingResult::getRoundResults)
                .map(RoundResults::getResults)
                .collect(Collectors.toList());
    }

    public RoundResults getLatestRoundResults() {
        if (racingResults.isEmpty()) {
            return new RoundResults(new ArrayList<>());
        }
        return racingResults.getLast().getRoundResults();
    }

    public int size() {
        return racingResults.size();
    }

    public boolean isEmpty() {
        return racingResults.isEmpty();
    }
}
