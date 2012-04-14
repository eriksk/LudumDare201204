/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

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
    
    private InputManager(){
        
    }

    public boolean isKeyDown(int key){
        return Keyboard.isKeyDown(key);
    }
}
