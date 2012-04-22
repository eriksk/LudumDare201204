/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.audio;

import se.offbeatgames.ld48lib.content.ContentManager;
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
        soundFxVolume = 1f;
    }
    
    public void load(ContentManager content){
        sounds.put("main", content.loadWav("audio/ld2.wav"));
        sounds.put("cut", content.loadWav("audio/cut.wav"));
    }
    
    public void playSound(String name){
        sounds.get(name).playAsSoundEffect(1f, soundFxVolume, false);
    }
    public void playSong(String name){
        if(!sounds.get(name).isPlaying()){
            sounds.get(name).playAsMusic(1f, musicVolume, true);
        }
    }
    public void stopSong(String name){
        sounds.get(name).stop();
    }
    
    public void resetSong(String name){
        sounds.get(name).setPosition(0f);
    }
}
