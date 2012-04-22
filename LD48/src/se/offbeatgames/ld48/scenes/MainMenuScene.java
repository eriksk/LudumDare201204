/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.scenes;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.input.InputManager;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;

/**
 *
 * @author Erik
 */
public class MainMenuScene extends Scene {

    Font font;
    String title= "THE ISLAND";
    String subText = "In the year 2046 the world\nexploded and everything that\nwas left was a small\nIsland known as\nTHE ISLAND\nA young boy has a dream to leave\nTHE ISLAND\nand explore the world.\nBut first he needs to\nbuild a boat!";
    String pressSpace = "Press Space to Start";
    
    public MainMenuScene(SceneManager manager) {
        super(manager);
    }

    @Override
    public void onActivated() {
    }

    @Override
    public void onDeactivated() {
    }

    @Override
    public void load(GameContainer container) {
        super.load(container);
        font = new ContentManager("resources/").loadFont("fonts/font");
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if(InputManager.I().isKeyClicked(Keyboard.KEY_SPACE)){
            manager.setCurrentScene("game");
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        font.drawString((Game.width / 2f) - font.getWidth(title) / 2f, 100, title);
        font.drawString((Game.width / 2f) - font.getWidth(subText) / 2f, 150, subText);
        font.drawString((Game.width / 2f) - font.getWidth(pressSpace) / 2f, 512-50, pressSpace);
    }
    
}
