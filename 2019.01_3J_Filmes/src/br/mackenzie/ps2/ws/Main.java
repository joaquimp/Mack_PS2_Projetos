package br.mackenzie.ps2.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquim Pessôa Filho
 * 
 */
public class Main {
    public static void main(String bananas[]){
        Servidor s = new Servidor();
        try {
            s.run(new String[]{"server"});
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
