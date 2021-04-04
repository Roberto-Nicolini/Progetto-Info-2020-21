 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubrica.controller;

import it.edu.gastaldiabba.rubrica.Rubrica;
import it.edu.gastaldiabba.rubrica.model.Cliente;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author galor
 */
public class FXMLDocumentController implements Initializable {
  ArrayList <TextField> noteVisual = new ArrayList<TextField>();
    @FXML
    private RadioButton ciao;
    private ListView<Cliente> list;
    @FXML
    private TextArea txtDettagli;
    @FXML
    private ListView<Cliente> listClienti;
    @FXML
    private VBox hboxNote;
    @FXML
    private Button btnAggiungiNota;
   
    @FXML
    private HBox hboxCrud;
    @FXML
    private Button btnModificaNota;
  @FXML
    private ToggleButton btnElimina;
    @FXML
    private FlowPane flowPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        hboxNote.setSpacing(5.0);
        btnElimina.setDisable(true);
        btnAggiungiNota.setDisable(true);
       btnModificaNota.setDisable(true);
       txtDettagli.setText("Dettagli:");
       listClienti.setItems(Rubrica.getlistClienti());
 }
    @FXML
    private void selection(MouseEvent event) {
        btnElimina.setDisable(false);
       btnElimina.setSelected(false);
       btnAggiungiNota.setDisable(false);
       btnModificaNota.setDisable(false);
       noteVisual.clear();
        hboxNote.getChildren().clear();
        Cliente a=listClienti.getSelectionModel().getSelectedItem();
        txtDettagli.setText(a.getDettagli());
       
        
        for(int i=0;i<a.getNote().size();i++){
            noteVisual.add(new TextField(a.getNote().get(i)));
        }
        for(int b=0;b<noteVisual.size();b++){
        hboxNote.getChildren().add(noteVisual.get(b));
        }
    }

    @FXML
    private void aggiungiNota(ActionEvent event) {
        btnElimina.setSelected(false);
        TextField txt = new TextField("");
        Cliente a=listClienti.getSelectionModel().getSelectedItem();
        hboxNote.getChildren().add(txt);
        noteVisual.add(txt);
        //a.getNote().add()
    }

    @FXML
    private void modifica(ActionEvent event) {
         Cliente a=listClienti.getSelectionModel().getSelectedItem();
         a.getNote().clear();
        for(int v=0;v<noteVisual.size();v++){
            a.getNote().add(noteVisual.get(v).getText());
        }
        hboxNote.getChildren().clear();
         for(int v=0;v<noteVisual.size();v++){
            hboxNote.getChildren().add(noteVisual.get(v));
        }
         btnElimina.setSelected(false);
    
}
    @FXML
    private void elimina(ActionEvent event) {
         Cliente a=listClienti.getSelectionModel().getSelectedItem();
        for(int i=0;i<noteVisual.size();i++){
         noteVisual.get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          final Object selectedNode = mouseEvent.getSource();
          final int selectedIdx  = hboxNote.getChildren().indexOf(selectedNode);
                 hboxNote.getChildren().remove(selectedIdx);
                 noteVisual.remove(selectedIdx);
                 
       };
               });
        }
        a.getNote().clear();
         for(int v=0;v<noteVisual.size();v++){
            a.getNote().add(noteVisual.get(v).getText());
        }
    }
    
}
    