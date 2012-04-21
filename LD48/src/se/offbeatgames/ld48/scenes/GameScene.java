/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.scenes;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.ResourceLoader;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48.cameras.Camera2D;
import se.offbeatgames.ld48.characters.CharacterManager;
import se.offbeatgames.ld48.ui.Gui;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.input.InputManager;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;
import se.offbeatgames.tiles.MapTiles;
import se.offbeatgames.tiles.TiledImporter;

/**
 *
 * @author Erik
 */
public class GameScene extends Scene {

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

        charMan = new CharacterManager();
        charMan.load(content);

        cam = new Camera2D();

        gui = new Gui();
        gui.load(content);

        gotoScene("mainland");
        
        charMan.player.x = 14 * 16;
        charMan.player.y = 5 * 16;
    }

    @Override
    public void onActivated() {
    }

    @Override
    public void onDeactivated() {
    }

    float prevX, prevY;
    public void gotoScene(String name) {
        if("mainland".equals(name)){
            charMan.player.x = prevX;
            charMan.player.y = prevY;
        }else{            
            prevX = charMan.player.x;
            prevY = charMan.player.y + 16;
        }
        map = TiledImporter.load(ResourceLoader.getResource("resources/maps/" + name + ".json").getPath());
        map.load(content);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        map.update(dt, charMan, this);
        charMan.update(dt, map);
        gui.update(dt);
        cam.move(charMan.player.x, charMan.player.y);
        cam.update(dt, map);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        cam.translate(g);
        map.draw(g);
        charMan.draw();
        g.resetTransform();
        gui.draw(g);

        if(InputManager.I().isKeyDown(Keyboard.KEY_TAB)){
            g.setColor(new Color(0,0,0,220));
            g.fillRect(0, 0, Game.width, Game.height);
            charMan.player.inventory.draw(Game.width / 2, Game.height / 2, gui.font, g);
        }
    }
}
