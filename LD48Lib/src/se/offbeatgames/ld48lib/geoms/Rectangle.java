/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.geoms;

/**
 *
 * @author Erik
 */
public class Rectangle {
    public float x, y, width, height;

    public Rectangle() {
    }

    public Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean contains(float x, float y){
        if(x < this.x) return false;
        if(y < this.y) return false;
        if(x > this.x + this.width) return false;
        if(y > this.y + this.height) return false;
        return true;
    }
}
