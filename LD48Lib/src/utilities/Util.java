/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Random;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Erik
 */
public class Util {

    //TODO: Ovelook references...
    
    private static Random rand;
    static {
        rand = new Random(System.currentTimeMillis());
    }
    static Vector2f temp = new Vector2f();
    
    public static float AngleFrom(Vector2f point1, Vector2f point2) {
        return (float) Math.atan2(point2.y - point1.y, point2.x - point1.x);
    }
    
    public static float AngleFrom(Vector2f v) {
        return (float) Math.atan2(v.y, v.x);
    }

    public static Vector2f AngleFrom(float angle) {
        temp.x = (float) Math.cos(angle);
        temp.y = (float) Math.sin(angle);
        return temp;
    }
    
    public static Vector2f DirectionFrom(Vector2f point1, Vector2f point2) {
        float angle = AngleFrom(point1, point2);
        Vector2f direction = new Vector2f((float) Math.cos(angle), (float) Math.sin(angle));
        direction.normalise();
        return direction;
    }
    
    public static void SetSeed(int seed) {
        rand = new Random(seed);
    }
    
    public static float Lerp(float value1, float value2, float weight) {
        return value1 + (value2 - value1) * weight;
    }
    
    public static float Rand(float max) {
        return (float) rand.nextFloat() * max;
    }
    
    public static float Rand(float min, float max) {
        return (float) rand.nextFloat() * (max - min) + min;
    }
    
    public static int Rand(int max) {
        return rand.nextInt(max);
    }
    
    public static int Rand(int min, int max) {
        return min + (rand.nextInt(max) - min);
    }
    
    public static float clamp(float value, float min, float max){
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }
    
    public static float distance(float x1, float y1, float x2, float y2){
        float x = x1 - x2;
        float y = y1 - y2;
        return (float)Math.sqrt((double)(x * x + y * y));
    }
}
