/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.tiles;

/**
 *
 * @author Erik
 */
public class Layer {
    public int[] data;
    public int width, height;
    public float opacity;
    public String name;
    public int[][] grid;

    public Layer() {
        data = new int[0];
        name ="";
    }
    
    public void convertToGrid(){
        grid = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = data[i + j * width] - 1;
            }
        }
    }
    
}
