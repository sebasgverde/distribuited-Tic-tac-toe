
import java.math.BigInteger;
import java.rmi.*;

public interface Factoriel extends Remote {
    long compute(long arg) throws RemoteException; 
    String saludo(String nombre) throws RemoteException;     
}


