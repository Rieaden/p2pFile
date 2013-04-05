package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;

	ArrayList<File> listFichiersServeur = new ArrayList<File>(); 

	public Reception(BufferedReader in){

		this.in = in;
	}

	public void run() {

		while(true){
			try {
				File f = new File(in.readLine());
				if(!listFichiersServeur.contains(f))
				{
					listFichiersServeur.add(f);
					File nouveauFichier = new File("src/Client/data/" + f);
					File fichierACopier = new File("src/Serveur/data/" + f);
					nouveauFichier.createNewFile();
					try{
						InputStream ips=new FileInputStream(fichierACopier.getPath()); 
						InputStreamReader ipsr=new InputStreamReader(ips);
						BufferedReader br=new BufferedReader(ipsr);
						String ligne;
						while ((ligne=br.readLine())!=null){
							FileWriter fw = new FileWriter (nouveauFichier);
							BufferedWriter bw = new BufferedWriter (fw);
							PrintWriter fichierSortie = new PrintWriter (bw); 

							fichierSortie.println(ligne);



							fichierSortie.close();
						}
						br.close(); 
					}		
					catch (Exception e){
						System.out.println(e.toString());
					}
				}
				for (int i = 0; i < listFichiersServeur.size(); i++)
				{
					File fichierCoteClient = new File("src/Client/data/" + listFichiersServeur.get(i));
					File fichierCoteServeur = new File("src/Serveur/data/" + listFichiersServeur.get(i));

					if(fichierCoteClient.equals(fichierCoteServeur))
					{
						System.out.println(fichierCoteClient + " : à jour");
					}
					else
					{
						System.out.println(fichierCoteClient.getName() + " out of date");
						System.out.println("Downloading");
						System.out.println(fichierCoteClient.lastModified());
						try{
							InputStream ips=new FileInputStream(fichierCoteServeur.getPath()); 
							InputStreamReader ipsr=new InputStreamReader(ips);
							BufferedReader br=new BufferedReader(ipsr);
							String ligne;
							while ((ligne=br.readLine())!=null){
								FileWriter fw = new FileWriter (fichierCoteClient);
								BufferedWriter bw = new BufferedWriter (fw);
								PrintWriter fichierSortie = new PrintWriter (bw); 
								fichierSortie.println(ligne);
								fichierSortie.close();
							}
							br.close(); 
						}		
						catch (Exception e){
							System.out.println(e.toString());
						}
					}

				}
				//            }
				System.out.println("La liste : " + listFichiersServeur.toString());

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}