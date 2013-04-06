package Launcher;


import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import Serveur.*;
import Client.*;

public class Launcher extends Application {

	public static Thread t;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}


	@Override
	public void start(final Stage primaryStage) throws Exception {

		primaryStage.setTitle("P2P File Sharing debbache"); 
		final TextField tfPort = new TextField();
		final TextField tfIp = new TextField();
		final TextField tfFichierACreer = new TextField();
		Label lblPort = new Label("Port choisi : ");
		Label lblIp = new Label("Adresse IP destination : ");
		Label lblFichier = new Label("Nom fichier à créer");
		Button boutonCreer = new Button("Creer");
		Button boutonGo = new Button("Go");

		final Group root = new Group();  
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(30, 5, 5, 5));

		grid.add(lblPort, 0, 0);
		grid.add(lblIp, 0, 1);
		grid.add(lblFichier, 0, 2);
		grid.add(tfPort, 1, 0);
		grid.add(tfIp, 1, 1);
		grid.add(tfFichierACreer, 1, 2);
		grid.add(boutonGo, 1, 3);
		grid.add(boutonCreer, 2,2);

		root.getChildren().add(grid);
		Scene MyScene = new Scene(root, 300, 150);

		primaryStage.setScene(MyScene);
		primaryStage.show();

		boutonCreer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) 
			{
				try {
					(new File("src/Serveur/data/" + tfFichierACreer.getText())).createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		boutonGo.setOnAction(new EventHandler<ActionEvent>() 
				{

			@Override
			public void handle(ActionEvent event) 
			{
				try {
					System.out.println(new Integer(tfPort.getText()));
					t = new Thread(new Serveur(new ServerSocket(new Integer(tfPort.getText()))));
					t.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				t = new Thread(new Client(tfIp.getText()));
				t.start();
			}
				});


	}

}
