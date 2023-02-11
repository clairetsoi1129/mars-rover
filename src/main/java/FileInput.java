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
            throw new IllegalArgumentException("Invalid vehicle initial position or initial direction.");
        }
        Point initialPos = new Point(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]));
        Direction direction = Direction.valueOf(strArray[2]);
        return new Instruction(initialPos, direction);
    }

    public char[] parseMovement(Scanner scanner) {
        String action = scanner.nextLine();
        return action.toCharArray();
    }

    public void parseSceneSize(Scanner scanner){
        String sceneSize = scanner.nextLine();
        String[] strArray = sceneSize.split(" ");
        if (strArray.length != 2) {
            throw new IllegalArgumentException("Invalid plateau size.");
        }
        sceneDimension = new Dimension( Integer.parseInt(strArray[0]),Integer.parseInt(strArray[1]));
    }

    public Dimension getSceneSize(){
        return sceneDimension;
    }
    public List<Instruction> getVehicleInstruction(){
        return instructionList;
    }
}
