/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import java.util.ArrayList;
import java.util.List;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48.inventory.Inventory;
import se.offbeatgames.ld48.particles.ParticleManagerImpl;
import se.offbeatgames.ld48.scenes.GameScene;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48.tiles.MapTiles;

/**
 *
 * @author Erik
 */
public class CharacterManager {

    public List<GameCharacter> characters;
    public Player player;
    private GameScene scene;
    
    public CharacterManager(GameScene scene) {
        this.scene = scene;
    }

    public void load(ContentManager content) {
        characters = new ArrayList<GameCharacter>();

        player = new Player();
        player.load(content);
        player.x = Game.width / 2;
        player.y = Game.height / 2;
    }

    public void update(float dt, MapTiles map, ParticleManagerImpl pMan) {

        for (int i = 0; i < characters.size(); i++) {
            GameCharacter c = characters.get(i);
            c.update(dt, map);
        }
        player.update(dt, map, pMan, characters);

        int col = (int) ((player.x + 8) / 16);
        int row = (int) ((player.y + 8) / 16);

        if (col == 31 && row == 18) {
            if (player.inventory.items.get(Inventory.FLAG).count > 0
                && player.inventory.items.get(Inventory.TREE).count > 100
                && player.inventory.items.get(Inventory.NAIL).count > 200) {
                scene.gameOver();
            }
        }
    }

    public void draw() {
        for (int i = 0; i < characters.size(); i++) {
            GameCharacter c = characters.get(i);
            c.draw();
        }
        player.draw();
    }
}
