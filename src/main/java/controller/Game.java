package controller;

import model.*;
import util.RandomLocation;
import util.RandomLocationImpl;

import java.awt.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<AVehicle> rovers;
    private AScene plateau;

    private RandomLocation random;

    private boolean isGameEnd;

    public Game(AInput input) {
        isGameEnd = false;
        List<Instruction> vehicleInstruction = input.getVehicleInstruction();
        rovers = new ArrayList<>();
        for (Instruction instruction: vehicleInstruction){
            rovers.add(new Rover(instruction, this));
        }

        List<Point> occupiedPos = new ArrayList<>();
        for (int i=0; i<this.getRovers().size(); i++) {
            occupiedPos.add(this.getRovers().get(i).getPosition());
        }
        random = new RandomLocationImpl(input.getSceneSize(), occupiedPos);

        plateau = new Plateau(input.getSceneSize(), this);
    }

    public Game(AInput input, RandomLocation random) {
        isGameEnd = false;
        List<Instruction> vehicleInstruction = input.getVehicleInstruction();

        rovers = new ArrayList<>();
        for (Instruction instruction: vehicleInstruction){
            rovers.add(new Rover(instruction, this));
        }
        this.random = random;
        plateau = new Plateau(input.getSceneSize(), this);
    }

    public void start(){
        for (AVehicle rover: rovers){
            rover.go();
        }
    }

    public List<AVehicle> getRovers() {
        return rovers;
    }

    public AScene getPlateau() {
        return plateau;
    }

    public static void main(String[] args){
        AInput input = new KeyboardInput();
        Game game = new Game(input);
        game.start();
        System.out.println(MessageFormat.format("Final Destination:{0}. You have collected {1} samples.",
                game.getRovers().get(0).getPosDir(), ((Rover)game.getRovers().get(0)).getBasket().size()));
    }

    public RandomLocation getRandom() {
        return random;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public void endGame(){
        isGameEnd = true;
    }
}
