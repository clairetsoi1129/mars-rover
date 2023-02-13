package ui;

import input.GUI;
import model.AVehicle;
import util.Message;

import javax.swing.*;
import java.awt.*;
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
        Point roverPos = gui.translateRoverPos(rover.getPosition());
        roverLabel = gui.createObject(1, roverPos.x, roverPos.y, String.format(ROVER_IMG, rover.getDirection()),
                Message.MENU_NAMES,
                Message.MENU_CMDS);
    }

    public void updateUI(){
        Point positionOnBoard = gui.translateRoverPos(rover.getPosition());
        String img = String.format(ROVER_IMG, rover.getDirection());
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(img)));
        roverLabel.setIcon(icon);
        roverLabel.setBounds(positionOnBoard.x, positionOnBoard.y, CELL_WIDTH, CELL_HEIGHT);
    }
}
