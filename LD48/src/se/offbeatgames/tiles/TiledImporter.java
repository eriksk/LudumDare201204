/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.tiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;

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
            return new MapTiles(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
