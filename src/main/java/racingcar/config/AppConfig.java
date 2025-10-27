package racingcar.config;

import racingcar.controller.RacingCarController;
import racingcar.generator.RandomNumberGenerator;
import racingcar.generator.RandomNumberGeneratorImpl;
import racingcar.parser.DefaultInputParser;
import racingcar.parser.InputParser;
import racingcar.service.RacingCarService;
import racingcar.service.RacingCarServiceImpl;
import racingcar.validator.CarNameValidator;
import racingcar.validator.InputValidator;
import racingcar.validator.TryCountValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {
    private AppConfig() {}

    private static class Holder {
        private static final AppConfig INSTANCE = new AppConfig();
    }

    public static AppConfig getInstance() {
        return Holder.INSTANCE;
    }

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorImpl();
    private final InputValidator carNameValidator = new CarNameValidator();
    private final InputValidator numberOfTrialsValidator = new TryCountValidator();
    private final InputParser inputParser = new DefaultInputParser();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RacingCarService racingCarService = new RacingCarServiceImpl(randomNumberGenerator);
    private final RacingCarController racingCarController = new RacingCarController(
            racingCarService,
            inputView,
            outputView,
            carNameValidator,
            numberOfTrialsValidator,
            inputParser
    );

    // 핵심 진입점만 퍼블릭으로 노출
    public RacingCarController racingCarController() {
        return racingCarController;
    }
}
