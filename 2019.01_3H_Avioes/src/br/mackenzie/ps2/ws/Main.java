/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.ps2.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaquim
 */
public class Main {
    public static void main(String args[]) {
        Servidor s = new Servidor();
        try {
            s.run(new String[]{ "server" });
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "deu ruim", ex);
        }
    }
}
