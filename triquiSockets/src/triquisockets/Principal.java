
import java.io.BufferedReader;
import triquisockets.TriquiPlayerSocket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    ServerSocket ss = null;
    Socket c = null;
    int port = 0;

    public Principal() {
        init(5555);
    }

    public Principal(int port) {
        init(port);
    }

    private void init(int port) {
        try {
            this.port = port;
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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
        Principal ts = null;
        TriquiPlayerSocket t = null;
        
        ClienteServidorJugadores clienServ = new ClienteServidorJugadores();
        
        int caso = Integer.parseInt(keyboard());
        if(caso == 1)
        {
            if (args.length < 1)
            {
                ts = new Principal();
                boolean a = clienServ.agregarPeer("juan", "5555");
            }
            else
                ts = new Principal(Integer.parseInt(args[0]));
            ts.run();
        }
        else
        {
            t = new TriquiPlayerSocket("192.168.56.1", 5555);
            t.run();                        
        }
    }
}