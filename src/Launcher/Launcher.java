package Launcher;

import java.io.IOException;
import java.net.ServerSocket;

import Serveur.*;
import Client.*;

public class Launcher {

    public static Thread t;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try {
			t = new Thread(new Serveur(new ServerSocket(2009)));
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t = new Thread(new Client("127.0.0.1"));
		t.start();
	}

}
