package oop;

import oop.control.Control;
import oop.enemy.Ballom;
import oop.enemy.Oneal;
import oop.entities.*;
import oop.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import oop.item.ItemB;
import oop.item.ItemF;
import oop.item.ItemS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.imageio.IIOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>(); // chua tat ca doi tuong co the di chuyen
    public static List<Entity> bricks = new ArrayList<>(); // chua tat ca doi tuong brick co the pha huy
    public static List<Entity> stillObjects = new ArrayList<>(); // chua block k the pha huy
    public static List<Entity> bombs = new ArrayList<>(); // chua bomb
    public static List<Entity> flames = new ArrayList<>(); // chua lua
    public static List<Entity> items = new ArrayList<>(); // chua item powerup
    private Control input;
    private Bomber bomber;
    public static int maxBomb = 1;
    public static int flameblock = 1;
    private boolean press = false;
    public Game(Scene scene, GraphicsContext gc, Canvas canvas) {
        this.input = new Control(scene);
        this.bomber = new Bomber(2, 3, Sprite.player_right.getFxImage(), input);
        this.gc = gc;
        this.canvas = canvas;
    }

    public void loadGame() throws Exception{
        createMap();
        entities.add(new Wall(0,14,Sprite.wall.getFxImage()));
        entities.add(bomber);
    }

    public void createMap() throws Exception {
        //File file = new File("res/textures/level1.txt");
        File file = new File("res/textures/test");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string;
        int h = 0;
        int w = 0;
        while ((string = br.readLine()) != null) {
            w = string.length();
            for (int i = 0; i < w; i++) {
                char block = string.charAt(i);
                switch (block) {
                    case '1':
                        stillObjects.add(new Wall(i, h, Sprite.wall.getFxImage()));
                        bricks.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        break;
                    case '0':
                        stillObjects.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        bricks.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        break;
                    case '2':
                        stillObjects.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        bricks.add(new Brick(i, h, Sprite.brick.getFxImage()));
                        break;
                    case '3':
                        stillObjects.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        bricks.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        items.add(new ItemS(i, h, Sprite.brick.getFxImage()));
                        break;
                    case '4':
                        stillObjects.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        items.add(new ItemB(i, h, Sprite.brick.getFxImage()));
                        bricks.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        break;
                    case '5':
                        stillObjects.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        items.add(new ItemF(i, h, Sprite.brick.getFxImage()));
                        bricks.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        break;
                    case 'b':
                        stillObjects.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        entities.add(new Ballom(i, h, Sprite.balloom_right1.getFxImage()));
                        bricks.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        System.out.println("ok");
                        break;
                    case 'o':
                        stillObjects.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        entities.add(new Oneal(i, h, Sprite.oneal_right1.getFxImage()));
                        bricks.add(new Grass(i, h, Sprite.grass.getFxImage()));
                        break;
                }
            }
            h++;
        }
    }

    public void update() {
        if(input.isPlacebomb() && maxBomb >= 1 && !press) {
            Entity bomb;
            int x = (bomber.getX() + 15) /Sprite.SCALED_SIZE;
            int y = (bomber.getY() + 20) /Sprite.SCALED_SIZE;
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            bombs.add(bomb);
            maxBomb-=1;
            press = true;
            input.setPlacebomb(false);
        }
        else if(!input.isPlacebomb()) {
            press = false;
        }
        for(int i = 0; i < bricks.size(); i++) {
            bricks.get(i).update();
        }
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        //for(Entity e : flames) {
           // e.update();
        //}
        for(int i = 0; i < bombs.size(); i++) {
            Bomb s = (Bomb)bombs.get(i);
            s.update();
            if(s.isExplode()) {
                bombs.remove(s);
                maxBomb+=1;
            }
        }
        for(int i = 0; i < flames.size(); i++) {
            Flame s = (Flame)flames.get(i);
            s.update();
            if(s.isDone()) {
                flames.remove(s);
            }
        }
        for(int i = 0; i < bricks.size(); i++) {
            for(int j = 0; j < flames.size(); j++) {
                if(bricks.get(i) instanceof Brick) {
                    if(flames.get(j).collide(bricks.get(i))) {
                        //bricks.remove(bricks.get(i));
                        ((Brick) bricks.get(i)).isDestroy = true;
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < entities.size(); i++) {
            for(int j = 0; j < flames.size(); j++) {
                if(flames.get(j).collide(entities.get(i))) {
                    if(entities.get(i) instanceof Oneal) {
                        ((Oneal) entities.get(i)).isDestroy = true;
                    }
                    else if(entities.get(i) instanceof Ballom) {
                        ((Ballom) entities.get(i)).isDestroy = true;
                    }
                    else if(entities.get(i) instanceof Bomber) {
                        ((Bomber) entities.get(i)).isDestroy = true;
                    }
                }
            }
        }
        for(int i = 0; i < items.size(); i++) {
            for(int j = 0; j < flames.size(); j++) {
                if(flames.get(j).collide(items.get(i))) {
                    //bricks.remove(bricks.get(i));
                    if(items.get(i) instanceof ItemF) {
                        ((ItemF) items.get(i)).isDestroy = true;
                    }
                    else if(items.get(i) instanceof ItemB) {
                        ((ItemB) items.get(i)).isDestroy = true;
                    }
                    else if(items.get(i) instanceof ItemS) {
                        ((ItemS) items.get(i)).isDestroy = true;
                    }
                    break;
                }
            }
        }
        for(int i = 0; i < items.size(); i++) {
            items.get(i).update();
        }
    }

    public void render() {
       // System.out.println(getBrickType(7, 1) + " " + getBlockType(7, 1));
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        for(Entity b : bricks) {
            if(!(b instanceof Grass)){
                b.render(gc);
            }
        }
        items.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        flames.forEach(g -> g.render(gc));

    }

    public void gameAdd(Entity e) {
        this.entities.add(e);
    }

    public void loop() {
        update();
        render();
    }

    public static String getBlockType(int x, int y) {
        String type = "";
        int i = x + (WIDTH*y);
        return stillObjects.get(i).getClass().toString();
    }

    public static String getBrickType(int x, int y) {
        String type = "";
        int i = x + (WIDTH*y);
        return bricks.get(i).getClass().toString();
    }

    public static Entity getBrick(int x, int y) {
        int i = x + (WIDTH*y);
        return (Brick)bricks.get(i);
    }

}
