import controller.AInput;
import controller.FileInput;
import controller.KeyboardInput;
import model.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import controller.Game;
import util.Message;
import util.RandomLocation;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class MarsRoverTest {
    @Mock
    RandomLocation random;

    @BeforeEach
    void init() {
        random = Mockito.mock(RandomLocation.class);

        lenient().when(random.getGeneratedLocation(0)).thenReturn(new Point(1,1));
        lenient().when(random.getGeneratedLocation(1)).thenReturn(new Point(2,2));
        lenient().when(random.getGeneratedLocation(2)).thenReturn(new Point(3,2));
        lenient().when(random.getGeneratedLocation(3)).thenReturn(new Point(4,4));
    }

    @Test
    public void testNormalMovementOfRoverWithFileInput() {
        AInput input = new FileInput("input-normal.txt");
        Game game = new Game(input, random);
        game.start();
        assertEquals("1 3 N", game.getRovers().get(0).getPosDir());
        assertEquals(1, ((Rover)game.getRovers().get(0)).getBasket().size());
        assertEquals("5 1 E", game.getRovers().get(1).getPosDir());
        assertEquals(0, ((Rover)game.getRovers().get(1)).getBasket().size());
    }

    @Test
    public void testHitBoundaryWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-hitboundary.txt");
            Game game = new Game(input, random);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_FORBIDDEN_MOVE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testHitObstacleWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-obstacle.txt");
            Game game = new Game(input, random);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_HAS_OBSTACLE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDecimalSizeWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-decimal-size.txt");
            Game game = new Game(input, random);
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
            Game game = new Game(input, random);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_INVALID_SIZE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testInitPosOutsideWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-pos-outside.txt");
            Game game = new Game(input, random);
            game.start();
        });

        String expectedMessage = Message.ERR_MSG_INVALID_POS_OUTSIDE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDecimalLocationWithFileInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AInput input = new FileInput("input-decimal-location.txt");
            Game game = new Game(input, random);
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
            Game game = new Game(input, random);
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
            Game game = new Game(input, random);
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
        Game game = new Game(input, random);
        game.start();
        assertEquals("1 3 N", game.getRovers().get(0).getPosDir());

        assertEquals(1, ((Rover)game.getRovers().get(0)).getBasket().size());
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
            Game game = new Game(input, random);
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
            Game game = new Game(input, random);
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
            Game game = new Game(input, random);
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
            Game game = new Game(input, random);
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
            Game game = new Game(input, random);
            game.start();
            System.setIn(sysInBackup);
        });

        String expectedMessage = Message.ERR_MSG_WRONG_INSTRUCTION;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testInitPosOutsideWithKeyboardInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String userInput = String.format("5 5%s8 2%sN%sMLMLMLMM%s",
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator());
            InputStream sysInBackup = System.in;
            ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(bais);

            AInput input = new KeyboardInput();
            Game game = new Game(input, random);
            game.start();
            System.setIn(sysInBackup);
        });

        String expectedMessage = Message.ERR_MSG_INVALID_POS_OUTSIDE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testHitObstacleWithKeyboardInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String userInput = String.format("5 5%s1 2%sE%sMM%s",
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator(),
                    System.lineSeparator());
            InputStream sysInBackup = System.in;
            ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(bais);

            AInput input = new KeyboardInput();
            Game game = new Game(input, random);
            game.start();
            System.setIn(sysInBackup);
        });

        String expectedMessage = Message.ERR_MSG_HAS_OBSTACLE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}