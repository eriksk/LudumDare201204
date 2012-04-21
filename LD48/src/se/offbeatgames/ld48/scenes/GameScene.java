/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.scenes;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.util.ResourceLoader;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;
import se.offbeatgames.ld48lib.utilities.Util;
import se.offbeatgames.tiles.MapTiles;
import se.offbeatgames.tiles.TiledImporter;

/**
 *
 * @author Erik
 */
public class GameScene extends Scene{

    ContentManager content;
    MapTiles map;
    
    Font font;
    float x, y, tx, ty;
    String message = "This is a sample game for Ludum Dare 23!!!!! :D";
    int start, end;
    
    public GameScene(SceneManager manager) {
        super(manager);
    }

    @Override
    public void load(GameContainer container) {
        super.load(container);
        content = new ContentManager("resources/");
        
        try {
            font = new AngelCodeFont("resources/fonts/font.fnt", new Image("resources/fonts/font.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        x = Util.rand(Game.width);
        y = Util.rand(Game.height);
        tx = Util.rand(Game.width);
        ty = Util.rand(Game.height);
        start = 0;
        end = message.length();
        
        map = TiledImporter.load(ResourceLoader.getResource("resources/maps/mainland.json").getPath());
        map.load(content);
    }
    
    @Override
    public void onActivated() {
    }

    @Override
    public void onDeactivated() {
    }

    float second = 0;
    @Override
    public void update(float dt) {
        super.update(dt);
        
        if(Util.distance(x, y, tx, ty) < 50f){
            tx = Util.rand(Game.width - 400);
            ty = Util.rand(Game.height);
        }
        second += dt;
        if(second > 100){
            second = 0f;
            start++;
            if(start == end)
                start = 0;
        }
        x = Util.qLerp(x, tx, 0.01f * dt);
        y = Util.qLerp(y, ty, 0.01f * dt);
        
        map.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        map.draw(g);
        font.drawString((int)x, (int)y, message, Color.white, 0, start);
    }
}
