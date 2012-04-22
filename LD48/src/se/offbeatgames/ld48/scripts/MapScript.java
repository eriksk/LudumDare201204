/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.scripts;

import se.offbeatgames.ld48.characters.CharacterManager;
import se.offbeatgames.ld48.scenes.GameScene;
import se.offbeatgames.ld48.tiles.MapTiles;

/**
 *
 * @author Erik
 */
public class MapScript {

    private String command;
    private String[] str_values;
    private int[] int_values;
    private boolean runAgain = true;

    public MapScript(String line) {
        String[] parts = line.split(":");
        command = parts[0].toLowerCase();
        if ("door".equals(command)) {
            parts = parts[1].split(",");
            int_values = new int[]{
                Integer.parseInt(parts[0].trim()),
                Integer.parseInt(parts[1].trim())
            };
            str_values = new String[]{
                parts[2].trim()
            };
        } else if ("start".equals(command)) {
            parts = parts[1].split(",");
            int_values = new int[]{
                Integer.parseInt(parts[0].trim()),
                Integer.parseInt(parts[1].trim())
            };
        } else {
        }
    }

    public void update(CharacterManager charMan, MapTiles map, GameScene game) {
        if ("door".equals(command)) {
            if ((int) ((charMan.player.x + 8) / 16) == int_values[0]
                    && (int) ((charMan.player.y + 8) / 16) == int_values[1]) {
                game.gotoScene(str_values[0]);
            }
        } else if ("start".equals(command)) {
            if (runAgain) {
                charMan.player.x = int_values[0] * 16;
                charMan.player.y = int_values[1] * 16;
                runAgain = false;
            }
        } else {
        }
    }
}
