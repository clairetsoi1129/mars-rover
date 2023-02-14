package controller;

import util.Message;

public class EventHandler {
    GameManager gm;

    public EventHandler(GameManager gm) {
        this.gm = gm;
    }

    public void turnLeft() {
        gm.getGui().getMessageText().setText(Message.MSG_TO_LEFT);
    }

    public void turnRight() {
        gm.getGui().getMessageText().setText(Message.MSG_TO_RIGHT);
    }

    public void moveForward() {
        gm.getGui().getMessageText().setText(Message.MSG_GO_FORWARD);
    }
}
