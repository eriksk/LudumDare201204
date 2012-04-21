/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import org.lwjgl.input.Keyboard;
import se.offbeatgames.ld48.inventory.Inventory;
import se.offbeatgames.ld48.particles.ParticleManagerImpl;
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
        animations.put("walk_down", new Animation(new int[]{1, 0, 2, 0}, 100f));
        animations.put("walk_up", new Animation(new int[]{9, 8, 10, 8}, 100f));
        animations.put("walk_left", new Animation(new int[]{12, 13, 11, 13}, 100f));
        animations.put("walk_right", new Animation(new int[]{4, 3, 5, 3}, 100f));
        setAnim("walk_down");
    }

    public void update(float dt, MapTiles map, ParticleManagerImpl pMan) {
        super.update(dt, map);

        float speed = 0.1f;
        boolean walking = false;
        if (InputManager.I().isKeyDown(Keyboard.KEY_LEFT)) {
            if (!map.collides(x - 1, y + 8)) {
                x -= speed * dt;
                walking = true;
                setAnim("walk_left");
            }
            dir = LEFT;
        } else if (InputManager.I().isKeyDown(Keyboard.KEY_RIGHT)) {
            if (!map.collides(x + 16, y + 8)) {
                x += speed * dt;
                walking = true;
                setAnim("walk_right");
            }
            dir = RIGHT;
        } else if (InputManager.I().isKeyDown(Keyboard.KEY_UP)) {
            if (!map.collides(x + 8, y - 1)) {
                walking = true;
                y -= speed * dt;
                setAnim("walk_up");
            }
            dir = UP;
        } else if (InputManager.I().isKeyDown(Keyboard.KEY_DOWN)) {
            if (!map.collides(x + 8, y + 16)) {
                y += speed * dt;
                walking = true;
                setAnim("walk_down");
            }
            dir = DOWN;
        }

        if (InputManager.I().isKeyClicked(Keyboard.KEY_SPACE)) {
            if (dir == UP) {
                if (map.cutDownTree(x + 8, y - 8, pMan)) {
                    treeGotCut();
                }
            } else if (dir == DOWN) {
                if (map.cutDownTree(x + 8, y + 16, pMan)) {
                    treeGotCut();
                }
            } else if (dir == LEFT) {
                if (map.cutDownTree(x - 8, y + 8, pMan)) {
                    treeGotCut();
                }
            } else if (dir == RIGHT) {
                if (map.cutDownTree(x + 16, y + 8, pMan)) {
                    treeGotCut();
                }
            }
        }
        if (walking) {
        } else {
            animations.get(currentAnim).reset();
        }
    }

    public void treeGotCut() {
        inventory.add(Inventory.TREE, 1);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
