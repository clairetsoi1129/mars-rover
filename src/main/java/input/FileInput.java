package input;

import model.Direction;
import model.Instruction;
import util.Message;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
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
            System.out.println("Input file not found");
        }
    }

    public Instruction parseInitialPosDirection(Scanner scanner) {
        String initialPosAndDir = scanner.nextLine();
        String[] strArray = initialPosAndDir.split(" ");
        if (strArray.length != 3) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_POS_DIR);
        }

        Point initialPos = null;
        Direction direction = null;
        try {
            initialPos = new Point(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]));
            direction = Direction.valueOf(strArray[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_POS_DIR);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_POS_DIR);
        }
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
            sceneDimension = new Dimension(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_SIZE);
        }
    }

    public Dimension getSceneSize() {
        return sceneDimension;
    }

    public List<Instruction> getVehicleInstruction() {
        return instructionList;
    }
}
