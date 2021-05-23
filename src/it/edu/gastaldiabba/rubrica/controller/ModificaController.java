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
import java.util.Collections;
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
    int g=0;
    
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
        int x=0;
        int y=0;
       String citta ="";
       String ragSoc = "";
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
            if(listClienti.get(g).equals(a)){
                   if(txtfld8.getText().isEmpty()){
                    txtflderor.setText("Inserisci l'affidabilità");
                    txtflderor.setVisible(true);
                    return;
                   }try{
              x=Integer.parseInt(txtfld8.getText());
                   }catch(NumberFormatException e){
                        txtflderor.setVisible(true);
                       txtflderor.setText("L'affidabilità deve essere un numero intero tra 0 e 10");
                       return;
                   } if(Integer.valueOf(txtfld8.getText())<0 || Integer.valueOf(txtfld8.getText())>10){
                 txtflderor.setText("L'affidabilità deve essere un numero intero tra 0 e 10");
                    txtflderor.setVisible(true);
                 return;
                        }
                   if(txtfld3.getText().isEmpty()){
                     txtflderor.setText("Inserisci il cap");
                    txtflderor.setVisible(true);
                    return;
                 }
                   try{
                  y=  Integer.parseInt(txtfld3.getText());
                   }catch(NumberFormatException e){
                       txtflderor.setVisible(true);
                       txtflderor.setText("Il cap deve essere un numero intero");
                       return;
                   }if(txtfld3.getText().length()>6){
                       txtflderor.setVisible(true);
                       txtflderor.setText("Il cap non può superare 6 caratteri");
                       return;
                   }
               if(txtfld1.getText().isEmpty()){
                    txtflderor.setText("Inserisci la ragione sociale");
                    txtflderor.setVisible(true);
                    return;
                }else{
                StringBuilder ragSoc1=new StringBuilder(txtfld1.getText());
                 char r=ragSoc1.charAt(0);
                 char r1=Character.toUpperCase(r);
        
                
                  ragSoc1.setCharAt(0, r1);
                   ragSoc=ragSoc1.toString();
                 
                }
               if(txtfld5.getText().isEmpty()){
                    txtflderor.setText("Inserisci la email");
                    txtflderor.setVisible(true);
                    return;
               }
              
                if(txtfld6.getText().isEmpty()){
                    txtflderor.setText("Inserisci l'indirizzo");
                    txtflderor.setVisible(true);
                    return;
               }
               
                if(txtfld7.getText().isEmpty()){
                    txtflderor.setText("Inserisci la partita iva");
                    txtflderor.setVisible(true);
                    return;
               }
             
                if(txtfld4.getText().isEmpty()){
                    txtflderor.setText("Inserisci il telefono");
                    txtflderor.setVisible(true);
                    return;
               }
               
                if(txtfld2.getText().isEmpty()){
                    txtflderor.setText("Inserisci la città");
                    txtflderor.setVisible(true);
                    return;
                }
            StringBuilder citta1=new StringBuilder(txtfld2.getText());
        char r2=citta1.charAt(0);
        char r3=Character.toUpperCase(r2);
        
        
        citta1.setCharAt(0, r3);
        citta=citta1.toString();
            
            }
        for(int v=0;v<arrtxtfld.length;v++){
             if(arrtxtfld[v].getText().length()>25){
            txtflderor.setText("I campi non possono superare più di 25 caratteri");
            arrtxtfld[v].setText("");
            txtflderor.setVisible(true);
            return;
        }  ArrayList <String> note = new ArrayList<String>();
             int ei=0;
              Cliente f=new Cliente(x,y,txtfld5.getText(),ragSoc,txtfld7.getText(),txtfld4.getText(),txtfld6.getText(),citta,note);
             for(int h=0;h<Rubrica.getlistClienti().size();h++){
              
                 if(f.toString().equals(Rubrica.getlistClienti().get(h).toString())){
                   ei++;
                  
                 }
                
             }
             if(ei>0){
                    
                       txtflderor.setText("Cliente già esistente, non è possibile modificare con quetsi parametri");
                     txtflderor.setVisible(true);
                     return;
                 }
              Rubrica.getlistClienti().get(g).setCitta(citta);   
                Rubrica.getlistClienti().get(g).setRagSoc(ragSoc);   
                  Rubrica.getlistClienti().get(g).setTelefono(txtfld4.getText());   
                    Rubrica.getlistClienti().get(g).setAffidabilita(x);   
                      Rubrica.getlistClienti().get(g).setCap(y);   
                        Rubrica.getlistClienti().get(g).setPiva(txtfld7.getText());   
                          Rubrica.getlistClienti().get(g).setIndirizzo(txtfld6.getText());   
            Rubrica.getlistClienti().get(g).setEmail(txtfld5.getText());   
              Cliente.create();
             
                Stage stage = (Stage) txtfld1.getScene().getWindow();
                stage.close();
                
        
    }
    }
    @FXML
    private void Annulla(ActionEvent event) {
        listClienti.clear();
         Stage stage = (Stage) txtfld1.getScene().getWindow();
         stage.close();
    }

    void transferMessage(Cliente selectedItem,int b,boolean e) {
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
     
        for(int l=0;l<listClienti.size();l++){
            if(listClienti.get(l).toString().equals(selectedItem.toString())){
                g=l;
                break;
            }
        }
        listClienti.remove(g);
        listClienti.add(g, a);
        
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
