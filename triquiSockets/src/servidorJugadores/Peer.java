/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorJugadores;

import java.io.Serializable;

/**
 *
 * @author sebastian
 */
public class Peer implements Serializable{

  public String usuario;

  public String ip;

  public String puerto;
  
  public Peer(String u, String i, String p)
  {
      usuario = u;
      ip = i;
      puerto = p;
      
  }
  


}