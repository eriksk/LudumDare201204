/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.tiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.util.ResourceLoader;
import se.offbeatgames.ld48.scripts.MapScript;
import se.offbeatgames.ld48.speech.SpeechTree;

/**
 *
 * @author Erik
 */
public class TiledImporter {

    public static MapTiles load(String path) {
        InputStream is = null;
        try {
            is = ResourceLoader.getResourceAsStream(path);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            String result = "";
            String line = "";
            while ((line = r.readLine()) != null) {
                result += line;
            }
            is.close();

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(TiledData.class, new JsonDeserializer<TiledData>(){
                @Override
                public TiledData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    int width = json.getAsJsonObject().get("width").getAsInt();
                    int height = json.getAsJsonObject().get("height").getAsInt();
                    int tileWidth = json.getAsJsonObject().get("tilewidth").getAsInt();
                    int tileHeight = json.getAsJsonObject().get("tileheight").getAsInt();
                    JsonArray jLayers = json.getAsJsonObject().get("layers").getAsJsonArray();
                    Layer[] layers = new Layer[jLayers.size()];
                    for (int i = 0; i < jLayers.size(); i++) {
                        JsonObject l = jLayers.get(i).getAsJsonObject();
                        int lWidth = l.getAsJsonObject().get("width").getAsInt();
                        int lHeight = l.getAsJsonObject().get("height").getAsInt();
                        String name = l.getAsJsonObject().get("name").getAsString();
                        JsonArray jData = l.getAsJsonObject().get("data").getAsJsonArray();
                        int[] data = new int[jData.size()];
                        for (int j = 0; j < jData.size(); j++) {
                            data[j] = jData.get(j).getAsInt();
                        }
                        layers[i] = new Layer(data, lWidth, lHeight, 1f, name);
                    }
                    return new TiledData(width, height, tileWidth, tileHeight, layers);
                }                
            });
            
            Gson gson = builder.create();
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
        InputStream is = null;
        try {
            is = ResourceLoader.getResourceAsStream(path);
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
        InputStream is = null;
        try {
            is = ResourceLoader.getResourceAsStream(path);
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
