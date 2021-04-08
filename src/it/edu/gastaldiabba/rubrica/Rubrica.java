/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubrica;

import it.edu.gastaldiabba.rubrica.controller.AggiungiController;
import it.edu.gastaldiabba.rubrica.controller.FXMLDocumentController;
import it.edu.gastaldiabba.rubrica.model.Cliente;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author galor
 */
public class Rubrica extends Application {
     private static ObservableList<Cliente> listClienti = FXCollections.observableArrayList();
    private static Stage popupStage;
    private static AggiungiController popupController;
    
    public Rubrica() throws TransformerException, SAXException, IOException, ParserConfigurationException{
        listClienti=Cliente.leggiXml();  
   }
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));
        primaryStage.setTitle("Rubrica");
        primaryStage.setScene(new Scene(root));
        
        popupStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/Aggiungi.fxml"));
         Parent popupRoot = fxmlLoader.load();
        popupController = fxmlLoader.getController(); // salva riferimento a controller del popup
        popupStage.setScene(new Scene(popupRoot));
        popupStage.setTitle("Aggiungi Cliente");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(primaryStage);
        
        primaryStage.show();
    }
     public static ObservableList<Cliente> getlistClienti() {
        return listClienti;
    }
    
      public static void mostraPopupAggiungi() {
        popupStage.showAndWait();
    }
      public static void eliminaPopupAggiungi() {
        popupStage.close();
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
