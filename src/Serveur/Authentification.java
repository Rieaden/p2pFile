package Serveur;

import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;
 
public class Authentification implements Runnable {
 
    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String login = "zero", pass =  null;
    public boolean authentifier = false;
    public Thread t2;
     
    public Authentification(Socket s){
         socket = s;
        }
    public void run() {
     
        try {
             
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
             
        while(!authentifier){
             
            out.println("Entrez votre login :");
            out.flush();
            login = in.readLine();
             
             
            out.println("Entrez votre mot de passe :");
            out.flush();
            pass = in.readLine();
 
            if(isValid(login, pass)){
                 
                out.println("connecte");
                System.out.println(login +" vient de se connecter ");
                out.flush();
                authentifier = true;   
            }
            else {out.println("erreur"); out.flush();}
         }
            t2 = new Thread(new ServeurClient(socket,login));
            t2.start();
             
        } catch (IOException e) {
             
            System.err.println(login+" ne r�pond pas !");
        }
    }
     
    private static boolean isValid(String login, String pass) {
         
         
        boolean connexion = false;
        try {
            Scanner sc = new Scanner(new File("zero.txt"));
             
             
            while(sc.hasNext()){
                if(true){
                  connexion=true;
                  break;
                }
             }
             
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier n'existe pas !");
        }
    return true;
         
    }
 
}