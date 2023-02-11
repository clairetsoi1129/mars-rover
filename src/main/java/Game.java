import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<AVehicle> rovers;
    private AScene scene;

    public Game(AInput input) {
        List<Instruction> vehicleInstruction = input.getVehicleInstruction();
        scene = new Plateau(input.getPlateauSize());

        rovers = new ArrayList<>();
        for (Instruction instruction: vehicleInstruction){
            rovers.add(new Rover(instruction, scene));
        }
    }

    public void start(){
        for (AVehicle rover: rovers){
            rover.start();
        }
    }

    public List<AVehicle> getRovers() {
        return rovers;
    }

    public AScene getScene() {
        return scene;
    }
}
