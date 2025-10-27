package racingcar;

import racingcar.config.AppConfig;
import racingcar.controller.RacingCarController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();
        RacingCarController racingCarController = appConfig.racingCarController();
        racingCarController.run();
    }
}
