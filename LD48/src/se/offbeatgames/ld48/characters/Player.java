/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import org.lwjgl.input.Keyboard;
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

    public Player() {
        super();
    }

    @Override
    public void load(ContentManager content) {
        super.load(content);
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
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_RIGHT)) {
            if (!map.collides(x + 16, y + 8)) {
                x += speed * dt;
                walking = true;
            }
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_UP)) {
            if (!map.collides(x + 8, y - 1)) {
                walking = true;
                y -= speed * dt;
            }
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_DOWN)) {
            if (!map.collides(x + 8, y + 16)) {
                y += speed * dt;
                walking = true;
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
