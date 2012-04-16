    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48lib.content.ContentManager;

/**
 *
 * @author Erik
 */
public abstract class Scene implements SceneListener{

    protected SceneManager manager;

    public Scene(SceneManager manager) {
        this.manager = manager;
    }
        
    public void load(GameContainer container){
    }
    
    public void update(float dt){
    }
    
    public void draw(Graphics g){
    }
}
