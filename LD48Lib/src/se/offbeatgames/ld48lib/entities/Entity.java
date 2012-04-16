/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.entities;

import se.offbeatgames.ld48lib.geoms.Rectangle;
import org.newdawn.slick.Image;

/**
 *
 * @author Erik
 */
public class Entity {
    
    protected Image image;
    public float x, y, vx, vy;
    public float rotation;
    public Rectangle source;

    public Entity(Image imageReference) {
        this.image = imageReference;
        source = new Rectangle(0, 0, image.getWidth(), image.getHeight());
    }
    
    public Rectangle getSource() {
        return source;
    }
    
    public boolean intersects(Entity other){
        if(other.x + other.source.width < x) return false;
        if(other.y + other.source.height < y) return false;
        if(x + source.width < other.x) return false;
        if(y + source.height < other.y) return false;
        
        return true;
    }

    public void update(float dt){
    }
    
    public void draw(){
        //image.setRotation(rotation);
        image.draw(x, y, source.x, source.y, source.x + source.width, source.y + source.width);
    }
}
