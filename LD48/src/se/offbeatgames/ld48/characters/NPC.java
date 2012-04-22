/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import se.offbeatgames.ld48.speech.SpeechTree;
import se.offbeatgames.ld48lib.animations.Animation;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.tiles.MapTiles;

/**
 *
 * @author Erik
 */
public class NPC extends GameCharacter{

    SpeechTree tree;
    int type;
    int col, row;
    
    public NPC(SpeechTree tree, int type, int col, int row) {
        this.tree = tree;
        this.type = type;
        this.col = col;
        this.row = row;
    }

    @Override
    public void load(ContentManager content) {
        super.load(content);        
        animations.put("idle", new Animation(new int[]{ type }, 500f));
        setAnim("idle");
        x = col * 16;
        y = row * 16;
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
