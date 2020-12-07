package oop.entities;

import javafx.scene.image.Image;
import oop.Game;
import oop.graphics.Animation;
import oop.graphics.Sprite;

public class Brick extends Entity{
    private Sprite[] brickexplode = {Sprite.brick, Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2};
    private Animation ani = new Animation(brickexplode, 6);
    public boolean isDestroy = false;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }
    @Override
    public void update() {
        if(!ani.aniDone()) {
            if(isDestroy) {
                ani.update();
                this.img = ani.getFrame().getFxImage();
            }
        }
        else {
            Game.bricks.remove(this);
        }

    }
    @Override
    public boolean collide(Entity e){
        return false;
    }

    public boolean isDestroyed() {
        return this.isDestroy;
    }
}
