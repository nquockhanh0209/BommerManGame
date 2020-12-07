package oop.item;

import javafx.scene.image.Image;
import oop.Game;
import oop.entities.Entity;
import oop.graphics.Animation;
import oop.graphics.Sprite;

public class ItemS extends Entity {
    private Sprite[] itemframe = {Sprite.brick, Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2};
    private Animation ani = new Animation(itemframe, 6);
    public boolean isDestroy = false;
    public ItemS(int x, int y, Image img) {
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
            this.img = Sprite.powerup_speed.getFxImage();
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