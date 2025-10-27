# java-racingcar-precourse

### 기능 구현 목록

- [x]  경주할 자동차들의 이름과 시도할 횟수를 입력받아 저장한다.
- [x]  회차별로 자동차를 전진시킨 후 모든 회차가 끝난 후 우승한 자동차를 선정한다
    - [x]  회차별로 각 자동차 별로 0~9사이의 무작위 값을 생성한다.
        - [x]  무작위 값 생성 시 테스트를 위해 난수 생성기를 주입할 수 있도록 설계한다.
    - [x]  자동차 이동 여부 판정:
        - [x]  생성된 값이 4 이상이면 1칸 이동
        - [x]  생성된 값이 4 미만이면 이동 X
    - [x]  회차별로 이동한 횟수만큼 "-"를 통해 이동 상태를 표시한다.
    - [x]  모든 회차가 끝난 후 가장 많이 이동한 자동차를 우승자로 선정한다.
        - [x]  우승자가 여러 명일 경우 쉼표(,)를 이용하여 구분

### 예외처리

`IllegalArgumentException` 이후 프로그램이 종료되도록 한다.

- [x]  **자동차 이름 입력 예외처리**
    - [x]  자동차 이름 길이가 5자를 넘는 경우
    - [x]  빈 이름이 포함된 경우의 자동차 이름
    - [x]  같은 이름이 있는 경우 중복 처리
- [x]  **시도할 횟수 예외처리**
    - [x]  숫자가 아닌 경우에 대한 처리
    - [x]  음수 또는 0일 경우에 대한 처리
    - [x] 빈 입력에 대한 처리 

### 패키지 구조
```text
src/
├─ main/
│  └─ java/
│     └─ racingcar/
│        ├─ Application.java
│        │
│        ├─ config/
│        │  └─ AppConfig.java
│        │
│        ├─ controller/
│        │  └─ RacingCarController.java
│        │
│        ├─ error/
│        │  └─ ErrorMessages.java
│        │
│        ├─ generator/
│        │  ├─ RandomNumberGenerator.java
│        │  └─ RandomNumberGeneratorImpl.java
│        │
│        ├─ model/
│        │  ├─ Car.java
│        │  ├─ Cars.java
│        │  ├─ CarManager.java
│        │  ├─ MoveStrategy.java
│        │  ├─ DefaultMoveStrategy.java
│        │  ├─ RoundResults.java
│        │  ├─ RacingResult.java
│        │  ├─ RacingResults.java
│        │  ├─ RoundManager.java
│        │  └─ RacingGame.java
│        │
│        ├─ parser/
│        │  ├─ InputParser.java
│        │  └─ DefaultInputParser.java
│        │
│        ├─ service/
│        │  ├─ RacingCarService.java
│        │  └─ RacingCarServiceImpl.java
│        │
│        ├─ validator/
│        │  ├─ InputValidator.java
│        │  ├─ CarNameValidator.java
│        │  └─ TryCountValidator.java
│        │
│        └─ view/
│           ├─ InputView.java
│           └─ OutputView.java
│
└─ test/
└─ java/
└─ racingcar/
├─ ApplicationTest.java
│
├─ model/
│  ├─ CarTest.java
│  ├─ CarsTest.java
│  ├─ MoveStrategyTest.java
│  ├─ CarManagerTest.java
│  ├─ RacingGameTest.java
│  ├─ RacingResultTest.java
│  ├─ RacingResultsTest.java
│  ├─ RoundResultsTest.java
│  └─ RoundManagerTest.java
│
├─ parser/
│  └─ DefaultInputParserTest.java
│
└─ validator/
├─ CarNameValidatorTest.java
└─ TryCountValidatorTest.java
```
- **핵심 도메인**
    - Car : 개별 상태, 이동
    - MoveStrategy/DefaultMoveStrategy : 이동 조건 결정(≥4)
    - Cars : 일급 컬렉션(중복 검증, 라운드 이동, 우승자/출력 문자열 생성)
    - CarManager : Cars 제어(초기화/라운드 실행/조회)
    - RoundResults/RacingResult/RacingResults : 라운드 스냅샷/전체 이력
    - RoundManager : 결과 누적/조회
    - RacingGame : 게임 오케스트레이션(초기화, 라운드 반복, 결과/우승자 노출)
### 설계 의도
- DDD: Entity(Car), 일급 컬렉션(Cars, RoundResults, RacingResults),
  도메인 서비스(CarManager, RoundManager), 오케스트레이터(RacingGame)
- Strategy: MoveStrategy로 이동 규칙 캡슐화(테스트/확장 용이)
- DI: AppConfig에서 수동 조립(생성자 주입)
- SRP: Validator(존재성/형식) ↔ Parser(형식 변환) ↔ Domain(규칙)의 역할 구분

### Domain Layer Structure
RacingCar 게임의 핵심 비즈니스 로직은 racingcar.model 패키지에 위치하며,
DDD(Domain-Driven Design) 원칙과 전략 패턴(Strategy Pattern), 일급 컬렉션(First-Class Collection) 개념을 적용했습니다.

| **설계 요소** | **적용 내용** |
| --- | --- |
| **DDD 분리** | Entity(Car), Value Object(RoundResults), Service(CarManager·RoundManager), Aggregate(RacingGame) 명확히 분리 |
| **전략 패턴 (Strategy Pattern)** | MoveStrategy 인터페이스를 통해 이동 규칙을 유연하게 교체 가능 |
| **일급 컬렉션 (First-Class Collection)** | Cars, RoundResults, RacingResults를 컬렉션 객체로 추상화하여 불변성 유지 |
| **단일 책임 원칙 (SRP)** | 각 클래스는 하나의 역할에 집중 (Car는 상태 관리, CarManager는 제어, RacingGame은 오케스트레이션) |
| **도메인 순수성 유지** | View·Controller·Service 레이어 의존 제거 (완전한 독립 계층 구조) |