package input;

import model.Direction;
import model.Instruction;
import util.Message;

import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class KeyboardInput extends AInput{
    private List<Instruction> instructionList = new ArrayList<>();

    public KeyboardInput() {
        super();
        Scanner scanner=new Scanner(System.in);
        printWelcomeMessage();
        parseSceneSize(scanner);
        Instruction instruction = parseInitialPosDirection(scanner);
        instruction.setMovements(parseMovement(scanner));
        instructionList.add(instruction);
        scanner.close();
    }

    private void printWelcomeMessage(){
        System.out.println(Message.TITLE);
        System.out.println(Message.WELCOME_MSG);
    }

    public void parseSceneSize(Scanner in){
        int x = 0;
        int y = 0;

        System.out.println(Message.MSG_GET_PLATEAU_SIZE);
        System.out.println(Message.MSG_PLATEAU_WIDTH);

        try {
            x = in.nextInt();
            System.out.println(Message.MSG_PLATEAU_HEIGHT);
            y = in.nextInt();
        }catch (InputMismatchException e) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_SIZE);
        }

        sceneDimension = new Dimension(x, y);
    }

    public Instruction parseInitialPosDirection(Scanner scanner) {
        return new Instruction(parseInitialPos(scanner), parseInitialDirection(scanner));
    }

    private Point parseInitialPos(Scanner scanner){
        int x = 0;
        int y = 0;

        try {
            System.out.println(Message.MSG_GET_INIT_POS);
            System.out.println(Message.MSG_INIT_POS_X);

            x = scanner.nextInt();
            System.out.println(Message.MSG_INIT_POS_Y);
            y = scanner.nextInt();
            scanner.nextLine(); //to consume the newline in previous in.nextInt() input
        }catch (InputMismatchException e) {
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_POS);
        }
        return new Point(x,y);
    }

    private Direction parseInitialDirection(Scanner scanner){
        Direction direction = null;
        try {
            System.out.println(Message.MSG_INIT_DIR);
            String input = scanner.nextLine();
            direction = Direction.valueOf(input.trim().toUpperCase());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(Message.ERR_MSG_INVALID_DIR);
        }
        return direction;
    }

    public char[] parseMovement(Scanner scanner){
        System.out.println(Message.MSG_SETUP_DONE);
        System.out.println(Message.MSG_GET_INSTRUCTION);
        String movement = scanner.nextLine();
        return movement.toCharArray();
    }

    public List<Instruction> getVehicleInstruction(){
        return instructionList;
    }
}
