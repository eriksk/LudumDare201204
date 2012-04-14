/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particles;

import content.ContentManager;
import utilities.Pool;

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
