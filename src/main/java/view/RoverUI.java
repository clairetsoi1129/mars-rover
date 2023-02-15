package view;

import model.AVehicle;
import model.Rover;
import util.Message;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.Objects;

public class RoverUI {
    private AVehicle rover;
    private final String ROVER_IMG = "img/rover50x50-%s.png";
    private final int CELL_WIDTH = 50;
    private final int CELL_HEIGHT = 50;

    private JLabel roverLabel;
    private GUI gui;

    public RoverUI(AVehicle rover, GUI gui) {
        this.rover = rover;
        this.gui = gui;
        initUI();
    }

    private void initUI(){
        Point roverPos = gui.translateObjectPos(rover.getPosition());
        roverLabel = gui.createObject(1, roverPos.x, roverPos.y, String.format(ROVER_IMG, rover.getDirection()),
                Message.MENU_NAMES,
                Message.MENU_CMDS);
    }

    public void updateUI(){
        Point positionOnBoard = gui.translateObjectPos(rover.getPosition());
        String img = String.format(ROVER_IMG, rover.getDirection());
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(img)));
        roverLabel.setIcon(icon);
        roverLabel.setBounds(positionOnBoard.x, positionOnBoard.y, CELL_WIDTH, CELL_HEIGHT);
        int basketSize = ((Rover)rover).getBasket().size();
        String steps = rover.getStepsTaken();
        String msg = "";

        if (rover.getGame().isGameEnd())
            msg = MessageFormat.format("Congratulation! You have collected all the samples. Total samples collected is {0}. Your final position is {1}. Steps taken are {2}.",
                    basketSize, rover.getPosDir(), steps);
        else
            msg = MessageFormat.format("Your new positiion is {0}. You got {1} sample(s) now. Steps: {2}.",
                    rover.getPosDir(), basketSize, steps);

        gui.getMessageText().setText(gui.getMessageText().getText()+msg);
    }
}
