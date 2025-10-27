package racingcar.controller;

import racingcar.model.RacingGame;
import racingcar.parser.InputParser;
import racingcar.service.RacingCarService;
import racingcar.validator.InputValidator;
import racingcar.validator.TryCountValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingCarController {
    private final RacingCarService racingCarService;
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator carNameValidator;
    private final InputValidator numberOfTrialsValidator;
    private final InputParser inputParser;

    public RacingCarController(RacingCarService racingCarService, InputView inputView, OutputView outputView, InputValidator carNameValidator, InputValidator numberOfTrialsValidator, InputParser inputParser) {
        this.racingCarService = racingCarService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.carNameValidator = carNameValidator;
        this.numberOfTrialsValidator = numberOfTrialsValidator;
        this.inputParser = inputParser;
    }

    public void run() {
        // 1. 입력 검증 및 파싱
        List<String> carNames = getValidatedCarNames();
        int tryCount = getValidatedTryCount();

        // 2. 게임 실행
        racingCarService.startRace(carNames, tryCount);

        // 3. 결과 출력
        outputView.printInitialStatus();
        RacingGame racingGame = racingCarService.getRacingGame();
        racingGame.getAllRoundResults().forEach(outputView::printRoundResults);
        outputView.printWinners(racingGame.getWinners());
    }

    private List<String> getValidatedCarNames() {
        outputView.printEnterCarNamesMessage();
        String carNamesInput = inputView.getCarNames();

        // 입력 검증 (null, empty 체크)
        carNameValidator.validate(carNamesInput);

        // 파싱만 수행 - 도메인 검증은 Cars.fromNames()에서 자동으로 수행됨
        return inputParser.parseCarNames(carNamesInput);
    }

    private int getValidatedTryCount() {
        outputView.printEnterTrialsCountMessage();
        String numberOfTrialsInput = inputView.getNumberOfTrials();

        // 입력 검증 (null, empty 체크)
        numberOfTrialsValidator.validate(numberOfTrialsInput);

        // 파싱
        int tryCount = inputParser.parseNumberOfTrials(numberOfTrialsInput);

        // 도메인 검증 (범위 체크)
        ((TryCountValidator) numberOfTrialsValidator).validateNumberOfTrials(tryCount);

        return tryCount;
    }
}
