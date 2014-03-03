/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triquisockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class ClienteLocal {
    
    TriquiSocketServer triqui;
    public String player = null;
    public String board = null;
    public String winner = "N";
    public int contplay = 0;
    
    public ClienteLocal(TriquiSocketServer tr) {
        triqui = tr;
    }
    
    public void aumentarContPlay()
    {
        contplay++;
    }

    
    private String keyboard() {
        String cadena = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            cadena = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TriquiSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }
   
    public void imprimirCosas()
    {
        System.out.println("\nTURNO: " + player);
        System.out.println(board);
    }
    
    public String leerJugada()
    {

        System.out.println("\nTURNO: " + player);
        System.out.println(board);

        System.out.print("Posicion = ");
        String pos = keyboard();  

        return pos;
    }
             
    public void run() {
        String input = "y";
        while (input.equals("y")) {
            System.out.print("Continuar? (y/n) = ");
            input = keyboard();
        }
    }
    
}
