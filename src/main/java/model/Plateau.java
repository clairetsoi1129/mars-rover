package model;

import controller.Game;
import java.awt.*;
import java.text.MessageFormat;
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
            System.out.println(MessageFormat.format("Sample[{0}]:[{1},{2}]",
                    i, sample.getLocation().x, sample.getLocation().y));
        }
    }

    private void generateObstacle(){
        obstacles = new ArrayList<>();

        for (int i=0; i < noOfObstacle; i++){
            Obstacle obstacle = new Obstacle(game.getRandom().getGeneratedLocation(i+noOfSample));
            obstacles.add(obstacle);
            System.out.println(MessageFormat.format("Obstacle[{0}]:[{1},{2}]",
                    i, obstacle.getLocation().x, obstacle.getLocation().y));
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
            if (sample.getLocation().equals(location) && !sample.isDigged()) {
                tmpSample = sample;
                tmpSample.setDigged(true);
            }
        }
        if (allSamplesDigged())
            game.endGame();
        return tmpSample;
    }

    public boolean allSamplesDigged(){
        boolean allSamplesDigged = true;
        for (Sample sample : samples) {
            if (!sample.isDigged()) {
                allSamplesDigged = false;
                break;
            }
        }
        return allSamplesDigged;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
