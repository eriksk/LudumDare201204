/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.particles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.particles.Particle;
import se.offbeatgames.ld48lib.particles.ParticleManager;
import se.offbeatgames.ld48lib.utilities.ListUtils;
import se.offbeatgames.ld48lib.utilities.Pool;
import se.offbeatgames.ld48lib.utilities.Util;

/**
 *
 * @author Erik
 */
public class ParticleManagerImpl extends ParticleManager{

    Image texture;
    SpriteSheet sheet;
    float GRAVITY = 0.0001f;
    
    public ParticleManagerImpl(int width, int height) {
        super(width, height);
    }

    @Override
    public void load(ContentManager content) {
        super.load(content);
        particles = new Pool(1024);
        particles.init(ListUtils.allocate(particles.getCapacity(), Particle.class));
        texture = content.loadImage("gfx/particles.png");
        sheet = new SpriteSheet(texture, 8, 8);
    }
    
    public void cutTree(float x, float y){
        for (int i = 0; i < 12; i++) {
            Particle p = (Particle)particles.pop();
            p.current = 0f;
            p.duration = Util.rand(200f, 500f);
            p.srcCol = 0;
            p.srcRow = 0;
            p.x = x;
            p.y = y;
            p.vx = Util.rand(-1f, 1f) * 0.05f;
            p.vy = Util.rand(-1f, 1f) * 0.05f;
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        
        for (int i = 0; i < particles.getCount(); i++) {
            Particle p = (Particle)particles.get(i);
            p.current += dt;
            if(p.current > p.duration){
                particles.push(i--);
            }else{
                p.vy += GRAVITY * dt;
                p.x += p.vx * dt;
                p.y += p.vy * dt;
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
        sheet.startUse();
        for (int i = 0; i < particles.getCount(); i++) {
            Particle p = (Particle)particles.get(i);
            sheet.renderInUse((int)p.x, (int)p.y, p.srcCol, p.srcRow);
        }
        sheet.endUse();
    }
}
