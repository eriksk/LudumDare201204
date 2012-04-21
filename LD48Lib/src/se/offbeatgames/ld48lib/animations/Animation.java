/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.animations;

/**
 *
 * @author Erik
 */
public class Animation {
    
    private int[] indices;
    private float current, interval;
    private int cellSize;
    private int currentFrame;

    public Animation(int[] indices, float interval) {
        this.indices = indices;
        this.interval = interval;
        this.current = 0;
        this.currentFrame = 0;
    }

    public void reset() {
        this.current = 0;       
        this.currentFrame = 0;
    }

    public int getCurrentFrame() {
        return indices[currentFrame];
    }
    
    public void update(float dt){
        current += dt;
        if(current > interval){
            current = 0;
            currentFrame++;
            if(currentFrame >= indices.length){
                currentFrame = 0;
            }
        }
    }
}
