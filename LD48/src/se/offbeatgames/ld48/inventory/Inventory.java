/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.inventory;

import java.util.HashMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import se.offbeatgames.ld48lib.content.ContentManager;

/**
 *
 * @author Erik
 */
public class Inventory {

    public HashMap<Integer, Item> items;
    public static final int TREE = 0;
    public static final int NAIL = 1;
    public static final int FLAG = 2;
    Image texture;

    public Inventory() {
        items = new HashMap<Integer, Item>();
        items.put(TREE, new Item("Wood", 0));
        items.put(NAIL, new Item("Nails", 0));
        items.put(FLAG, new Item("Flag", 0));
    }

    public void load(ContentManager content) {
        texture = content.loadImage("gfx/items.png");
    }

    public void add(int id, int by) {
        items.get(id).count += by;
    }

    public void remove(int id, int by) {
        items.get(id).count -= by;
        if (items.get(id).count < 0) {
            items.get(id).count = 0;
        }
    }

    public void draw(int x, int y, Font font, Graphics g) {
        for (int i = -1; i < 2; i++) {
            texture.draw(x + (i * 32), y, x + (i * 32) + 32, y + 32, 0, 0, 32, 32);
        }
        texture.draw(x - 32, y, x, y + 32, 32, 0, 64, 32);
        font.drawString(x - 32, y + 32, "" + items.get(TREE).count);
        texture.draw(x, y, x + 32, y + 32, 64, 0, 64 + 32, 32);
        font.drawString(x, y + 32, "" + items.get(NAIL).count);
        texture.draw(x + 32, y, x + 64, y + 32, 96, 0, 96 + 32, 32);
        font.drawString(x + 32, y + 32, "" + items.get(FLAG).count);
    }
}