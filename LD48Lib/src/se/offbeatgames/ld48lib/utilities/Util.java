/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.utilities;

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

    public static float angleFrom(Vector2f point1, Vector2f point2) {
        return (float) Math.atan2(point2.y - point1.y, point2.x - point1.x);
    }

    public static float angleFrom(Vector2f v) {
        return (float) Math.atan2(v.y, v.x);
    }

    public static Vector2f angleFrom(float angle) {
        temp.x = (float) Math.cos(angle);
        temp.y = (float) Math.sin(angle);
        return temp;
    }

    public static Vector2f directionFrom(Vector2f point1, Vector2f point2) {
        float angle = angleFrom(point1, point2);
        Vector2f direction = new Vector2f((float) Math.cos(angle), (float) Math.sin(angle));
        direction.normalise();
        return direction;
    }

    public static void setSeed(int seed) {
        rand = new Random(seed);
    }

    /**
     * Linear interpolation
     * @param start
     * @param end
     * @param weight
     * @return 
     */
    public static float lerp(float start, float end, float weight) {
        return start + (end - start) * weight;
    }

    /**
     * Cubic interpolation
     * @param start
     * @param end
     * @param amount
     * @return 
     */
    public static float qLerp(float start, float end, float weight) {
        weight = (weight > 1f) ? 1f : weight;
        weight = (weight < 0f) ? 0f : weight;
        weight = (weight * weight) * (3f - (2f * weight));
        return (start + ((end - start) * weight));
    }

    public static float rand(float max) {
        return (float) rand.nextFloat() * max;
    }

    public static float rand(float min, float max) {
        return (float) rand.nextFloat() * (max - min) + min;
    }

    public static int rand(int max) {
        return rand.nextInt(max);
    }

    public static int rand(int min, int max) {
        return min + (rand.nextInt(max) - min);
    }

    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static float distance(float x1, float y1, float x2, float y2) {
        float x = x1 - x2;
        float y = y1 - y2;
        return (float) Math.sqrt((double) (x * x + y * y));
    }
}
