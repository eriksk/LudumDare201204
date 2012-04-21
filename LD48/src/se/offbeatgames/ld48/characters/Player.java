/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import org.lwjgl.input.Keyboard;
import se.offbeatgames.ld48.inventory.Inventory;
import se.offbeatgames.ld48lib.animations.Animation;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.input.InputManager;
import se.offbeatgames.tiles.MapTiles;

/**
 *
 * @author Erik
 */
public class Player extends GameCharacter {

    public int state = 0;
    public static final int FREE = 0;
    public static final int TALKING = 1;
    public int dir = 0;
    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    public Inventory inventory;

    public Player() {
        super();
    }

    @Override
    public void load(ContentManager content) {
        super.load(content);
        inventory = new Inventory();
        inventory.load(content);
        animations.put("idle", new Animation(new int[]{0}, 500f));
        animations.put("walk", new Animation(new int[]{1, 2}, 200f));
        setAnim("idle");
    }

    @Override
    public void update(float dt, MapTiles map) {
        super.update(dt, map);

        float speed = 0.1f;
        boolean walking = false;
        if (InputManager.I().isKeyDown(Keyboard.KEY_LEFT)) {
            if (!map.collides(x - 1, y + 8)) {
                x -= speed * dt;
                walking = true;
            }
            dir = LEFT;
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_RIGHT)) {
            if (!map.collides(x + 16, y + 8)) {
                x += speed * dt;
                walking = true;
            }
            dir = RIGHT;
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_UP)) {
            if (!map.collides(x + 8, y - 1)) {
                walking = true;
                y -= speed * dt;
            }
            dir = UP;
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_DOWN)) {
            if (!map.collides(x + 8, y + 16)) {
                y += speed * dt;
                walking = true;
            }
            dir = DOWN;
        }

        if (InputManager.I().isKeyClicked(Keyboard.KEY_SPACE)) {
            if (dir == UP) {
                map.cutDownTree(x + 8, y - 8);
            } else if (dir == DOWN) {
                map.cutDownTree(x + 8, y + 16);
            } else if (dir == LEFT) {
                map.cutDownTree(x - 8, y + 8);
            } else if (dir == RIGHT) {
                map.cutDownTree(x + 16, y + 8);
            }
        }

        if (walking) {
            setAnim("walk");
        } else {
            setAnim("idle");
        }
    }

    @Override
    public void draw() {
        super.draw();
    }
}
