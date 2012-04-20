/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.scenes;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;
import se.offbeatgames.ld48lib.utilities.Util;

/**
 *
 * @author Erik
 */
public class GameScene extends Scene{

    Font font;
    float x, y, tx, ty;
    
    public GameScene(SceneManager manager) {
        super(manager);
    }

    @Override
    public void load(GameContainer container) {
        super.load(container);
        try {
            font = new AngelCodeFont("resources/fonts/font.fnt", new Image("resources/fonts/font.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        x = Util.rand(Game.width);
        y = Util.rand(Game.height);
        tx = Util.rand(Game.width);
        ty = Util.rand(Game.height);
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
        
        if(Util.distance(x, y, tx, ty) < 50f){
            tx = Util.rand(Game.width - 400);
            ty = Util.rand(Game.height);
        }
        x = Util.qLerp(x, tx, 0.01f * dt);
        y = Util.qLerp(y, ty, 0.01f * dt);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        
        font.drawString((int)x, (int)y, "This is a sample game for Ludum Dare 23!");
        font.drawString(16, 32, "Hello World!");
    }
}
