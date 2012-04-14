/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import content.ContentManager;
import java.util.HashMap;
import org.newdawn.slick.openal.Audio;

/**
 *
 * @author Erik
 */
public class AudioManager {

    private static AudioManager instance;
    private float musicVolume, soundFxVolume;
    
    static{
        instance = new AudioManager();
    }
    public static AudioManager I(){
        return instance;
    }
    
    private HashMap<String, Audio> sounds;
    private AudioManager() {
        sounds = new HashMap<String, Audio>();
        musicVolume = 0.2f;
        soundFxVolume = 0.2f;
    }
    
    public void load(ContentManager content){
        sounds.put("coin", content.loadWav("audio/coin.wav"));
        sounds.put("fire", content.loadWav("audio/fire.wav"));
        sounds.put("fire_enemy", content.loadWav("audio/fire_enemy.wav"));
        sounds.put("explosion", content.loadWav("audio/explosion.wav"));
        sounds.put("song1", content.loadWav("audio/song1.wav"));
    }
    
    public void playSound(String name){
        sounds.get(name).playAsSoundEffect(1f, soundFxVolume, false);
    }
    public void playSong(String name){
        sounds.get(name).playAsMusic(1f, musicVolume, true);
    }
    
    public void resetSong(String name){
        sounds.get(name).setPosition(0f);
    }
}
