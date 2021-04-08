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
       
    }    

    @FXML
    private void aggiungiCliente(ActionEvent event) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException {
       TextField[] arrtxtfld= {txtfld1,txtfld2,txtfld3,txtfld4,txtfld5,txtfld6,txtfld7,txtfld8};
        try{

        String ragSoc=txtfld1.getText();
        String citta=txtfld2.getText();

       int cap=Integer.parseInt(txtfld3.getText());
        
        String tel=txtfld4.getText();
        String email= txtfld5.getText();
        String indirizzo = txtfld6.getText();
        String piva= txtfld7.getText();
       
       int aff= Integer.parseInt(txtfld8.getText());
     
        ArrayList <String> note = new ArrayList<String>();
        for(int i=0;i<arrtxtfld.length;i++){
            if(arrtxtfld[i].getText().isEmpty()){
                arrtxtfld[i]=null;
            }       
        }
        if((arrtxtfld[0]==null)||(arrtxtfld[1]==null)||(arrtxtfld[3]==null)||(arrtxtfld[4]==null)||(arrtxtfld[5]==null)||(arrtxtfld[6]==null)){
            txtflderor.setText("errore");
        }
        else{
        Cliente a= new Cliente(aff,cap,email,ragSoc,piva,tel,indirizzo,citta,note);
        Rubrica.getlistClienti().add(a);
        Cliente.create();
        Rubrica.eliminaPopupAggiungi();
        }
       }catch(NumberFormatException e){
                    
                txtflderor.setText("Errore");
                
            }       
          }
    }
   
//}
//}