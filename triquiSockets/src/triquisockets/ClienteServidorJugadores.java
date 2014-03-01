/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.Inet4Address;
import java.rmi.*;
import java.rmi.registry.*;
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
    
        public ClienteServidorJugadores()
    {
        try {
            servJug=(IServidorJugadores)Naming.lookup("rmi://localhost/ElServidorJugadores");
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
    

}

