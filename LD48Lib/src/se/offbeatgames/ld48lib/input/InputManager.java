/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.input;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Erik
 */
public class InputManager {
    
    private static InputManager instance;
    static {
        instance = new InputManager();
    }    
    public static InputManager I(){
        return instance;
    }

    private HashMap<Integer, Boolean> keys;
    private HashMap<Integer, Boolean> oldkeys;
    
    private InputManager(){
    }
    
    public void bootstrap(int[] keys){
        this.keys = new HashMap<Integer, Boolean>();
        for (int i = 0; i < keys.length; i++) {
            this.keys.put(keys[i], false);
        }
        this.oldkeys = new HashMap<Integer, Boolean>();
        for (int i = 0; i < keys.length; i++) {
            this.keys.put(keys[i], false);
        }
        update();
    }
    
    public boolean isKeyUp(int key){
        return !keys.get(key);
    }
    public boolean isKeyDown(int key){
        return keys.get(key);
    }
    public boolean isKeyClicked(int key){
        return !oldkeys.get(key) && keys.get(key);
        
    }
    
    public void update(){  
         Iterator<Entry<Integer, Boolean>> iterator = keys.entrySet().iterator();
         while(iterator.hasNext()){
             // copy and clear
             Entry<Integer, Boolean> c = iterator.next();
             oldkeys.put(c.getKey(), c.getValue());
             c.setValue(Keyboard.isKeyDown(c.getKey()));
         }
    }
}
