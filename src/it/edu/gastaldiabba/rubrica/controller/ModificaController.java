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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ModificaController implements Initializable {
 static ObservableList<Cliente> listClienti = FXCollections.observableArrayList();
    @FXML
    private TextField txtfld8;
    @FXML
    private Button btneliminatutto;
    @FXML
    private Button btnModifica;
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
    private Button Annulla;
    Cliente a= new Cliente(0,0,"","","","","","");
    @FXML
    private TextField txtflderor;
    
    
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      
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
    private void modifica(ActionEvent event) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException {
        TextField[] arrtxtfld= {txtfld1,txtfld2,txtfld3,txtfld4,txtfld5,txtfld6,txtfld7,txtfld8};  
        int z=0;
        for(int i=0;i<Rubrica.getlistClienti().size();i++){
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
                    txtflderor.setText("Più campi sono vuoti, inseriscili");
                    txtflderor.setVisible(true);
                    return;
                }
            }
            if(listClienti.get(i).equals(a)){
                   if(txtfld8.getText().isEmpty()){
                    txtflderor.setText("Inserisci l'affidabilità");
                    txtflderor.setVisible(true);
                    return;
                   }
                Rubrica.getlistClienti().get(i).setAffidabilita(Integer.parseInt(txtfld8.getText()));
                   if(txtfld3.getText().isEmpty()){
                     txtflderor.setText("Inserisci il cap");
                    txtflderor.setVisible(true);
                    return;
                 }
                   try{
                Rubrica.getlistClienti().get(i).setCap(Integer.parseInt(txtfld3.getText()));
                   }catch(NumberFormatException e){
                       txtflderor.setVisible(true);
                       txtflderor.setText("Il cap deve essere un numero intero");
                       return;
                   }
               if(txtfld1.getText().isEmpty()){
                    txtflderor.setText("Inserisci la ragione sociale");
                    txtflderor.setVisible(true);
                    return;
                }else{
                Rubrica.getlistClienti().get(i).setRagSoc(txtfld1.getText()); 
                }
               if(txtfld5.getText().isEmpty()){
                    txtflderor.setText("Inserisci la email");
                    txtflderor.setVisible(true);
                    return;
               }
                Rubrica.getlistClienti().get(i).setEmail(txtfld5.getText());
                if(txtfld6.getText().isEmpty()){
                    txtflderor.setText("Inserisci l'indirizzo");
                    txtflderor.setVisible(true);
                    return;
               }
                Rubrica.getlistClienti().get(i).setIndirizzo(txtfld6.getText());   
                if(txtfld7.getText().isEmpty()){
                    txtflderor.setText("Inserisci la partita iva");
                    txtflderor.setVisible(true);
                    return;
               }
                Rubrica.getlistClienti().get(i).setPiva(txtfld7.getText());     
                if(txtfld4.getText().isEmpty()){
                    txtflderor.setText("Inserisci il telefono");
                    txtflderor.setVisible(true);
                    return;
               }
                Rubrica.getlistClienti().get(i).setTelefono(txtfld4.getText());  
                if(txtfld2.getText().isEmpty()){
                    txtflderor.setText("Inserisci la città");
                    txtflderor.setVisible(true);
                    return;
                }
                Rubrica.getlistClienti().get(i).setCitta(txtfld2.getText());   
                Cliente.create();
                Stage stage = (Stage) txtfld1.getScene().getWindow();
                stage.close();
                
            }
        }
        
        
    }

    @FXML
    private void Annulla(ActionEvent event) {
        listClienti.clear();
         Stage stage = (Stage) txtfld1.getScene().getWindow();
         stage.close();
    }

    void transferMessage(Cliente selectedItem,int i) {
        txtflderor.setVisible(false);
        txtfld1.setText(selectedItem.getRagSoc());
        txtfld2.setText(selectedItem.getCitta());
        txtfld3.setText(String.valueOf(selectedItem.getCap()).toString());
        txtfld4.setText(selectedItem.getTelefono());
        txtfld5.setText(selectedItem.getEmail());
        txtfld6.setText(selectedItem.getIndirizzo());
        txtfld7.setText(selectedItem.getPiva());
        txtfld8.setAlignment(Pos.CENTER);
        txtfld8.setText(String.valueOf(selectedItem.getAffidabilita()).toString());
        listClienti.setAll(Rubrica.getlistClienti());
        listClienti.remove(i);
        listClienti.add(i, a);
        
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
    
}
