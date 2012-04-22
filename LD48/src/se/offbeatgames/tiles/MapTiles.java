/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.tiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import se.offbeatgames.ld48.characters.CharacterManager;
import se.offbeatgames.ld48.characters.GameCharacter;
import se.offbeatgames.ld48.particles.ParticleManagerImpl;
import se.offbeatgames.ld48.scenes.GameScene;
import se.offbeatgames.ld48.scripts.MapScript;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.geoms.Rectangle;

/**
 *
 * @author Erik
 */
public class MapTiles {

    private TiledData data;
    private Layer[] layers;
    private Image texture;
    private SpriteSheet sheet;
    private MapScript[] scripts;
    public CharacterDef[] characters;

    public MapTiles(TiledData data, MapScript[] scripts, CharacterDef[] characters) {
        this.data = data;
        this.layers = data.layers;
        for (int i = 0; i < layers.length; i++) {
            layers[i].convertToGrid();
        }
        this.scripts = scripts;
        this.characters = characters;
    }

    public void load(ContentManager content) {
        texture = content.loadImage("gfx/maps.png");
        sheet = new SpriteSheet(texture, 16, 16);
    }

    public boolean collides(float x, float y) {
        Layer collLayer = getCollisionLayer();
        int col = (int) (x) / 16;
        int row = (int) (y) / 16;
        if (col > -1 && row > -1 && col < collLayer.grid.length && row < collLayer.grid[0].length) {
            int cell = collLayer.grid[col][row];
            if (cell != -1) {
                // collision found
                return true;
            }
        }
        return false;
    }

    public boolean cutDownTree(float x, float y, ParticleManagerImpl pMan) {
        int col = (int) x / 16;
        int row = (int) y / 16;
        if (col > -1 && col < getCollisionLayer().width && row > -1 && row < getCollisionLayer().height) {
            for (int l = 0; l < layers.length; l++) {
                Layer layer = layers[l];
                if (layer.grid[col][row] == 1) {
                    pMan.cutTree((col * 16) + 8, (row * 16) + 8);
                    layer.grid[col][row] = -1;
                    getCollisionLayer().grid[col][row] = -1;
                    return true;
                }
            }
        }
        return false;
    }

    public Layer getCollisionLayer() {
        for (int i = 0; i < layers.length; i++) {
            if ("collision".equals(layers[i].name)) {
                return layers[i];
            }
        }
        return null;
    }

    public void update(float dt, CharacterManager charMan, GameScene game) {
        for (int i = 0; i < scripts.length; i++) {
            scripts[i].update(charMan, this, game);
        }
    }

    public void draw(Graphics g) {
        int col = 0;
        int row = 0;
        int cell = 0;
        sheet.startUse();
        for (int l = 0; l < layers.length; l++) {
            if (!"collision".equals(layers[l].name)) {
                for (int i = 0; i < layers[l].grid.length; i++) {
                    for (int j = 0; j < layers[l].grid[i].length; j++) {
                        cell = layers[l].grid[i][j];
                        if (cell != -1) {
                            col = cell % 8;
                            row = cell / 8;
                            sheet.renderInUse(i * 16, j * 16, col, row);
                        }
                    }
                }
            }
        }
        sheet.endUse();
    }
}
