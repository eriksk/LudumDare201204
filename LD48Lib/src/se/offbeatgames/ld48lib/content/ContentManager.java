package se.offbeatgames.ld48lib.content;

import java.util.HashMap;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Erik
 */
public class ContentManager {
    
    private HashMap<String, Image> images;
    private String root = "";
    
    public ContentManager(){
        this("resources/");
    }
    
    public ContentManager(String root){
        this.root = root;
        if(!this.root.endsWith("/")){
            this.root += "/";
        }
        images = new HashMap<String, Image>();
    }
    
    public String getRoot() {
        return root;
    }
    
    /**
     * Loads an image.
     * If an image is already in memory, an instance to this will be loaded instead.
     * @param path
     * @return 
     */
    public Image loadImage(String path){
        if(images.containsKey(path)){
            return images.get(path);
        }
        
        try {
            images.put(path, new Image(root + path));
        } catch (SlickException ex) {
            ex.printStackTrace();
            return null;
        }
        
        return loadImage(path);
    }
    
    public Audio loadWav(String path){
        Audio a = null;
        try {
            a = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(root + path));
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        return a;
    }
    
    public Font loadFont(String path){
        Font font;
        try {
            font = new AngelCodeFont(root + path + ".fnt", new Image(root + path + ".png"));
            return font;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
