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
import se.offbeatgames.ld48.cameras.Camera2D;
import se.offbeatgames.ld48.characters.CharacterManager;
import se.offbeatgames.ld48.characters.Player;
import se.offbeatgames.ld48.ui.Gui;
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
    CharacterManager charMan;
    Gui gui;
    Camera2D cam;
    
    public GameScene(SceneManager manager) {
        super(manager);
    }

    @Override
    public void load(GameContainer container) {
        super.load(container);
        content = new ContentManager("resources/");
        
        map = TiledImporter.load(ResourceLoader.getResource("resources/maps/mainland.json").getPath());
        map.load(content);
        
        charMan = new CharacterManager();
        charMan.load(content);
        
        cam = new Camera2D();
        
        gui = new Gui();
        gui.load(content);
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
        charMan.update(dt, map);
        map.update(dt);
        
        gui.update(dt);
        
        cam.move(charMan.player.x, charMan.player.y);
        cam.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        cam.translate(g);
        map.draw(g);
        charMan.draw();
        g.resetTransform();
        gui.draw(g);
    }
}
