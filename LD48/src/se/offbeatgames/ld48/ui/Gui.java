/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.ui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.input.InputManager;

/**
 *
 * @author Erik
 */
public class Gui {

    private TextBox text;
    public Font font;

    public Gui() {
    }

    public void load(ContentManager content) {   
        font = content.loadFont("fonts/font");
        text = new TextBox(font);
        text.setText("Hello.. This is a box with text in it. I should probably do some wrapping here... Otherwise it will be too much.");
    }

    public void update(float dt) {
        if(InputManager.I().isKeyClicked(Keyboard.KEY_SPACE)){
            text.step(); 
        }
    }

    public void draw(Graphics g) {
        text.draw(g, font);
    }
}
