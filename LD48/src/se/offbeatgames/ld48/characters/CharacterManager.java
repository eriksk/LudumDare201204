/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import java.util.ArrayList;
import java.util.List;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.tiles.MapTiles;

/**
 *
 * @author Erik
 */
public class CharacterManager {

    public List<GameCharacter> characters;
    public Player player;

    public CharacterManager() {
    }

    public void load(ContentManager content) {
        characters = new ArrayList<GameCharacter>();

        player = new Player();
        player.load(content);
        player.x = Game.width / 2;
        player.y = Game.height / 2;

        NPC mayor = new NPC();
        mayor.load(content);
        mayor.x = 19 * 16;
        mayor.y = 15 * 16;
        characters.add(mayor);
    }

    public void update(float dt, MapTiles map) {
        for (int i = 0; i < characters.size(); i++) {
            GameCharacter c = characters.get(i);
            c.update(dt, map);
        }
        player.update(dt, map);
    }

    public void draw() {
        for (int i = 0; i < characters.size(); i++) {
            GameCharacter c = characters.get(i);
            c.draw();
        }
        player.draw();
    }
}
