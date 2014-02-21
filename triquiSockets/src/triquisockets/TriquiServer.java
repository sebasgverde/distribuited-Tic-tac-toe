
import java.io.BufferedReader;
import triquisockets.TriquiPlayerSocket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TriquiServer {

    ServerSocket ss = null;
    Socket c = null;
    int port = 0;

    public TriquiServer() {
        init(5555);
    }

    public TriquiServer(int port) {
        init(port);
    }

    private void init(int port) {
        try {
            this.port = port;
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(TriquiServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run() {
        TriquiSocketServer tss = null;
        System.out.println("TriquiServer.run().listening..." + port);
        while (true) {
            try {
                System.out.println("otro socket");
                c = ss.accept();
            } catch (Exception e) {
                e.printStackTrace();
            }
            tss = new TriquiSocketServer(c);
            //tss.run();
            tss.start();
        }
    }
    
            private static String keyboard() {
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

    public static void main(String[] args) {
    //public void mainServidor(String[] args) {
        TriquiServer ts = null;
        TriquiPlayerSocket t = null;
        
                        int caso = Integer.parseInt(keyboard());
                if(caso == 1)
                {
                    if (args.length < 1)
                    {
                        ts = new TriquiServer();
                    }
                    else
                        ts = new TriquiServer(Integer.parseInt(args[0]));
                    ts.run();
                }
                else
                {
                    t = new TriquiPlayerSocket("localhost", 5555);
                    t.run();
                          
                }
                

    }
}