import java.awt.*;

public class Instruction {
    private Point initialPosition;

    private Direction direction;
    private char[] movements;

    public Instruction(Point position, Direction direction) {
        this.initialPosition = position;
        this.direction = direction;
    }

    public void setMovements(char[] movements) {
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
