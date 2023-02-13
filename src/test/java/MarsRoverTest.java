import input.AInput;
import input.FileInput;
import input.KeyboardInput;
import org.junit.jupiter.api.Test;
import ui.Game;
import util.Message;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverTest {
    @Test
    public void testNormalMovementOfRoverWithFileInput() {
        AInput input = new FileInput("input-normal.txt");
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

        String expectedMessage = Message.ERR_MSG_FORBIDDEN_MOVE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDecimalSizeWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-decimal-size.txt");
            Game game = new Game(input);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_INVALID_SIZE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testWrongNoOfArgumentWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-wrong-no.txt");
            Game game = new Game(input);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_INVALID_SIZE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDecimalLocationWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-decimal-location.txt");
            Game game = new Game(input);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_INVALID_POS_DIR;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testWrongDirectionWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-wrong-direction.txt");
            Game game = new Game(input);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_INVALID_POS_DIR;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testWrongInstructionWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-wrong-instruction.txt");
            Game game = new Game(input);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_WRONG_INSTRUCTION;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNormalMovementOfRoverWithKeyboardInput() {
        String userInput = String.format("5 5%s1 2%sN%sLMLMLMLMM%s",
                System.lineSeparator(),
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

    @Test
    public void testDecimalSizeWithKeyboardInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String userInput = String.format("10.5 5.5%s1 2%sN%sLMLMLMLMM%s",
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator());
            InputStream sysInBackup = System.in;
            ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(bais);

            AInput input = new KeyboardInput();
            Game game = new Game(input);
            game.start();
            System.setIn(sysInBackup);
        });

        String expectedMessage = Message.ERR_MSG_INVALID_SIZE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testHitBoundaryWithKeyboardInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String userInput = String.format("5 5%s0 0%sW%sM%s",
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator());
            InputStream sysInBackup = System.in;
            ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(bais);

            AInput input = new KeyboardInput();
            Game game = new Game(input);
            game.start();
            System.setIn(sysInBackup);
        });

        String expectedMessage = Message.ERR_MSG_FORBIDDEN_MOVE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDecimalLocationWithKeyboardInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String userInput = String.format("5 5%s1.3 2%sN%sLMLMLMLMM%s",
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator());
            InputStream sysInBackup = System.in;
            ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(bais);

            AInput input = new KeyboardInput();
            Game game = new Game(input);
            game.start();
            System.setIn(sysInBackup);
        });

        String expectedMessage = Message.ERR_MSG_INVALID_POS;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testWrongDirectionWithKeyboardInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String userInput = String.format("5 5%s1 2%sK%sLMLMLMLMM%s",
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator());
            InputStream sysInBackup = System.in;
            ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(bais);

            AInput input = new KeyboardInput();
            Game game = new Game(input);
            game.start();
            System.setIn(sysInBackup);
        });

        String expectedMessage = Message.ERR_MSG_INVALID_DIR;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testWrongInstructionWithKeyboardInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String userInput = String.format("5 5%s1 2%sN%sPMLMLMLMM%s",
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator());
            InputStream sysInBackup = System.in;
            ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(bais);

            AInput input = new KeyboardInput();
            Game game = new Game(input);
            game.start();
            System.setIn(sysInBackup);
        });

        String expectedMessage = Message.ERR_MSG_WRONG_INSTRUCTION;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}