package content;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Erik
 */
public class ContentManager {
    
    private String root = "";
    
    public ContentManager(){
        this.root = "resources/";
    }
    public ContentManager(String root){
        this.root = root;
        if(!this.root.endsWith("/")){
            this.root += "/";
        }
    }
    
    public String getRoot() {
        return root;
    }
    
    public Image loadImage(String path){
        try {
            return new Image(root + path);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
