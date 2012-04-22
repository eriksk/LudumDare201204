/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.speech;

import se.offbeatgames.ld48.characters.Player;
import se.offbeatgames.ld48.inventory.Inventory;
import se.offbeatgames.ld48.ui.Gui;

/**
 *
 * @author Erik
 */
public class SpeechLine {

    public String command;
    public String[] str_values;
    public int[] int_values;

    public SpeechLine(String command, String[] str_values, int[] int_values) {
        this.command = command;
        this.str_values = str_values;
        this.int_values = int_values;
    }

    public void run(Player player) {
        if ("text".equals(command)) {
        } else if ("give".equals(command)) {
            switch (int_values[0]) {
                case Inventory.TREE:
                    player.inventory.add(Inventory.TREE, 1);
                    break;
                case Inventory.FLAG:
                    player.inventory.add(Inventory.FLAG, 1);
                    Gui.log("1 Flag was added to inventory");
                    break;
                case Inventory.NAIL:
                    player.inventory.add(Inventory.NAIL, 1);
                    Gui.log("1 Nail was added to inventory");
                    break;
                case -1:
                    player.canCutTrees = true;
                    Gui.log("Ability CUT learned!");
                    break;
                case -2:
                    player.canCutTrees = true;
                    player.inventory.add(Inventory.NAIL, 20);
                    Gui.log("20 Nails were added to inventory");
                    break;
            }
        }
    }
}
