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
    
    public TriquiSocketServer(Socket c) {
        this.c = c;
        open();
        triqui = new TriquiGame();
        System.out.println("Nueva conexion...");
    }
    
        public void run() {
        try {
            String cmd = recvRequest();
            System.out.println("Comando: "+cmd);
            String response;
            while (!cmd.equals("QUIT")) {
                if (cmd.equals("START")) {
                    triqui.Start();
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
                cmd = recvRequest();
                System.out.println("Comando: "+cmd);
            }
            c.close();
            c = null;
        } catch (IOException ex) {
            Logger.getLogger(TriquiSocketServer.class.getName()).log(Level.SEVERE, null, ex);
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
