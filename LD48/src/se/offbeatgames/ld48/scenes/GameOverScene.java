/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.scenes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;

/**
 *
 * @author Erik
 */
public class GameOverScene extends Scene{

    Font font;
    String text = "Thus, the boy succeeded in building\nhis boat. But as he\nsailed towards the darkness\nthat laid ahead, the boat\nreached a stop.\nThe nothingness was the\nend of the world. And it was\nonly 100 feet from the dock.\nToo bad!\n\nThanks for playing!\n/@Th3dz";
    
    public GameOverScene(SceneManager manager) {
        super(manager);
    }

    @Override
    public void load(GameContainer container) {
        super.load(container);
        font = new ContentManager("resources/").loadFont("fonts/font");
    }

    @Override
    public void onActivated() {
    }

    @Override
    public void onDeactivated() {
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        font.drawString((Game.width / 2f) - font.getWidth(text) / 2f, 100, text);
    }
}
