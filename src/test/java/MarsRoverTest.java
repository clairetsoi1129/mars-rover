import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {
    @Test
    public void testMovementOfRover() {
        AInput input = new FileInput("input-onerover.txt");
        Game game = new Game(input);
        game.start();
        assertEquals("1 3 N", game.getRovers().get(0).getPosDir());
        assertEquals("5 1 E", game.getRovers().get(1).getPosDir());
    }

//    @ParameterizedTest
//    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
//    void testValidCase(
//            String input, int expected) {
//        BowlingGame bowling = new BowlingGame(input);
//        int actualValue = bowling.calculateFinalScore();
//        assertEquals(expected, actualValue);
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/invalidData.csv", numLinesToSkip = 1)
//    void testInvalidNumeralThrowException(
//            String input) {
//        Exception exception = assertThrows(IllegalArgumentException.class,
//                () -> {
//                    BowlingGame bowling = new BowlingGame(input);
//                    bowling.calculateFinalScore();
//                });
//
//        String expectedMessage = "Input is not valid.";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
}