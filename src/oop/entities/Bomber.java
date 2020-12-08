package oop.entities;

import oop.Game;
import oop.graphics.Sprite;
import javafx.scene.image.Image;
import oop.control.Control;

import java.util.List;
import oop.graphics.Animation;
import oop.item.ItemB;
import oop.item.ItemF;
import oop.item.ItemS;

public class Bomber extends Entity {
    private final Sprite[] frameleft = {Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2};
    private final Sprite[] frameright = {Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2};
    private final Sprite[] frameup = {Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2};
    private final Sprite[] framedown = {Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2};
    private final Sprite[] dead = {Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3};
    private boolean isMoving = false;
    private double speed = 2;
    private int pad;
    private int dir = 0;
    public boolean isDestroy = false;
    public Animation ani = new Animation(frameleft, 3);
    public Animation aniDead = new Animation(dead, 20);
    public Control control;
    public Bomber(int x, int y, Image img, Control control) {
        super( x, y, img);
        this.control = control;
    }

    public void move() {
        switch (this.control.controlPlayer()) {
            case Control.MOVE_A:
                pad = -1;
                dir = Control.MOVE_A;
                ani.setFrames(frameleft);
                break;
            case Control.MOVE_S:
                pad = 1;
                dir = Control.MOVE_S;
                ani.setFrames(framedown);
                break;
            case Control.MOVE_D:
                pad = 1;
                dir = Control.MOVE_D;
                ani.setFrames(frameright);
                break;
            case Control.MOVE_W:
                pad = -1;
                dir = Control.MOVE_W;
                ani.setFrames(frameup);
                break;
            default:
                pad = 0;
                break;
        }
        for(Entity e : Game.stillObjects){
            if(e instanceof Wall) {
                if(this.collide(e)) {
                    if(dir == Control.MOVE_W || dir == Control.MOVE_A) {
                        //x+=1;
                        pad = 1;
                    }
                    else if(dir == Control.MOVE_D || dir == Control.MOVE_S) {
                        pad = -1;
                    }
                }
            }
        }
        for(Entity e : Game.bricks){
            if(e instanceof Brick) {
                if(this.collide(e) && !((Brick) e).isDestroy) {
                    if(dir == Control.MOVE_W || dir == Control.MOVE_A) {
                        pad = 1;
                    }
                    else if(dir == Control.MOVE_D || dir == Control.MOVE_S) {
                        pad = -1;
                    }
                }
            }
        }
        for(int i = 0; i < Game.items.size(); i++) {
            if(this.collide(Game.items.get(i))) {
                if(Game.items.get(i) instanceof ItemS) {
                    if(((ItemS) Game.items.get(i)).isDestroyed()) {
                        this.speed += 2;
                        Game.items.remove(Game.items.get(i));
                        break;
                    }
                    else {
                        if(dir == Control.MOVE_W || dir == Control.MOVE_A) {
                            pad = 1;
                        }
                        else if(dir == Control.MOVE_D || dir == Control.MOVE_S) {
                            pad = -1;
                        }
                    }
                }
                else if(Game.items.get(i) instanceof ItemF) {
                    if(((ItemF) Game.items.get(i)).isDestroyed()) {
                        Game.flameblock += 1;
                        Game.items.remove(Game.items.get(i));
                        break;
                    }
                    else {
                        if(dir == Control.MOVE_W || dir == Control.MOVE_A) {
                            pad = 1;
                        }
                        else if(dir == Control.MOVE_D || dir == Control.MOVE_S) {
                            pad = -1;
                        }
                    }
                }
                else if(Game.items.get(i) instanceof ItemB) {
                    if(((ItemB) Game.items.get(i)).isDestroyed()) {
                        Game.maxBomb += 1;
                        Game.items.remove(Game.items.get(i));
                        break;
                    }
                    else {
                        if(dir == Control.MOVE_W || dir == Control.MOVE_A) {
                            pad = 1;
                        }
                        else if(dir == Control.MOVE_D || dir == Control.MOVE_S) {
                            pad = -1;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < Game.bombs.size(); i++) {
            if (this.collide(Game.bombs.get(i))) {
                if(((Bomb)Game.bombs.get(i)).awayFromb){
                    if (dir == Control.MOVE_W || dir == Control.MOVE_A) {
                        pad = 1;
                    } else if (dir == Control.MOVE_D || dir == Control.MOVE_S) {
                        pad = -1;
                    }
                }
            }
            else {
                ((Bomb)Game.bombs.get(i)).awayFromb = true;
            }
        }
        if(pad != 0) {
            isMoving = true;
        }
        else isMoving = false;
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
        if(isDestroy) {
            aniDead.update();
            this.img = aniDead.getFrame().getFxImage();
            if(aniDead.aniDone()){
                Game.entities.remove(this);
            }
        }
        else {
            if(isMoving) ani.update();
            this.img = ani.getFrame().getFxImage();
            move();
        }
    }
}
