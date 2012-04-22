/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.speech;

import java.util.ArrayList;
import java.util.List;
import se.offbeatgames.ld48.inventory.Inventory;
import se.offbeatgames.ld48.ui.Gui;

/**
 *
 * @author Erik
 */
public class SpeechTree {

    private String text;
    private int current;
    List<SpeechLine> lines;

    public SpeechTree(String text) {
        this.text = text;
        lines = parse(text);
    }

    public void reset() {
        //TODO: reset on new scene
        current = 0;
    }

    public boolean done() {
        return current == lines.size() - 1;
    }

    public SpeechLine getNext() {
        if (current == lines.size()) {
            reset();
            return null;
        }
        SpeechLine line = lines.get(current++);
        return line;
    }

    public String getAllText() {
        String result = "";
        for (int i = 0; i < lines.size(); i++) {
            if ("text".equals(lines.get(i).command)) {
                result += lines.get(i).str_values[0];
            }
        }
        return result;
    }

    private List<SpeechLine> parse(String text) {
        lines = new ArrayList<SpeechLine>();
        String[] parts = text.split("&");
        for (int i = 0; i < parts.length; i++) {
            String[] params = parts[i].split("=");
            String command = params[0].toLowerCase();
            if ("text".equals(command)) {
                lines.add(new SpeechLine(command,
                        new String[]{
                            params[1]
                        },
                        new int[]{}));
            } else if ("give".equals(command)) {
                String obj = params[1];
                int val = 0;
                if ("TREE".equals(obj)) {
                    val = Inventory.TREE;
                } else if ("NAIL".equals(obj)) {
                    val = Inventory.NAIL;
                } else if ("FLAG".equals(obj)) {
                    val = Inventory.FLAG;
                } else if ("NAILS".equals(obj)) {
                    val = -2;
                } else if ("CUT".equals(obj)) {
                    val = -1;
                }
                lines.add(new SpeechLine(command,
                        new String[]{},
                        new int[]{
                            val
                        }));
            }
        }
        return lines;
    }
}
