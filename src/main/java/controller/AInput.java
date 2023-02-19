package controller;

import model.Instruction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AInput {
    protected Dimension sceneDimension;
    protected List<Instruction> instructionList;
    
    protected AInput(){
        instructionList = new ArrayList<>();
    }

    public Dimension getSceneSize(){
        return sceneDimension;
    }

    public abstract void parseSceneSize(Scanner scanner);

    public abstract Instruction parseInitialPosDirection(Scanner scanner);

    public abstract char[] parseMovement(Scanner scanner);

    public List<Instruction> getVehicleInstruction(){
        return instructionList;
    }
}
