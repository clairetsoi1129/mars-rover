import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<AVehicle> rovers;
    private AScene plateau;

    public Game(AInput input) {
        List<Instruction> vehicleInstruction = input.getVehicleInstruction();
        plateau = new Plateau(input.getSceneSize());

        rovers = new ArrayList<>();
        for (Instruction instruction: vehicleInstruction){
            rovers.add(new Rover(instruction, plateau));
        }
    }

    public void start(){
        for (AVehicle rover: rovers){
            rover.go();
        }
    }

    public List<AVehicle> getRovers() {
        return rovers;
    }

    public static void main(String[] args){
        AInput input = new KeyboardInput();
        Game game = new Game(input);
        game.start();
        System.out.println("Final Destination:"+game.getRovers().get(0).getPosDir());
    }
}
