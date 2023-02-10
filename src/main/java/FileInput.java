import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInput extends AInput {
    private int plateauLength = 0;
    private int plateauHeight = 0;

    private List<Instruction> instructionList = new ArrayList<>();

    public FileInput(String filename) {
        try {
            File inputFile = new File(filename);
            Scanner scanner = new Scanner(inputFile);
            parsePlateauSize(scanner.nextLine());

            while (scanner.hasNextLine()) {
                parseVehicleInstruction(scanner.nextLine(), scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
        }
    }

    private void parsePlateauSize(String plateauSize){
        String[] strArray = plateauSize.split(" ");
        if (strArray.length != 2) {
            throw new IllegalArgumentException("Invalid plateau size.");
        }
        plateauLength = Integer.parseInt(strArray[0]);
        plateauHeight = Integer.parseInt(strArray[1]);
    }

    private void parseVehicleInstruction(String initialPosAndDir, String action){
        String[] strArray = initialPosAndDir.split(" ");
        if (strArray.length != 3) {
            throw new IllegalArgumentException("Invalid vehicle initial position or initial direction.");
        }
        Point initialPos = new Point(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]));
        Direction direction = Direction.valueOf(strArray[2]);
        char[] actionCharArr = action.toCharArray();
        instructionList.add(new Instruction(initialPos, direction, actionCharArr));
    }

    public Dimension getPlateauSize(){
        return new Dimension(plateauLength,plateauHeight);
    }

    public List<Instruction> getVehicleInstruction(){
        return instructionList;
    }
}
