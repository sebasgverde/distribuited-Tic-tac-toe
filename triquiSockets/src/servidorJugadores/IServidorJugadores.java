/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorJugadores;

import java.rmi.*;
import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public interface IServidorJugadores extends Remote{

  public ArrayList<Peer> listarPeers() throws RemoteException;

  public Peer buscarPeer() throws RemoteException;

  public boolean agregarPeer(Peer p) throws RemoteException;

  public boolean eliminarPeer(Peer p) throws RemoteException;

}