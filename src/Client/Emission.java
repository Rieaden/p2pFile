package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
 
 
public class Emission implements Runnable {
 
    private PrintWriter out;
    private String login = null, message = null;
    private Scanner sc = null;
     
    public Emission(PrintWriter out) {
        this.out = out;
         
    }
 
     
    public void run() {
         
           
          while(true){
                System.out.println("Votre message :");
               // message = sc.nextLine();
                out.println(message);
                out.flush();
              }
    }
}