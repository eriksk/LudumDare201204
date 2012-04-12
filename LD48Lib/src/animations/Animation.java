/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animations;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Erik
 */
public class Animation {
    
    private int[] indices;
    private float current, interval;
    private Rectangle source;
    private int cellSize;

    public Animation(int[] indices, float interval, int cellSize) {
        this.indices = indices;
        this.interval = interval;
        this.source = new Rectangle(0, 0, cellSize, cellSize);
        this.current = 0f;
    }

    public Rectangle getSource() {
        return source;
    }
    
    public void Update(float dt){
        
    }    
}
