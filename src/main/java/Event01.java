public class Event01 {
    GameManager gm;

    public Event01(GameManager gm) {
        this.gm = gm;
    }

    public void turnLeft(){
        gm.getGui().getMessageText().setText("Turn Left");

    }

    public void turnRight(){
        gm.getGui().getMessageText().setText("Turn Right");

    }

    public void moveForward(){
        gm.getGui().getMessageText().setText("Move Forward");
    }


}
