/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.ui;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48.Game;

/**
 *
 * @author Erik
 */
public class TextBox {

    protected String text;
    protected List<String> lines;
    protected int width, height;
    protected Color bgColor, borderColor;
    protected Font font;
    protected int currentLine;
    float x, y;
    int margin;

    public TextBox(Font font) {
        lines = new ArrayList<String>();
        width = 420;
        height = 60;
        x = (512 - width) / 2;
        y = Game.height - (height + 4);
        margin = 8;
        bgColor = new Color(0f, 0f, 0f, 0.8f);
        borderColor = Color.orange;
        this.font = font;
    }

    public void setText(String text) {
        this.text = text;
        lines.clear();

        String[] words = text.split(" ");
        String line = "";
        for (int i = 0; i < words.length; i++) {
            if (font.getWidth(line + words[i] + "...") > width - margin) {
                lines.add(line);
                line = "";
            }
            line += words[i] + " ";
        }
        lines.add(line);
    }
    
    public void reset(){
        currentLine = 0;
    }

    public void step() {
        currentLine += 2;
        //TODo: look for script stuff
        if (currentLine >= lines.size()) {
            reset();
            // TODO: close
        }
    }

    public void draw(Graphics g, Font font) {
        g.setColor(bgColor);
        g.fillRect(x, y, width, height);
        g.setColor(borderColor);
        g.drawRect(x, y, width, height);

        font.drawString(x + margin, y + margin, lines.get(currentLine));
        if (currentLine != lines.size() - 1) {
            font.drawString(x + margin, y + font.getHeight(lines.get(currentLine)) + margin, lines.get(currentLine + 1));
        }
    }
}
