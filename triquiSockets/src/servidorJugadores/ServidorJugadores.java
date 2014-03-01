/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorJugadores;

import java.util.ArrayList;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

/**
 *
 * @author sebastian
 */

public class ServidorJugadores extends UnicastRemoteObject implements IServidorJugadores {

  public ArrayList<Peer> peerServers;

    /**
   * 
   * @element-type Peer
   */
  
  ServidorJugadores() throws java.rmi.RemoteException
  {
      super();
      peerServers = new ArrayList<Peer>();
  }

  public ArrayList<Peer> listarPeers() {
  return peerServers;
  }

  public Peer buscarPeer() {
  return null;
  }

  public boolean agregarPeer(Peer p) {
      boolean band = false;
      try {
          peerServers.add(p);
          System.out.println(p.usuario + " "+p.ip + " "+  p.puerto);
          band = true;
      } catch (Exception e) {
      }
    return band;
  }

  public boolean eliminarPeer(Peer p) {
      boolean band = false;
      try {
          peerServers.add(p);
          band = true;
      } catch (Exception e) {
      }
    return band;
  }
  
    public static void main(String args[]) { 
      try {
          ServidorJugadores servJug;
          LocateRegistry.createRegistry(1099);
          servJug=new ServidorJugadores(); 
          Naming.bind("ElServidorJugadores", servJug); 
          System.out.println("El servidor esta listo\n");
      }
      catch (Exception e){
          e.printStackTrace();
      }
    } 

}