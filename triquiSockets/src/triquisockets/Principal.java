
package triquisockets;

import java.io.BufferedReader;
import triquisockets.TriquiPlayerSocket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
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
        
        System.out.println("Ingrese su usuario");
        String usuario = keyboard();
        
        System.out.println("Conoce una ip?");
        if(keyboard().equals("y"))
        {
            System.out.println("Ingrese ip");
            String ip = keyboard();
            System.out.println("Ingrese puerto");
            int puerto = Integer.parseInt(keyboard());

            t = new TriquiPlayerSocket(ip, puerto);
            t.run();   
        }
        else
        {
            ClienteServidorJugadores clienServ = new ClienteServidorJugadores();
            clienServ.listarPeers();

            int caso;
            if(clienServ.hayServidores())
            {
                System.out.println("Si desea ser peer servidor ingrese 1");
                caso = Integer.parseInt(keyboard());
            }
            else
            {
                System.out.println("Como no hay ningun servidor no puede ser cliente");
                caso = 1;
            }
        
            if(caso == 1)
            {
                System.out.println("Ingrese el puerto por el que va a atender");//normalmente 5555
                String puerto = keyboard();

                ts = new Principal(Integer.parseInt(puerto));
                boolean a = clienServ.agregarPeer(usuario, puerto);

                ts.run();
            }
            else
            {

                    System.out.println("ingrese busqueda");
                    String dato = clienServ.buscarServer(keyboard());
                    if(dato.equals(""))
                        System.out.println("no se encontro");
                    else
                    {
                        String [] datos = dato.split(",");
                                        t = new TriquiPlayerSocket(datos[0], Integer.parseInt(datos[1]));
                t.run();    
                        
                    }
                    
 
                /*clienServ.listarPeers();

                System.out.println("Ingrese el numero del peer servidor con el que quiere jugar");
                int numServidor = Integer.parseInt(keyboard());

                String ip = clienServ.getIpServer(numServidor);
                int puerto = clienServ.getPuertoServer(numServidor);

                t = new TriquiPlayerSocket(ip, puerto);
                t.run();                        */
            }
        }
    }
}