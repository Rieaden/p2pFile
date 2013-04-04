package Serveur;

import java.io.*;
import java.net.*;

public class Serveur implements Runnable {
	public static ServerSocket ss = null;
	public static Thread t;
	public static ServerSocket socketserver = null;


	 public Serveur(ServerSocket ss){
	     socketserver = ss;
	    }
	     

	@Override
	public void run() 
	{
		ss = socketserver;
		System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());

		t = new Thread(new Accepter_connexion(ss));
		t.start();
	}
	
	public static File[] getListeFichiersServeur()
    {
    	File[] listFiles;
    	return listFiles = (new File("./src/Serveur/data")).listFiles();
    }


}