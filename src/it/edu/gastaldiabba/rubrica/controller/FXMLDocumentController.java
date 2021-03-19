/*
 * To change this license header, choose License Headers in Project Properties.
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author galor
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private RadioButton ciao;
    private TextField txtfld1;
    private ListView<Cliente> list;
    @FXML
    private TextArea txtDettagli;
    @FXML
    private TextArea txtNote;
    @FXML
    private ListView<Cliente> listClienti;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listClienti.setItems(Rubrica.getlistClienti());
       
    }    

    @FXML
    private void selection(MouseEvent event) {
        Cliente a=listClienti.getSelectionModel().getSelectedItem();
        txtDettagli.setText(a.getDettagli());
    }
    
}
