package oop.entities;

import javafx.scene.image.Image;
import oop.Game;
import oop.graphics.Animation;
import oop.graphics.Sprite;

import java.util.List;

public class Flame extends Entity{
    private boolean flamedone = false;
    private int flametime = 0;
    private Animation ani;
    public Flame(int x, int y, Image img, Sprite[] s) {
        super(x, y, img);
        ani = new Animation(s, 3);
    }

    @Override
    public void update(){
        flamming();
        flametime++;
        if(flametime > 10) {
            if(ani.aniDone()) {
                flamedone = true;
                flametime = 0;
            }
        }
    }

    public void flamming() {
        ani.update();
        this.img = ani.getFrame().getFxImage();
    }

    public boolean isDone() {
        return this.flamedone;
    }
}
