import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client{
    
     private static String keyboard() {
        String cadena = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            cadena = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }
     
    public static void main(String args[]){
	Factoriel calculator;
      int number;
	try {
	    calculator=(Factoriel)Naming.lookup("rmi://localhost/TheCalculator");
          //number=Integer.parseInt(args);
            
            System.out.println("Ingrese su nombre");
            System.out.println(calculator.saludo(keyboard()));
            do
            {
                System.out.println("Ingrese un numero");
                number = Integer.parseInt(keyboard());
                System.out.println();
                System.out.println( "   "+number+"! = "+calculator.compute(number));
                System.out.println();
            }while(number != 15);
	}
	catch (Exception e){
	    e.printStackTrace();
	}
    }
}

