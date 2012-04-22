/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.scenes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;

/**
 *
 * @author Erik
 */
public class GameOverScene extends Scene{

    public GameOverScene(SceneManager manager) {
        super(manager);
    }

    @Override
    public void load(GameContainer container) {
        super.load(container);
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
        g.setColor(Color.white);
        g.drawString("TEH GAM3 ARE OVAR!", Game.width / 2, Game.height / 2);
    }
}
