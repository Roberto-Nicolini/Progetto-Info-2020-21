/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubrica;

import it.edu.gastaldiabba.rubrica.controller.FXMLDocumentController;
import it.edu.gastaldiabba.rubrica.model.Cliente;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author galor
 */
public class Rubrica extends Application {
     private static ObservableList<Cliente> listClienti = FXCollections.observableArrayList();
    
    
    public Rubrica(){
       listClienti.add(new Cliente(2,16100,"robertofdsnw","fieschi","42879879124", 234, "ciao","genova"));
        listClienti.add(new Cliente(2,16100,"r","fieschir","42879879124", 234, "ciao","genova"));
         listClienti.add(new Cliente(2,16100,"r","roberto","42879879124", 234, "ciao","genova"));
          listClienti.add(new Cliente(2,16100,"r","daneil","42879879124", 234, "ciao","genova"));
           listClienti.add(new Cliente(2,16100,"r","manuel","42879879124", 567, "ciao","genova"));

   }
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));
        primaryStage.setTitle("Rubrica");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
     public static ObservableList<Cliente> getlistClienti() {
        return listClienti;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Cliente[] a = Cliente.leggiXml();
       // Cliente b= new Cliente(a[0]);
      //  b.toString();
    }
    
}
