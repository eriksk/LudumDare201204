/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.scenes;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48lib.input.InputManager;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;

/**
 *
 * @author Erik
 */
public class MainMenuScene extends Scene {

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
        g.setColor(Color.white);
        g.drawString("TEH GAM3 NAMEZ", Game.width / 2f, 100);
        g.drawString("Prezz SPaCe to Startz!", Game.width / 2f, 150);
    }
    
}
