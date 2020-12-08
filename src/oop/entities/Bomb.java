package oop.entities;
import oop.graphics.Animation;
import oop.graphics.Sprite;
import javafx.scene.image.Image;
import oop.Game;
public class Bomb extends Entity {
    private Sprite[] frameExplode = {Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, Sprite.bomb, Sprite.bomb_1, Sprite.bomb};
    private Sprite[] flameh1 = {Sprite.explosion_horizontal,Sprite.explosion_horizontal1, Sprite.explosion_horizontal2};
    private Sprite[] flameh2 = {Sprite.explosion_horizontal,Sprite.explosion_horizontal1, Sprite.explosion_horizontal2};
    private Sprite[] flamev1 = {Sprite.explosion_vertical,Sprite.explosion_vertical1, Sprite.explosion_vertical2};
    private Sprite[] flamev2 = {Sprite.explosion_vertical,Sprite.explosion_vertical1, Sprite.explosion_vertical2};
    private Sprite[] flamemid = {Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2};
    public boolean isExplode = false;
    private boolean flameIsCreate = false;
    private int time2Explode = 100;
    private int time = 0;
    public boolean awayFromb = false;
    public Animation ani = new Animation(frameExplode, 8);
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        time++;
        if(time >= time2Explode) {
            if(ani.aniDone()) {
                isExplode = true;
                if(!flameIsCreate) {
                    this.img = Sprite.bomb_exploded1.getFxImage();
                    flameIsCreate = true;
                    int x = this.getX()/Sprite.SCALED_SIZE;
                    int y = this.getY()/Sprite.SCALED_SIZE;
                    if(!Game.getBlockType(x-1, y).equals("class oop.entities.Wall")) {
                        Entity flame1 = new Flame(x - 1, y, Sprite.explosion_horizontal.getFxImage(), flameh1);
                        Game.flames.add(flame1);
                        if(Game.flameblock == 2) {
                            if(!Game.getBrickType(x - 1, y).equals("class oop.entities.Brick") || ((Brick)Game.getBrick(x-1, y)).isDestroyed()) {
                                if (!Game.getBlockType(x - 2, y).equals("class oop.entities.Wall")) {
                                    Entity flame11 = new Flame(x - 2, y, Sprite.explosion_horizontal.getFxImage(), flameh1);
                                    Game.flames.add(flame11);
                                }
                            }
                        }

                    }
                    if(!Game.getBlockType(x+1, y).equals("class oop.entities.Wall")) {
                        Entity flame2 = new Flame(x + 1, y, Sprite.explosion_horizontal.getFxImage(), flameh2);
                        Game.flames.add(flame2);
                        if(Game.flameblock == 2) {
                            if(!Game.getBrickType(x + 1, y).equals("class oop.entities.Brick") || ((Brick)Game.getBrick(x+1, y)).isDestroyed()) {
                                if (!Game.getBlockType(x + 2, y).equals("class oop.entities.Wall")) {
                                    Entity flame12 = new Flame(x + 2, y, Sprite.explosion_horizontal.getFxImage(), flameh1);
                                    Game.flames.add(flame12);
                                }
                            }
                        }
                    }
                    if(!Game.getBlockType(x, y-1).equals("class oop.entities.Wall")) {
                        Entity flame3 = new Flame(x, y - 1, Sprite.explosion_vertical.getFxImage(), flamev1);
                        Game.flames.add(flame3);
                        if(Game.flameblock == 2) {
                            if(!Game.getBrickType(x, y - 1).equals("class oop.entities.Brick") || ((Brick)Game.getBrick(x, y-1)).isDestroyed()){
                                if (!Game.getBlockType(x , y - 2).equals("class oop.entities.Wall")) {
                                    Entity flame13 = new Flame(x, y - 2, Sprite.explosion_vertical.getFxImage(), flamev1);
                                    Game.flames.add(flame13);
                                }
                            }
                        }

                    }
                    if(!Game.getBlockType(x, y+1).equals("class oop.entities.Wall")) {
                        Entity flame4 = new Flame(x, y + 1, Sprite.explosion_vertical.getFxImage(), flamev2);
                        Game.flames.add(flame4);
                        if(Game.flameblock == 2) {
                            if(!Game.getBrickType(x, y + 1).equals("class oop.entities.Brick") || ((Brick)Game.getBrick(x, y+1)).isDestroyed()) {
                                if (!Game.getBlockType(x , y + 2).equals("class oop.entities.Wall")) {
                                    Entity flame14 = new Flame(x, y + 2, Sprite.explosion_vertical.getFxImage(), flamev2);
                                    Game.flames.add(flame14);
                                }
                            }
                        }
                    }
                    Entity flame5 = new Flame(x, y, Sprite.explosion_vertical.getFxImage(), flamemid);
                    Game.flames.add(flame5);
                }
            }
            else {
                explode();
            }
        }
    }
    public void explode() {
        ani.update();
        this.img = ani.getFrame().getFxImage();
    }
    public boolean isExplode() {
        return this.isExplode;
    }
}
