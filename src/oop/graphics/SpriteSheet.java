package oop.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {
    private String _path;
    public final int SIZE;
    public int[] _pixels;
    public BufferedImage image;

    public static SpriteSheet tiles = new SpriteSheet("res/textures/classic.png", 256);

    public SpriteSheet(String path, int size) {
        _path = path;
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            URL a = SpriteSheet.class.getResource(_path);
            //System.out.println(a);
            image = ImageIO.read(new File("res/textures/classic.png"));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, _pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
