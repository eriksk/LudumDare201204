package content;

import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
}
