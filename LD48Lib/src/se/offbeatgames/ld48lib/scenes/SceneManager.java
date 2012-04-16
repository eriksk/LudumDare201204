/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.scenes;

import java.util.HashMap;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48lib.content.ContentManager;

/**
 *
 * @author Erik
 */
public class SceneManager {

    private HashMap<String, Scene> scenes;
    private String currentScene;
    
    public SceneManager() {
        currentScene = "";
        scenes = new HashMap<String, Scene>();
    }
    
    public void load(){
    }
    
    public void addScene(Scene scene, String name){
        scenes.put(name, scene);
    }

    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
        this.scenes.get(currentScene).onActivated();
    }

    public void update(float dt){
        if(!"".equals(currentScene)){
            scenes.get(currentScene).update(dt);
        }
    }
    
    public void draw(Graphics g){
        if(!"".equals(currentScene)){
            scenes.get(currentScene).draw(g);
        }
    }
}
