package controller;

import model.Direction;
import model.Instruction;
import util.Message;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput extends AInput {
    public FileInput(String filename) {
        super();
        try {
            File inputFile = new File(filename);
            Scanner scanner = new Scanner(inputFile);
            parseSceneSize(scanner);

            Instruction instruction;

            while (scanner.hasNextLine()) {
                instruction = parseInitialPosDirection(scanner);
                instruction.setMovements(parseMovement(scanner));
                instructionList.add(instruction);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found");
        }
    }

    public Instruction parseInitialPosDirection(Scanner scanner) {
        String initialPosAndDir = scanner.nextLine();
        String[] strArray = initialPosAndDir.split(" ");
        if (strArray.length != 3) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_POS_DIR);
        }

        Point initialPos;
        Direction direction;
        int x;
        int y;
        try {
            x = Integer.parseInt(strArray[0]);
            y = Integer.parseInt(strArray[1]);
            initialPos = new Point(x,y);
            direction = Direction.valueOf(strArray[2]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_POS_DIR);
        }

        if (x > sceneDimension.getWidth() || y > sceneDimension.getHeight() || x < 0 || y < 0)
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_POS_OUTSIDE);
        return new Instruction(initialPos, direction);
    }

    public char[] parseMovement(Scanner scanner) {
        String action = scanner.nextLine();
        return action.toCharArray();
    }

    public void parseSceneSize(Scanner scanner) {
        String sceneSize = scanner.nextLine();
        String[] strArray = sceneSize.split(" ");
        if (strArray.length != 2) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_SIZE);
        }

        try {
            int x = Integer.parseInt(strArray[0]);
            int y = Integer.parseInt(strArray[1]);
            if (x < 1 || y < 1){
                throw new IllegalArgumentException(Message.ERR_MSG_INVALID_SIZE);
            }
            sceneDimension = new Dimension(x, y);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_SIZE);
        }
    }
}
