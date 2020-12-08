package oop.enemy;

import javafx.scene.image.Image;
import oop.Game;
import oop.control.Control;
import oop.entities.Brick;
import oop.entities.Entity;
import oop.entities.Wall;
import oop.graphics.Animation;
import oop.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ballom extends Entity {
    private Sprite[] balloml = {Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3};
    private Sprite[] ballomr = {Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3};
    private Animation ani = new Animation(ballomr, 8);
    private int time2clear = 30;
    public boolean isDestroy = false;
    private boolean movex = true;
    private boolean movey = false;
    private int dir = 1;
    private int time2change = 10;
    private List<Integer> listMove = new ArrayList<>();
    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(isDestroy) {
            this.img = Sprite.balloom_dead.getFxImage();
            time2clear--;
            if(time2clear == 0) {
                Game.entities.remove(this);
            }
        }
        else {
            ani.update();
            this.img = ani.getFrame().getFxImage();
            move();
        }
    }
    public boolean isDestroyed() {
        return this.isDestroy;
    }
    public void move() {
        if(movex) {
            x += dir;
        }
        //time2change++;
        for (Entity e : Game.bricks) {
            if (e instanceof Brick) {
                if (this.collide(e)) {
                    dir = -dir;
                }
            }
        }
    }
        /**switch (cmd) {
            case Control.MOVE_A:
                pad = -1;
                dir = Control.MOVE_A;
                ani.setFrames(balloml);
                break;
            case Control.MOVE_S:
                pad = 1;
                dir = Control.MOVE_S;
                break;
            case Control.MOVE_D:
                pad = 1;
                dir = Control.MOVE_D;
                ani.setFrames(ballomr);
                break;
            case Control.MOVE_W:
                pad = -1;
                dir = Control.MOVE_W;
                break;
        }
        for(Entity e : Game.bricks){
            if(e instanceof Brick) {
                if(this.collide(e)) {
                    if(dir == Control.MOVE_A) {
                        //pad = 1;
                        //cmd = Control.MOVE_D;
                        cmd = 3;
                    }
                    else if(dir == Control.MOVE_W) {
                        //pad = 1;
                    }
                    else if(dir == Control.MOVE_D) {
                        //pad = -1;
                    }
                    else if(dir == Control.MOVE_S) {
                        // pad = -1;
                    }
                }
            }
        }
        //System.out.println(pad);
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
*/


}
