/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.tiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import se.offbeatgames.ld48.characters.GameCharacter;
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

    public MapTiles(TiledData data) {
        this.data = data;
        this.layers = data.layers;
        for (int i = 0; i < layers.length; i++) {
            layers[i].convertToGrid();
        }
    }

    public void load(ContentManager content) {
        texture = content.loadImage("gfx/maps.png");
        sheet = new SpriteSheet(texture, 16, 16);
    }

    public void collide(GameCharacter character) {
        Layer collLayer = getCollisionLayer();
        int col = (int) (character.x + 8) / 16;
        int row = (int) (character.y + 8) / 16;
        int cell = collLayer.grid[col][row];
        if (cell != -1) {
            // collision found
            character.x = character.col * 16;
            character.y = character.row * 16;
        }
    }

    public boolean collides(float x, float y) {
        Layer collLayer = getCollisionLayer();
        int col = (int) (x) / 16;
        int row = (int) (y) / 16;
        int cell = collLayer.grid[col][row];
        if (cell != -1) {
            // collision found
            return true;
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

    public void update(float dt) {
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
