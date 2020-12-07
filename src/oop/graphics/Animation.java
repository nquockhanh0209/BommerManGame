package oop.graphics;

public class Animation {
    private Sprite[] frames;
    private int currentFrame;
    private int numFrames;
    private int count;
    private int delay;
    private boolean aniDone = false;

    public Animation(Sprite[] frames, int delay) {
        this.frames = frames;
        this.numFrames = frames.length;
        this.delay = delay;
    }
    public Sprite getFrame () {
        return this.frames[currentFrame];
    }
    public void setFrames (Sprite[] s) {
        this.frames = s;
    }
    public void update() {
        if(delay == -1) return;
        count++;
        if(count == delay) {
            currentFrame++;
            count = 0;
        }
        if(currentFrame == numFrames) {
            aniDone = true;
            currentFrame = 0;
        }
    }
    public boolean aniDone() {
        return aniDone;
    }
}
