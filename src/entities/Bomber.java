package entities;

import javafx.scene.image.Image;
import control.Control;
import java.util.List;

public class Bomber extends Entity {
    private double speed = 1;
    private int pad;
    private int dir = 0;
    public Control control;
    public List<Entity> stillObjs;
    public Bomber(int x, int y, Image img, Control control, List<Entity> stillObjs) {
        super( x, y, img);
        this.control = control;
        this.stillObjs = stillObjs;
    }

    public void move() {
        switch (this.control.controlPlayer()) {
            case Control.MOVE_A:
                pad = -2;
                dir = Control.MOVE_A;
                break;
            case Control.MOVE_S:
                pad = 2;
                dir = Control.MOVE_S;
                break;
            case Control.MOVE_D:
                pad = 2;
                dir = Control.MOVE_D;
                break;
            case Control.MOVE_W:
                pad = -2;
                dir = Control.MOVE_W;
                break;
            case 0:
                pad = 0;
                break;
        }
        for(Entity e : stillObjs){
            if(e instanceof Wall) {
                if(this.collide(e)) {
                    if(dir == Control.MOVE_W || dir == Control.MOVE_A) {
                        pad = 1;
                    }
                    else if(dir == Control.MOVE_D || dir == Control.MOVE_S) {
                        pad = -1;
                    }
                }
            }
        }
        go();
    }

    public void go() {
        if(dir == Control.MOVE_A || dir == Control.MOVE_D) {
            x+=pad*speed;
        }
        else if(dir == Control.MOVE_W || dir == Control.MOVE_S) {
            y+=pad*speed;
        }

    }

    @Override
    public void update() {
        move();
    }

    @Override
    public boolean collide(Entity e) {
        int sumX = this.getX()+this.getW();
        int sumY = this.getY()+this.getH();
        if(sumX >= e.getX()+10 && sumY >= e.getY() && this.getX() <= e.getX()+e.getW() && this.getY()<=e.getY()+e.getH()) {
            return true;
        }
        return false;
    }
}
