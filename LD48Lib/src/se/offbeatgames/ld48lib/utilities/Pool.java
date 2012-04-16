/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.ld48lib.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik
 */
public class Pool {

    private int count;
    private int capacity;
    private List<Object> items;

    public Pool(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        items = new ArrayList<Object>(capacity);
    }
    
    public void clear(){
        count = 0;
    }

    public void init(List<Object> entities) {
        for (int i = 0; i < entities.size(); i++) {
            items.add(entities.get(i));
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return count;
    }

    public List<Object> getItems() {
        return items;
    }

    public Object get(int index) {
        return items.get(index);
    }

    public void push(int index) {
        Object temp = items.get(index);
        items.set(index, items.get(count - 1));
        items.set(count - 1, temp);
        count--;
    }

    public Object pop() {
        Object i = items.get(count++);
        return i;
    }
}
