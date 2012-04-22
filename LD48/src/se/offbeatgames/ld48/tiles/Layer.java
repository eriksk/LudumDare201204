/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.tiles;

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

    public Layer(int[] data, int width, int height, float opacity, String name) {
        this.data = data;
        this.width = width;
        this.height = height;
        this.opacity = opacity;
        this.name = name;
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
