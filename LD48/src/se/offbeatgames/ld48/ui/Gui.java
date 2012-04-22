/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.ui;

import java.util.ArrayList;
import java.util.List;
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

    public static TextBox textBox;
    public static Font font;
    public static List<String> log;
    private static float logCurrent, logInterval = 1000;

    public static void load(ContentManager content) {
        font = content.loadFont("fonts/font");
        textBox = new TextBox(font);
        log = new ArrayList<String>();
    }

    public static void log(String message) {
        log.add(message);
        if (log.size() > 5) {
            log.remove(0);
        }
    }

    public static void setText(String text) {
        textBox.setText(text);
    }

    public static void update(float dt) {

        if (log.size() > 0) {
            logCurrent += dt;
            if (logCurrent > logInterval) {
                logCurrent = 0f;
                log.remove(0);
            }
        } else {
            logCurrent = 0f;
        }

    }

    public static void draw(Graphics g) {
        for (int i = 0; i < log.size(); i++) {
            font.drawString(16, 24 + (i * 18), log.get(i));
        }
        if (!"".equals(textBox.text)) {
            textBox.draw(g, font);
        }
    }
}
