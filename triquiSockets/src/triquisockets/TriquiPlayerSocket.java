package triquisockets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emontoya
 */
public class TriquiPlayerSocket {
    
    TriquiSocketClient triqui = null;
    
    public TriquiPlayerSocket(String host, int port) {
        triqui = new TriquiSocketClient(host,port);
    }
    
    private String keyboard() {
        String cadena = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            cadena = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TriquiPlayerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }
    
    private void play() {
        triqui.send("START,");
        String winner = "N";
        String jugadorCliente = "O";
        int contplay = 0;
        boolean valid = false;
        String player = null;
        String board = null;

        //maximo iria hasta 5 por ser el que inicia
        while (winner.equals("N") && contplay < 5) {
            

            /*do
            {*/
                triqui.send("PLAYER,");
                player = triqui.recv();





                triqui.send("BOARD,");
                board = triqui.recv();




                System.out.println("\nTURNO: " + player);
                System.out.println(board);
                
            do 
            {
                System.out.print("Posicion = ");
                String pos = keyboard();

                //valid = triqui.play(Integer.parseInt(pos)-1); // Standalone
                
                // comunicacion socket - inicio
                
                triqui.send("PLAY,"+pos);
                valid = Boolean.parseBoolean(triqui.recv());
                
                
                
                
                // comunicacion socket - fin
                
                if (!valid)
                    System.out.println(">>> Jugada invalida");
            } while (!valid);
            
            triqui.send("TESTWINNER,");
            winner = triqui.recv();
            contplay++;
            
            triqui.send("PLAYER,");
            player = triqui.recv();
            triqui.send("BOARD,");
            board = triqui.recv();
            System.out.println("\nTURNO: " + player);
            System.out.println(board);
            
            triqui.send("CEDERTURNO,");
            
            triqui.send("TESTWINNER,");
            System.out.println("Esperando que el otro jugador haga la jugada");
            winner = triqui.recv();
        }
        
        triqui.send("BOARD,");
        board = triqui.recv();
        
        System.out.println(board);
        System.out.println("Ganador: " + winner);
        triqui.send("CEDERTURNO,");

    }
    
    public void run() {
        String input = "y";
        while (input.equals("y")) {
            play();
            System.out.print("Continuar? (y/n) = ");
            input = keyboard();
        }
        triqui.send("QUIT,");
    }
    
}
