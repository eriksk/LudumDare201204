/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.cameras;

import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48.Game;
import se.offbeatgames.ld48.tiles.MapTiles;

/**
 *
 * @author Erik
 */
public class Camera2D {

    public float x, y;
    public float targetX, targetY;
    public float speed = 0.1f;
    public float scale = 2;

    public Camera2D() {
    }
    
    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public void update(float dt, MapTiles map){
        // Todo: clamp to mapsize probably after scale
    }
    
    public void translate(Graphics g){
        g.translate((int)(-x + (Game.width / 2f)), (int)(-y  + (Game.height / 2)));
    }
}
