package oop.entities;
import oop.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getW(){
        return (int)this.img.getWidth();
    }
    public int getH(){
        return (int)this.img.getHeight();
    }
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public void update() {}
    public boolean collide(Entity e) {
        int sumX = this.getX()+this.getW();
        int sumY = this.getY()+this.getH();
        if(sumX >= e.getX()+10 && sumY >= e.getY()+5 && this.getX() <= e.getX()+e.getW()-5 && this.getY()<=e.getY()+e.getH()-10) {
            return true;
        }
        return false;
    }
}