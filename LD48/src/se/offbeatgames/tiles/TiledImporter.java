/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.tiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import se.offbeatgames.ld48.scripts.MapScript;
import se.offbeatgames.ld48.speech.SpeechTree;

/**
 *
 * @author Erik
 */
public class TiledImporter {

    public static MapTiles load(String path) {
        File file = new File(path);
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            String result = "";
            String line = "";
            while ((line = r.readLine()) != null) {
                result += line;
            }
            is.close();

            Gson gson = new Gson();
            TiledData data = gson.fromJson(result, new TypeToken<TiledData>() {
            }.getType());
            // Also read script
            MapScript[] scripts = loadScripts(path.substring(0, path.length() - 5) + ".script");
            CharacterDef[] characters = loadNPCS(path.substring(0, path.length() - 5) + ".npc");

            return new MapTiles(data, scripts, characters);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static CharacterDef[] loadNPCS(String path) {
        File file = new File(path);
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            List<String> lines = new ArrayList<String>();
            String line = "";
            while ((line = r.readLine()) != null) {
                lines.add(line);
            }

            CharacterDef[] characters = new CharacterDef[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                String[] params = lines.get(i).split("/");
                String[] prts = params[0].split(",");
                CharacterDef d = new CharacterDef(
                        Integer.parseInt(prts[0]), 
                        new SpeechTree(params[1]),
                        Integer.parseInt(prts[1]),
                        Integer.parseInt(prts[2]));
                characters[i] = d;
            }

            return characters;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static MapScript[] loadScripts(String path) {
        File file = new File(path);
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            List<String> lines = new ArrayList<String>();
            String line = "";
            while ((line = r.readLine()) != null) {
                lines.add(line);
            }
            MapScript[] scripts = new MapScript[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                scripts[i] = new MapScript(lines.get(i));
            }
            is.close();
            return scripts;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
