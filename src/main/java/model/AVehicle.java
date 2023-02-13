package model;

import util.Message;

import java.awt.*;
import java.text.MessageFormat;

public class AVehicle {
    protected Point position;
    protected Direction direction;
    protected char[] movements;
    protected AScene scene;

    public AVehicle(Instruction instruction, AScene scene){
        this.position = instruction.getInitialPosition();
        this.direction = instruction.getDirection();
        this.movements = instruction.getMovements();
        this.scene = scene;
    }

    public void move(int step){
        switch (direction) {
            case N -> position.translate(0, step);
            case E -> position.translate(step, 0);
            case S -> position.translate(0, -step);
            case W -> position.translate(-step, 0);
        }
        if (scene.reachBoundary(position)){
            // rollback the move
            switch (direction) {
                case N -> position.translate(0, -step);
                case E -> position.translate(-step, 0);
                case S -> position.translate(0, step);
                case W -> position.translate(step, 0);
            }
            throw new IllegalArgumentException(Message.ERR_MSG_FORBIDDEN_MOVE);
        }
    }

    public void turn(char dir){
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

    public void go(){
        for (char movement : movements) {
            go(movement);
        }
    }

    private void go(char movement){
        if (movement == Movement.L.asChar() || movement == Movement.R.asChar()) {
            turn(movement);
        } else if (movement == Movement.M.asChar()) {
            move(1);
        } else {
            throw new IllegalArgumentException(Message.ERR_MSG_WRONG_INSTRUCTION);
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public String getPosDir(){
        return MessageFormat.format("{0} {1} {2}",
                (int)position.getX(), (int)position.getY(), getDirection());
    }

    public Point getPosition() {
        return position;
    }

    public void setMovements(char[] movements) {
        this.movements = movements;
    }
}
