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

public class ServidorJugadores implements IServidorJugadores {

  public ArrayList<Peer> PeerServers;

    /**
   * 
   * @element-type Peer
   */

  public ArrayList<Peer> listarPeers() {
  return null;
  }

  public Peer buscarPeer() {
  return null;
  }

  public boolean agregarPeer() {
  return false;
  }

  public boolean eliminarPeer() {
  return false;
  }

}