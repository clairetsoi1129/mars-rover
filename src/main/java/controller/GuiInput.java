package controller;

import model.Direction;
import model.Instruction;
import util.Message;
import view.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GuiInput extends AInput{
    private GUI gui;
    private JFormattedTextField widthText;
    private JFormattedTextField heightText;
    private JFormattedTextField roverXText;
    private JFormattedTextField roverYText;
    private JFormattedTextField roverDirText;

    private final int BTN_CONT_WIDTH = 50;
    private final int BTN_CONT_HEIGHT = 50;

    private final String ARROW_IMG = "img/arrow50x50.png";

    private final String CMD_GO_SCENES_1 = "goScene1";
    private final String CMD_GO_SCENES_0 = "goScene0";


    public GuiInput(GUI gui) {
        this.gui = gui;
        initUI();
    }

    private void initUI(){
        initPlateauSizeUI();
        initRoverPosUI();
        initContinueButton();
    }

    private void initPlateauSizeUI(){
        int labelWidth = 300;
        int labelHeight = 50;
        int labelX = 100;
        int labelY = 100;
        int textFieldWidth = 50;
        int textFieldX = labelX + labelWidth;
        int textFieldY = labelY;
        JPanel panel = gui.getBgPanel()[0];

        JLabel plateauSizeLabel = new JLabel();
        plateauSizeLabel.setText(Message.MSG_GET_PLATEAU_SIZE_GUI);
        plateauSizeLabel.setForeground(Color.WHITE);
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
        int labelWidth = 300;
        int labelHeight = 50;
        int labelX = 100;
        int labelY = 200;
        int textFieldWidth = 50;
        int textFieldX = 400;
        int textFieldY = labelY;
        JPanel panel = gui.getBgPanel()[0];

        JLabel roverPosLabel = new JLabel(Message.MSG_INIT_POS_X_Y_GUI);
        roverPosLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        roverPosLabel.setForeground(Color.WHITE);

        JLabel roverDirLabel = new JLabel(Message.MSG_INIT_DIR);
        roverDirLabel.setBounds(labelX, labelY + 50, labelWidth, labelHeight);
        roverDirLabel.setForeground(Color.WHITE);

        roverXText = new JFormattedTextField();
        roverYText = new JFormattedTextField();
        roverDirText = new JFormattedTextField();
        roverXText.setBounds(textFieldX, textFieldY, textFieldWidth, labelHeight);
        roverYText.setBounds(textFieldX + 50, textFieldY, textFieldWidth, labelHeight);
        roverDirText.setBounds(textFieldX, textFieldY+50, textFieldWidth, labelHeight);

        panel.add(roverPosLabel);
        panel.add(roverDirLabel);
        panel.add(roverXText);
        panel.add(roverYText);
        panel.add(roverDirText);
    }

    private void initContinueButton(){
        initButton(CMD_GO_SCENES_1);
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
                case CMD_GO_SCENES_1 -> {
                    parseSceneSize(null);
                    Dimension dim = sceneDimension;
                    Instruction instruction = parseInitialPosDirection(null);
                    Point pos = instruction.getInitialPosition();
                    Direction dir = instruction.getDirection();

                    if (dim != null && pos != null && dir != null) {
                        sceneDimension = dim;
                        instructionList = new ArrayList<>();
                        instructionList.add(new Instruction(pos, dir));
                        gui.initGameBoardComponents();
                        gui.getGm().getScreenChanger().showScreen1();
                    }
                }
                case CMD_GO_SCENES_0 -> gui.getGm().getScreenChanger().showScreen0();
                default -> {
                }
            }
        });
        arrowButton.setActionCommand(CMD_GO_SCENES_1);
        arrowButton.setBorderPainted(false);
        panel.add(arrowButton);
    }

    @Override
    public void parseSceneSize(Scanner scanner){
        try {
            int width = Integer.parseInt(widthText.getText());
            int height = Integer.parseInt(heightText.getText());
            if (width > 15 || height > 9 || width < 1 || height < 1)
                throw new IllegalArgumentException();
            sceneDimension = new Dimension(Integer.parseInt(widthText.getText()),
                    Integer.parseInt(heightText.getText()));
        }catch (NumberFormatException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_SIZE_GUI);
        }catch (IllegalArgumentException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_SIZE_GUI);
        }
    }

    public Point parseRoverInitialPos(){
        try {
            int x = Integer.parseInt(roverXText.getText());
            int y = Integer.parseInt(roverYText.getText());
            if (x > sceneDimension.getWidth() || y > sceneDimension.getHeight() || x < 0 || y < 0)
                throw new IllegalArgumentException();

            return new Point(x,y);
        }catch (NumberFormatException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_POS);
        }catch (IllegalArgumentException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_POS_OUTSIDE);
        }
        return null;
    }

    public Direction parseRoverInitialDirection(){
        try {
            return Direction.valueOf(roverDirText.getText().trim().toUpperCase());
        }catch (IllegalArgumentException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_DIR);
        }
        return null;
    }

    @Override
    public Instruction parseInitialPosDirection(Scanner scanner) {
        // scanner is useless in GUI
        Instruction instruction = new Instruction(parseRoverInitialPos(), parseRoverInitialDirection());
        instructionList.add(instruction);
        return instruction;
    }

    @Override
    public char[] parseMovement(Scanner scanner) {
        // this function is useless in GUI
        return new char[0];
    }
}
