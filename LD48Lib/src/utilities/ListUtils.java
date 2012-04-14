/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik
 */
public class ListUtils {
 
    public static List allocate(int count, Class c){
        List list = new ArrayList();
        for (int i = 0; i < count; i++) {
            try {
                list.add(c.newInstance());
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
        }
        return list;
    }
}
