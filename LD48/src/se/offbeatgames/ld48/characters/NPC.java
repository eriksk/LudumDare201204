/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import se.offbeatgames.ld48lib.animations.Animation;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.tiles.MapTiles;

/**
 *
 * @author Erik
 */
public class NPC extends GameCharacter{

    public NPC() {
    }

    @Override
    public void load(ContentManager content) {
        super.load(content);        
        animations.put("idle", new Animation(new int[]{ 8 }, 500f));
        setAnim("idle");
    }

    @Override
    public void update(float dt, MapTiles map) {
        super.update(dt, map);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
