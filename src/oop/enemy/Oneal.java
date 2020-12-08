package oop.enemy;

import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import oop.Game;
import oop.entities.Entity;
import oop.graphics.Animation;
import oop.graphics.Sprite;

public class Oneal extends Entity {
    private Sprite[] onealr = {Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3};
    private Sprite[] oneall = {Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3};
    private Animation ani = new Animation(onealr, 5);
    private int time2clear = 30;
    public boolean isDestroy = false;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

        if(isDestroy) {
            this.img = Sprite.oneal_dead.getFxImage();
            time2clear--;
            if(time2clear == 0) {
                Game.entities.remove(this);
            }
        }
        else {
            ani.update();
            this.img = ani.getFrame().getFxImage();
        }
    }
    public boolean isDestroyed() {
        return this.isDestroy;
    }
}
