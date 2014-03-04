/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triquisockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorJugadores.*;
import sun.security.krb5.internal.PAData;

/**
 *
 * @author sebastian
 */
public class ClienteServidorJugadores {
    
    IServidorJugadores servJug;
    ArrayList<Peer> servers;
    
        private static String leerHost() {
            String cadena = null;
            try
            {
                FileInputStream fstream = new FileInputStream("hostRmi.txt");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                cadena = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(TriquiPlayerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cadena;
        }
    
        public ClienteServidorJugadores()
    {
        try {
            //System.out.println("rmi://"+leerHost()+"ElServidorJugadores");
            servJug=(IServidorJugadores)Naming.lookup("rmi://"+leerHost()+"/ElServidorJugadores");           
            //servJug=(IServidorJugadores)Naming.lookup("rmi://sistemas.eafit.edu.co/ElServidorJugadores");
          //number=Integer.parseInt(args);

                System.out.println();
                //System.out.println( "   "+number+"! = "+calculator.compute(number));
                System.out.println();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean agregarPeer(String nombre, String puerto)
    {
        try {
            Peer myPeer = new Peer( nombre,Inet4Address.getLocalHost().getHostAddress(), puerto);
            return servJug.agregarPeer(myPeer);
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean hayServidores()
    {
        return (servers.size() > 0);
    }
    
    public String buscarServer(String user)
    {
        try {
            Peer nuePeer = servJug.buscarPeer(user);
            if(nuePeer != null)
                return (nuePeer.ip + "," + nuePeer.puerto);
            else
                return "";
        } catch (Exception e) {
            return "";
        }
    }
    
    public void listarPeers()
    {
        try {
            servers = servJug.listarPeers();
        } catch (Exception e) {
        }

    }
    
    public void imprimirPeers()
    {          
        listarPeers();
        for(int i = 0; i < servers.size(); i++)
        {
            System.out.println( i + " " + servers.get(i).usuario);
        }
    }
    
    public String getIpServer(int ind)
    {
        return (servers.get(ind).ip);
    }
    
    public int getPuertoServer(int ind)
    {
        return (Integer.parseInt(servers.get(ind).puerto));
    }
}

