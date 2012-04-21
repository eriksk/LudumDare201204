/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48;

import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import se.offbeatgames.ld48.scenes.GameScene;
import se.offbeatgames.ld48lib.input.InputManager;
import se.offbeatgames.ld48lib.scenes.SceneManager;

/**
 *
 * @author Erik
 */
public class Game extends BasicGame {

    public static String title = "TODO: Title";
    public static int width = 512;
    public static int height = 512;
    public static boolean fullScreen = false;
    public static int fps = -1;
    SceneManager sceneMan;

    public Game() {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        container.getGraphics().setAntiAlias(false);
        InputManager.I().bootstrap(new int[]{
            Keyboard.KEY_LEFT,
            Keyboard.KEY_RIGHT,
            Keyboard.KEY_UP,
            Keyboard.KEY_DOWN,
            Keyboard.KEY_SPACE,
            Keyboard.KEY_TAB
        });
        
        sceneMan = new SceneManager();
        sceneMan.load();
        
        GameScene gameScene = new GameScene(sceneMan);
        gameScene.load(container);
        sceneMan.addScene(gameScene, "game");
        
        sceneMan.setCurrentScene("game");
        
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        float dt = delta;
        sceneMan.update(dt);
        
        //TODO: remove before release
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            container.exit();
        }
        InputManager.I().update();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        //container.getGraphics().setAntiAlias(true);
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        sceneMan.draw(g);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer gc = new AppGameContainer(new Game());
            gc.setDisplayMode(width, height, fullScreen);
            gc.setTargetFrameRate(fps);
            gc.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }finally{
            AL.destroy();
        }
    }
}
