package Client;

import java.io.*;
import java.net.*;
 
public class Client implements Runnable {
 
    public static Socket socket = null;
    public static Thread t1;
    public static String AdresseIpServeur = "127.0.0.1";
    
     
    
    public Client(String ip){
	     AdresseIpServeur = ip;
	    }
    
    @Override
	public void run() {

	     
        
	    try {
	         
	        System.out.println("Demande de connexion");
	        socket = new Socket(AdresseIpServeur,2009);
	        System.out.println("Connexion �tablie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connect�
	         
	        t1 = new Thread(new Connexion(socket));
	        t1.start();
	         
	         
	         
	    } catch (UnknownHostException e) {
	      System.err.println("Impossible de se connecter � l'adresse "+socket.getLocalAddress());
	    } catch (IOException e) {
	      System.err.println("Aucun serveur � l'�coute du port "+socket.getLocalPort());
	    }
	     
	}
 
}