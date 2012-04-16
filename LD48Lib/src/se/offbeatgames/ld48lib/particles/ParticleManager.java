/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.particles;

import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.utilities.Pool;

/**
 *
 * @author Erik
 */
public class ParticleManager {

    private int width;
    private int height;
    protected Pool particles;
    
    public ParticleManager(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void load(ContentManager content){
        particles = new Pool(0);
    }
    
    public void update(float dt){
        // override this
    }
    
    public void draw(){     
        // override this
    }
}
