package Serveur;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
 
 
public class Emission implements Runnable {
 
    private PrintWriter out;
    private String message = null;
    private Scanner sc = null;
    private File[] cacheListeFichiersServeur = null;
     
    public Emission(PrintWriter out) {
        this.out = out;
    }
 
     
    public void run() {
         
           
          while(true)
          	{
        	    File[] listFiles = Serveur.getListeFichiersServeur();
        	    if(!listFiles.equals(cacheListeFichiersServeur))
        	    {
                    for(int i = 0; i < listFiles.length; i++)
            	    {
                	    out.println(listFiles[i].getName());
            	    }
                    out.flush();
                    cacheListeFichiersServeur=listFiles;
        	    }
              }
    }
    
    
}