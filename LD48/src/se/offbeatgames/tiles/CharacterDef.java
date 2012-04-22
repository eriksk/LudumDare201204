/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.tiles;

import se.offbeatgames.ld48.speech.SpeechTree;

/**
 *
 * @author Erik
 */
public class CharacterDef {
    public int type, col, row;
    public SpeechTree tree;

    public CharacterDef(int type, SpeechTree tree, int col, int row) {
        this.type = type;
        this.tree = tree;
        this.col = col;
        this.row = row;
    }
}
