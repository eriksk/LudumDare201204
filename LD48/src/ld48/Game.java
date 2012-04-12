package ld48;

import org.newdawn.slick.*;

public class Game extends BasicGame{
    public static final int width = 512;
    public static final int height = 512;
    public static final boolean fullScreen = false;
        
    Image bg;
    
    public Game() {
        super("Ludum Dare 48");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
        container.setTargetFrameRate(-1);
        
        bg = new Image("resources/bg.png");
        bg.setCenterOfRotation(bg.getWidth() / 2f, bg.getHeight() / 2f);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        bg.draw(0, 0);
    }
    
    public static void main(String[] args) {
        AppGameContainer gc;
        try {
            gc = new AppGameContainer(new Game());
            gc.setDisplayMode(width, height, fullScreen);
            gc.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }
}
