/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import triquisockets.TriquiSocketClient;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emontoya
 */
public class TriquiSocketServer extends Thread{
    
    Socket c = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    TriquiGame triqui = null;
    String[] command = null; // variable donde recibe un comando con sus argumentos
    ClienteLocal clienteLoc;
    
    public TriquiSocketServer(Socket c) {
        this.c = c;
        open();
        triqui = new TriquiGame();
        clienteLoc = new ClienteLocal(this);
        System.out.println("Nueva conexion...");
    }
    
        public void run() {
        try {
            String cmd = recvRequest();
            System.out.println("Comando: "+cmd);
            String response  = "";
            atendiendoRemoto(cmd, response);
            c.close();
            c = null;
        } catch (IOException ex) {
            Logger.getLogger(TriquiSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void atendiendoRemoto(String cmd, String response)
    {
          while (!cmd.equals("QUIT")) {
            if (cmd.equals("START")) {
                triqui.Start();
                
                clienteLoc.contplay = 0;
                
                clienteLoc.player = triqui.Player();
                clienteLoc.board = triqui.Board();

                clienteLoc.imprimirCosas();
            
            } else if (cmd.equals("PLAY")) {
                int pos = Integer.parseInt(command[1])-1;
                boolean res = triqui.Play(pos);
                response = Boolean.toString(res);
                sendResponse(response);
            } else if (cmd.equals("PLAYER")) {
                response = triqui.Player();
                sendResponse(response);
            } else if (cmd.equals("BOARD")) {
                response = triqui.Board();
                sendResponse(response);
            } else if (cmd.equals("TESTWINNER")) {
                response = triqui.TestWinner();
                sendResponse(response);
            }
            else if(cmd.equals("CEDERTURNO"))
            {
                atenderLocal();
            }

            cmd = recvRequest();
           // System.out.println("Comando: "+cmd);
            
        }
    }
    
    public void atenderLocal()
    {
        clienteLoc.winner = triqui.TestWinner();
        String winner = clienteLoc.winner;
        int contplay = clienteLoc.contplay;
        
        if (winner.equals("N") && contplay < 4) 
        {
            boolean valid;

            clienteLoc.player = triqui.Player();
            clienteLoc.board = triqui.Board();
                             
            //clienteLoc.imprimirCosas();
            do
            {
        
                            
            int pos = Integer.parseInt(clienteLoc.leerJugada());
            valid = triqui.Play(pos-1);
                if (!valid)
                   System.out.println(">>> Jugada invalida");
            } while (!valid);
            
            clienteLoc.winner = triqui.TestWinner();
            
            clienteLoc.aumentarContPlay();
            
            clienteLoc.player = triqui.Player();
            clienteLoc.board = triqui.Board();
            clienteLoc.imprimirCosas();
            
            System.out.println("Esperando que el otro jugador haga la jugada");
           // contplay++;
        }
        else
        {
            clienteLoc.board = triqui.Board();
            System.out.println(clienteLoc.board);
            System.out.println("Ganador: " + winner);
        }
    }

    private String recvRequest() {
        String msg = null;
        try {
            msg = dis.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(TriquiSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        command = msg.split(",");
        return command[0];
    }
    
    private void sendResponse(String msg) {
        try {
            dos.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(TriquiSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void open() {
        try {
            dis = new DataInputStream(c.getInputStream());
	    dos = new DataOutputStream(c.getOutputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(TriquiSocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TriquiSocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
