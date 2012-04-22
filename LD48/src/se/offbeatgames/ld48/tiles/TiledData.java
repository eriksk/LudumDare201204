/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48.tiles;

/**
 *
 * @author Erik
 */
public class TiledData {

    public int width, height, tileWidth, tileHeight;
    public Layer[] layers;
    
    public TiledData(int width, int height, int tileWidth, int tileHeight, Layer[] layers) {
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.layers = layers;
    }
}
