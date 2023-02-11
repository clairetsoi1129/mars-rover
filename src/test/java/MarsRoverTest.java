import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverTest {
    @Test
    public void testNormalMovementOfRoverWithFileInput() {
        AInput input = new FileInput("input-onerover.txt");
        Game game = new Game(input);
        game.start();
        assertEquals("1 3 N", game.getRovers().get(0).getPosDir());
        assertEquals("5 1 E", game.getRovers().get(1).getPosDir());
    }

    @Test
    public void testHitBoundaryWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-hitboundary.txt");
            Game game = new Game(input);
            game.start();
        });

        String expectedMessage = "Forbidden move. You will hit the boundary.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNormalMovementOfRoverWithKeyboardInput() {
        String userInput = String.format("5 5%s1 2 N%sLMLMLMLMM%s",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        InputStream sysInBackup = System.in;
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        AInput input = new KeyboardInput();
        Game game = new Game(input);
        game.start();
        assertEquals("1 3 N", game.getRovers().get(0).getPosDir());
        System.setIn(sysInBackup);
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