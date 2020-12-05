package entities;

import javafx.scene.image.Image;
import control.Control;

public class Bomber extends Entity {
    private double speed = 1;
    private int pad;
    private int dir = 0;
    public Control control;
    public Bomber(int x, int y, Image img, Control control) {
        super( x, y, img);
        this.control = control;
    }

    public void move() {
        switch (this.control.controlPlayer()) {
            case 1:
                pad = -2;
                dir = 0;
                break;
            case 2:
                pad = 2;
                dir = 1;
                break;
            case 3:
                pad = 2;
                dir = 0;
                break;
            case 4:
                pad = -2;
                dir = 1;
                break;
            case 0:
                pad = 0;
                break;
        }
        go();
    }

    public void go() {
        if(dir == 0) {
            x+=pad*speed;
        }
        else if(dir == 1) {
            y+=pad*speed;
        }

    }

    @Override
    public void update() {
        move();
    }
}
