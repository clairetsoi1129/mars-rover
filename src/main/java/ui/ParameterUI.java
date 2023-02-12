package ui;

import input.GUI;
import model.Direction;
import model.Instruction;
import util.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParameterUI {
    private GUI gui;
    private JFormattedTextField widthText;
    private JFormattedTextField heightText;
    private JFormattedTextField roverXText;
    private JFormattedTextField roverYText;
    private JFormattedTextField roverDirText;

    private final int BTN_CONT_WIDTH = 50;
    private final int BTN_CONT_HEIGHT = 50;

    private final String ARROW_IMG = "img/arrow50x50.png";

    public ParameterUI(GUI gui) {
        this.gui = gui;
        initUI();
    }

    private void initUI(){
        initPlateauSizeUI();
        initRoverPosUI();
        initContinueButton();
    }

    private void initPlateauSizeUI(){
        int labelWidth = 200;
        int labelHeight = 50;
        int labelX = 200;
        int labelY = 100;
        int textFieldWidth = 50;
        int textFieldX = labelX + labelWidth;
        int textFieldY = labelY;
        JPanel panel = gui.getBgPanel()[0];

        JLabel plateauSizeLabel = new JLabel();
        plateauSizeLabel.setText(Message.MSG_ASK_PLATEAU_SIZE);
        plateauSizeLabel.setForeground(Color.LIGHT_GRAY);
        plateauSizeLabel.setBounds(labelX, labelY, labelWidth, labelHeight);

        widthText = new JFormattedTextField();
        heightText = new JFormattedTextField();
        widthText.setBounds(textFieldX, textFieldY, textFieldWidth, labelHeight);
        heightText.setBounds(textFieldX + 50, textFieldY, textFieldWidth, labelHeight);

        panel.add(plateauSizeLabel);
        panel.add(widthText);
        panel.add(heightText);
    }

    private void initRoverPosUI(){
        int labelWidth = 200;
        int labelHeight = 50;
        int labelX = 200;
        int labelY = 200;
        int textFieldWidth = 50;
        int textFieldX = 400;
        JPanel panel = gui.getBgPanel()[0];

        JLabel roverPosLabel = new JLabel("model.Rover position (x, y):");
        roverPosLabel.setBounds(labelX, labelY, labelWidth, labelHeight);

        JLabel roverDirLabel = new JLabel("model.Rover direction (N, E, S, W):");
        roverDirLabel.setBounds(labelX, labelY + 50, labelWidth, labelHeight);

        roverXText = new JFormattedTextField();
        roverYText = new JFormattedTextField();
        roverDirText = new JFormattedTextField();
        roverXText.setBounds(textFieldX, labelY, textFieldWidth, labelHeight);
        roverYText.setBounds(textFieldX + 50, labelY, textFieldWidth, labelHeight);
        roverDirText.setBounds(textFieldX, labelY + 50, textFieldWidth, labelHeight);

        panel.add(roverPosLabel);
        panel.add(roverDirLabel);
        panel.add(roverXText);
        panel.add(roverYText);
        panel.add(roverDirText);
    }

    private void initContinueButton(){
        initButton("goScene1");
    }

    private void initButton(String cmd){
        JPanel panel = gui.getBgPanel()[0];

        ImageIcon arrowIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(ARROW_IMG)));
        JButton arrowButton = new JButton();
        arrowButton.setBounds(400, 400, BTN_CONT_WIDTH, BTN_CONT_HEIGHT);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setIcon(arrowIcon);
        arrowButton.addActionListener(ev -> {
            switch (cmd) {
                case "goScene1" -> {
                    gui.setSceneDimension(getPlateauSize());
                    List<Instruction> instructionList = new ArrayList<>();
                    instructionList.add(new Instruction(getRoverInitialPos(), getRoverInitialDirection()));
                    gui.setInstructionList(instructionList);
                    gui.initGameBoardComponents();
                    gui.getGm().getScreenChanger().showScreen1();
                }
                case "goScene0" -> gui.getGm().getScreenChanger().showScreen0();
                default -> {
                }
            }
        });
        arrowButton.setActionCommand("goScene1");
        arrowButton.setBorderPainted(false);
        panel.add(arrowButton);
    }

    public Dimension getPlateauSize(){
        return new Dimension(Integer.parseInt(widthText.getText()),
                Integer.parseInt(heightText.getText()));
    }

    public Point getRoverInitialPos(){
        return new Point(Integer.parseInt(roverXText.getText()), Integer.parseInt(roverYText.getText()));
    }

    public Direction getRoverInitialDirection(){
        return Direction.valueOf(roverDirText.getText().trim().toUpperCase());
    }
}
