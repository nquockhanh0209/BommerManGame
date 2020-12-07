package oop.control;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Control {
    public static final int MOVE_W = 4;
    public static final int MOVE_A = 1;
    public static final int MOVE_S = 2;
    public static final int MOVE_D = 3;
    public boolean placebomb = false;
    private Scene scene;
    public int cmd;
    public Control(Scene scene) {
        this.scene = scene;
    }

    public int controlPlayer() {
        this.scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        switch (code) {
                            case "A":
                                cmd = MOVE_A;
                                break;
                            case "D":
                                cmd = MOVE_D;
                                break;
                            case "W":
                                cmd = MOVE_W;
                                break;
                            case "S":
                                cmd = MOVE_S;
                                break;
                            case "SPACE":
                                placebomb = true;
                                break;
                        }
                    }
        });
        this.scene.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                switch (code) {
                    case "A":
                        cmd = 0;
                        break;
                    case "D":
                        cmd = 0;
                        break;
                    case "W":
                        cmd = 0;
                        break;
                    case "S":
                        cmd = 0;
                        break;
                    case "SPACE":
                        placebomb = false;
                        break;
                }
            }
        });
        return cmd;
    }

    public boolean isPlacebomb() {
        return this.placebomb;
    }

    public void setPlacebomb(boolean placebomb) {
        this.placebomb = placebomb;
    }
}
