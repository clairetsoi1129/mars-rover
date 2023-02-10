import java.awt.*;
import java.text.MessageFormat;

public class AVehicle {
    protected Point position;
    protected Direction direction;

    protected char[] movements;

    public AVehicle(Instruction instruction){
        this.position = instruction.getInitialPosition();
        this.direction = instruction.getDirection();
        this.movements = instruction.getMovements();
    }

    protected void move(int step){
        switch (direction) {
            case N -> position.translate(0, step);
            case E -> position.translate(step, 0);
            case S -> position.translate(0, -step);
            case W -> position.translate(-step, 0);
        }
    }

    protected void turn(char dir){
        switch (direction){
            case N:
                if (dir == Movement.L.asChar()) {
                    direction = Direction.W;
                }else{
                    direction = Direction.E;
                }
                break;
            case E:
                if (dir == Movement.L.asChar()) {
                    direction = Direction.N;
                }else{
                    direction = Direction.S;
                }
                break;
            case S:
                if (dir == Movement.L.asChar()) {
                    direction = Direction.E;
                }else{
                    direction = Direction.W;
                }
                break;
            case W:
                if (dir == Movement.L.asChar()) {
                    direction = Direction.S;
                }else{
                    direction = Direction.N;
                }
                break;
        }
    }

    protected void start(){
        for (char movement : movements) {
            if (movement == Movement.L.asChar() || movement == Movement.R.asChar()) {
                turn(movement);
            } else if (movement == Movement.M.asChar()) {
                move(1);
            } else {
                throw new IllegalArgumentException("Instruction is wrong");
            }
        }
    }

    protected Direction getDirection() {
        return direction;
    }

    protected String getPosDir(){
        return MessageFormat.format("{0} {1} {2}",
                (int)position.getX(), (int)position.getY(), getDirection());
    }
}
