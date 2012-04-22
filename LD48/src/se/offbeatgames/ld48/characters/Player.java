/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.characters;

import java.util.List;
import org.lwjgl.input.Keyboard;
import se.offbeatgames.ld48.inventory.Inventory;
import se.offbeatgames.ld48.particles.ParticleManagerImpl;
import se.offbeatgames.ld48.speech.SpeechLine;
import se.offbeatgames.ld48.speech.SpeechTree;
import se.offbeatgames.ld48.ui.Gui;
import se.offbeatgames.ld48lib.animations.Animation;
import se.offbeatgames.ld48lib.audio.AudioManager;
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
    public boolean canCutTrees;
    private SpeechTree npcSpeech;

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
        canCutTrees = false;
        
        //TODO: REMOVE DEBUG CODE!
        canCutTrees = true;
        inventory.add(Inventory.TREE, 100);
        inventory.add(Inventory.FLAG, 1);
        inventory.add(Inventory.NAIL, 200);
    }

    public void setConversation(SpeechTree speech) {
        state = Player.TALKING;
        this.npcSpeech = speech;
        Gui.setText(speech.getAllText());
        npcSpeech.getNext();
    }

    public void update(float dt, MapTiles map, ParticleManagerImpl pMan, List<GameCharacter> characters) {
        super.update(dt, map);

        if (state == FREE) {
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

            // boundaries
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            int width = map.getCollisionLayer().width;
            int height = map.getCollisionLayer().height;
            if (x + 16 > width * 16) {
                x = (width * 16) - 16;
            }
            if(y + 16 > height * 16){
                y = (height * 16) - 16;
            }

            if (canCutTrees) {
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
            }
            if (walking) {
            } else {
                animations.get(currentAnim).reset();
            }

            if (InputManager.I().isKeyClicked(Keyboard.KEY_SPACE)) {
                // check for nearby people
                for (int i = 0; i < characters.size(); i++) {
                    NPC c = (NPC) characters.get(i);
                    if (intersects(c)) {
                        setConversation(c.tree);
                    }
                }
            }
        } else if (state == TALKING) {
            animations.get(currentAnim).reset();
            if (InputManager.I().isKeyClicked(Keyboard.KEY_SPACE)) {

                if (!Gui.textBox.step()) {
                    SpeechLine line = npcSpeech.getNext();
                    while (line != null) {
                        line.run(this);
                        line = npcSpeech.getNext();
                    }
                    Gui.setText("");
                    state = FREE;
                }
            }
        }
    }

    public void treeGotCut() {
        Gui.log("1 Wood added to inventory");
        inventory.add(Inventory.TREE, 1);
        AudioManager.I().playSound("cut");
    }

    @Override
    public void draw() {
        super.draw();
    }
}
