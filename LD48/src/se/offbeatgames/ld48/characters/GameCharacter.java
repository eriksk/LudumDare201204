/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import se.offbeatgames.ld48.speech.SpeechTree;
import se.offbeatgames.ld48lib.animations.Animation;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48.tiles.MapTiles;

/**
 *
 * @author Erik
 */
public class GameCharacter {
    
    public float x, y;
    public HashMap<String, Animation> animations;
    protected String currentAnim;
    protected SpriteSheet sheet;
    protected Image texture;

    public GameCharacter() {
        currentAnim = "";
    }
    
    public void load(ContentManager content){
        texture = content.loadImage("gfx/characters.png");
        sheet = new SpriteSheet(texture, 16, 16);
        animations = new HashMap<String, Animation>();
    }

    public void setAnim(String name){
        if(!this.currentAnim.equals(name)){
            this.currentAnim = name;
            animations.get(currentAnim).reset();
        }
    }
    
    public boolean intersects(GameCharacter other){
        if(x > other.x + 16) return false;
        if(y > other.y + 16) return false;
        if(x + 16 < other.x) return false;
        if(y + 16 < other.y) return false;
        
        return true;
    }
    
    public void update(float dt, MapTiles map){
        if(!"".equals(currentAnim)){
            animations.get(currentAnim).update(dt);
        }
    }
    
    public void draw(){
        if(!"".equals(currentAnim)){
            Animation ani = animations.get(currentAnim);
            sheet.startUse();
            int c = ani.getCurrentFrame() % 8;
            int r = ani.getCurrentFrame() / 8;
            sheet.renderInUse((int)x, (int)y, c, r);
            sheet.endUse();
        }
    }
}
