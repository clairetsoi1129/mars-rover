package model;

import ui.Game;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plateau extends AScene {
    private List<Sample> samples;
    private List<Obstacle> obstacles;
    private int noOfSample;
    private int noOfObstacle;

    public Plateau(Dimension dimension, Game game){
        super(dimension, game);

        noOfSample = dimension.width*dimension.height/10;
        noOfObstacle = dimension.width*dimension.height/10;
        game.getRandom().generateLocationAvoidConflict(noOfSample + noOfObstacle);
        generateSample();
        generateObstacle();
    }

    private void generateSample(){
        samples = new ArrayList<>();

        for (int i=0; i < noOfSample; i++){
            Sample sample = new Sample(game.getRandom().getGeneratedLocation(i));
            samples.add(sample);
        }
    }

    private void generateObstacle(){
        obstacles = new ArrayList<>();

        for (int i=0; i < noOfObstacle; i++){
            Obstacle obstacle = new Obstacle(game.getRandom().getGeneratedLocation(i+noOfSample));
            obstacles.add(obstacle);
        }
    }

    public boolean hasObstacle(Point roverPosition){
        boolean hasObstacle = false;
        for (Obstacle obstacle : obstacles) {
            if (roverPosition.equals(obstacle.getLocation())) {
                hasObstacle = true;
                break;
            }
        }
        return hasObstacle;
    }

    public Sample digSample(Point location){
        Sample tmpSample = null;

        for (Sample sample : samples) {
            if (sample.getLocation().equals(location)) {
                tmpSample = sample;
                tmpSample.setDigged(true);
            }
        }
        return tmpSample;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
