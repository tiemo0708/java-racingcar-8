package racingcar.model;

import java.util.ArrayList;
import java.util.List;

public class RoundResults {
    private final List<String> roundResults;

    public RoundResults(List<String> roundResults) {
        this.roundResults = new ArrayList<>(roundResults);
    }

    public List<String> getResults() {
        return new ArrayList<>(roundResults);
    }

    public int size() {
        return roundResults.size();
    }

    public boolean isEmpty() {
        return roundResults.isEmpty();
    }

    public String get(int index) {
        return roundResults.get(index);
    }
}