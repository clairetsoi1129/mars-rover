import java.awt.*;

public class Instruction {
    private Point initialPosition;

    private Direction direction;
    private char[] movements;

    public Instruction(Point position, Direction direction, char[] movements) {
        this.initialPosition = position;
        this.direction = direction;
        this.movements = movements;
    }

    public Point getInitialPosition() {
        return initialPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public char[] getMovements() {
        return movements;
    }
}
