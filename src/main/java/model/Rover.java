package model;

import util.Message;

import java.util.ArrayList;
import java.util.List;

public class Rover extends AVehicle{
    private List<Sample> basket;
    public Rover(Instruction instruction, AScene scene){
        super(instruction, scene);
        basket = new ArrayList<>();
    }

    public void collect(Sample sample){
        basket.add(sample);
    }

    public void move(int step){
        switch (direction) {
            case N -> position.translate(0, step);
            case E -> position.translate(step, 0);
            case S -> position.translate(0, -step);
            case W -> position.translate(-step, 0);
        }
        if (scene.reachBoundary(position)){
            rollback(1);
            throw new IllegalArgumentException(Message.ERR_MSG_FORBIDDEN_MOVE);
        }
        if (((Plateau)scene).hasObstacle(position)){
            // rollback the move
            rollback(1);
            throw new IllegalArgumentException(Message.ERR_MSG_HAS_OBSTACLE);
        }
        Sample sample = ((Plateau)scene).digSample(position);
        if (sample != null){
            collect(sample);
        }
    }

    public List<Sample> getBasket() {
        return basket;
    }
}
