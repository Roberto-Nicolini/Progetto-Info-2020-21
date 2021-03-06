/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubrica.controller;

import it.edu.gastaldiabba.rubrica.Rubrica;
import it.edu.gastaldiabba.rubrica.model.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * FXML Controller class
 *
 * @author R2600-GTX1650s
 */
public class AggiungiController implements Initializable {

    @FXML
    private TextField txtfld8;
    @FXML
    private Button btnEliminaTutto;
    @FXML
    private Button btnaggiungiCliente;
    @FXML
    private TextField txtfld1;
    @FXML
    private Button btnelimina1;
    @FXML
    private TextField txtfld2;
    @FXML
    private Button btnelimina2;
    @FXML
    private TextField txtfld3;
    @FXML
    private Button btnelimina3;
    @FXML
    private TextField txtfld4;
    @FXML
    private Button btnelimina4;
    @FXML
    private TextField txtfld5;
    @FXML
    private Button btnelimina5;
    @FXML
    private TextField txtfld6;
    @FXML
    private Button btnelimina6;
    @FXML
    private TextField txtfld7;
    @FXML
    private Button btnelimina7;
    @FXML
    private Button btnAnnulla;
    
    /**
     * Initializes the controller class.
     */
   
    @FXML
    private TextField txtflderor;
    
    public void initialize(URL url, ResourceBundle rb) {
        txtfld1.setText("");
        txtfld2.setText("");
        txtfld3.setText("");
        txtfld4.setText("");
        txtfld5.setText("");
        txtfld6.setText("");
        txtfld7.setText("");
       txtflderor.setVisible(false);
       txtflderor.setEditable(false);
       txtfld8.setAlignment(Pos.CENTER);
    }    

    @FXML
    private void aggiungiCliente(ActionEvent event) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException {
       TextField[] arrtxtfld= {txtfld1,txtfld2,txtfld3,txtfld4,txtfld5,txtfld6,txtfld7,txtfld8};    
       int cap;
       int aff;
       int z=0;
        for(int g=0;g<arrtxtfld.length;g++){
            if(arrtxtfld[g].getText().isEmpty()){
                arrtxtfld[g]= null;
            }
            }
            for(int k=0;k<arrtxtfld.length;k++){
                if(arrtxtfld[k]==null){
                    z++;
                }
                if(z>=2){
                    txtflderor.setText("Pi?? campi sono vuoti, inseriscili");
                    txtflderor.setVisible(true);
                    return;
                }
            }
        if(txtfld1.getText().isEmpty()){
            txtflderor.setText("Inserire la ragione Sociale");
            txtflderor.setVisible(true);
            return;
        }
        
        String ragSoc=txtfld1.getText();
        
        if(txtfld2.getText().isEmpty()){
            txtflderor.setText("Inserire la citt??");
            txtflderor.setVisible(true);
            return;
        }   
        String citta=txtfld2.getText();
        if(txtfld3.getText().isEmpty()){
            txtflderor.setText("Inserire il cap");
            txtflderor.setVisible(true);
            return;
        }
        try{
        cap=Integer.parseInt(txtfld3.getText());
        }catch(NumberFormatException e){
            txtflderor.setText("Il cap deve essere un numero intero");
            txtflderor.setVisible(true);
            return;
        }
        if(txtfld4.getText().isEmpty()){
            txtflderor.setText("Inserire il telefono");
            txtflderor.setVisible(true);
            return;
        }
        String tel=txtfld4.getText();
        if(txtfld5.getText().isEmpty()){
            txtflderor.setText("Inserire la mail");
            txtflderor.setVisible(true);
            return;
        }
        String email= txtfld5.getText();
        if(txtfld6.getText().isEmpty()){
            txtflderor.setText("Inserire Indirizzo");
            txtflderor.setVisible(true);
            return;
        }
        String indirizzo = txtfld6.getText();
         if(txtfld7.getText().isEmpty()){
            txtflderor.setText("Inserire la partita iva");
            txtflderor.setVisible(true);
            return;
        }
        String piva= txtfld7.getText();
        if(txtfld8.getText().isEmpty()){
            txtflderor.setText("Inserire l'affidabiilit??");
            txtflderor.setVisible(true);
            return;
        }
       try{
        aff= Integer.parseInt(txtfld8.getText());
       }catch(NumberFormatException E){
           txtflderor.setText("L'affidabilit?? deve essere un numero intero tra 1 e 10");
            txtflderor.setVisible(true);
            return;
       }
        ArrayList <String> note = new ArrayList<String>();
        
        
        Cliente a= new Cliente(aff,cap,email,ragSoc,piva,tel,indirizzo,citta,note);
        Rubrica.getlistClienti().add(a);
        Cliente.create();
        Stage stage=(Stage) btnelimina1.getScene().getWindow();
        stage.close();
        
   }

    @FXML
    private void eliminatutto(ActionEvent event) {
        txtfld1.setText("");
        txtfld2.setText("");
        txtfld3.setText("");
        txtfld4.setText("");
        txtfld5.setText("");
        txtfld6.setText("");
        txtfld7.setText("");
        txtfld8.setText("");
    }

    @FXML
    private void eliminacontenutotxtfld1(ActionEvent event) {
        txtfld1.setText("");
    }

    @FXML
    private void eliminacontenutotxtfld2(ActionEvent event) {
        txtfld2.setText("");
    }

    @FXML
    private void eliminacontenutotxtfld3(ActionEvent event) {
        txtfld3.setText("");
    }

    @FXML
    private void eliminacontenutotxtfld4(ActionEvent event) {
        txtfld4.setText("");
    }

    @FXML
    private void eliminacontenutotxtfld5(ActionEvent event) {
        txtfld5.setText("");
    }

    @FXML
    private void eliminacontenutotxtfld6(ActionEvent event) {
        txtfld6.setText("");
    }

    @FXML
    private void eliminacontenutotxtfld7(ActionEvent event) {
        txtfld7.setText("");
    }

    @FXML
    private void annulla(ActionEvent event) {
        Stage stage=(Stage) btnelimina1.getScene().getWindow();
        stage.close();
    }
    }
   
//}
//}