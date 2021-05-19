 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.gastaldiabba.rubrica.controller;

import it.edu.gastaldiabba.rubrica.Rubrica;
import it.edu.gastaldiabba.rubrica.model.Cliente;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author galor
 */
public class FXMLDocumentController implements Initializable {
    
    
  ArrayList <TextArea> noteVisual = new ArrayList<TextArea>();
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
    @FXML
    private Button btnEliminaCliente;
    @FXML
    private Button btnAggiungiCliente;
    @FXML
    private ScrollPane scrlpanenote;
    @FXML
    private Button btnModifica;
    @FXML
    private Button btnImporta;
    @FXML
    private RadioButton filterRag;
    @FXML
    private RadioButton filterAff;
    @FXML
    private RadioButton filterCitta;
    @FXML
    private RadioButton filterCres;
    @FXML
    private RadioButton filterDecr;
    @FXML
    private RadioButton filterCap;

    @FXML
    private void importa(ActionEvent event) throws ParserConfigurationException, SAXException, IOException, TransformerException {
          JFileChooser J=new JFileChooser();
          try{
          if(J.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
              File selectedFile = J.getSelectedFile();
              DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc= db.parse(selectedFile);
           doc.getDocumentElement().normalize();
           NodeList nodelist = doc.getElementsByTagName("cliente");
           
           for( int t=0;t< nodelist.getLength();t++){
              org.w3c.dom.Node node = nodelist.item(t);
              if(node.getNodeType()== org.w3c.dom.Node.ELEMENT_NODE){
                 Element element = (Element) node;
                 ArrayList<String> arrNote = new ArrayList<String>();
                 String ragSoc=(element.getElementsByTagName("ragsoc").item(0).getTextContent());
                 String indirizzo =(element.getElementsByTagName("indirizzo").item(0).getTextContent());
                 String piva=(element.getElementsByTagName("piva").item(0).getTextContent());
                 String citta=(element.getElementsByTagName("citta").item(0).getTextContent());
                 String telefono=element.getElementsByTagName("telefono").item(0).getTextContent();
                 String email=(element.getElementsByTagName("email").item(0).getTextContent());
                 int cap=(Integer.parseInt(element.getElementsByTagName("cap").item(0).getTextContent()));
                 int aff=(Integer.parseInt(element.getElementsByTagName("affidabilita").item(0).getTextContent()));
                 String note=(element.getElementsByTagName("note").item(0).getTextContent());
                 String [] ciao=note.split("/");
                 for(int g=0;g<ciao.length;g++){
                   arrNote.add(ciao[g]);
                 }
                 Cliente b=new Cliente( aff, cap, email, ragSoc, piva, telefono, indirizzo, citta,arrNote);
                 Rubrica.getlistClienti().add(b);
                 
              } //fine if
           }//fine for
           listClienti.setItems(Rubrica.getlistClienti());
           Cliente.create();
          }
          }catch(Exception e){
              Alert a = new Alert(AlertType.ERROR);
              a.setContentText("Errore nella lettura del file, assicurarsi che l'estensione sia .xml");
              a.show();
          }
    }

 public void filtrareCres(){
        if(filterRag.isSelected()){
       ObservableList<Cliente> listClientiFilt = FXCollections.observableArrayList();
       ArrayList <String> k = new ArrayList<String>();
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
       k.add(Rubrica.getlistClienti().get(i).getRagSoc());
      
        }
        
      
   Collections.sort(k);
  for(int i=0;i<Rubrica.getlistClienti().size();i++){
       System.out.println(k.get(i));
      
        }
       for(int i=0;i<k.size();i++){
            for(int j=0;j<k.size();j++){
                
               if(k.get(i).equals(Rubrica.getlistClienti().get(j).getRagSoc())){
                     listClientiFilt.add(new Cliente(Rubrica.getlistClienti().get(j).getAffidabilita(),
                  Rubrica.getlistClienti().get(j).getCap(),Rubrica.getlistClienti().get(j).getEmail(),k.get(i),Rubrica.getlistClienti().get(j).getPiva()
          ,Rubrica.getlistClienti().get(j).getTelefono(),Rubrica.getlistClienti().get(j).getIndirizzo(),Rubrica.getlistClienti().get(j).getCitta(),Rubrica.getlistClienti().get(j).getNote()));
                 Rubrica.getlistClienti().get(j).setRagSoc(Rubrica.getlistClienti().get(j).getRagSoc()+ ",");
                
               }
           }
        }
       for(int v=0;v<Rubrica.getlistClienti().size();v++){
           StringBuilder a=new StringBuilder(Rubrica.getlistClienti().get(v).getRagSoc());
           int p=a.length();
           
           a.deleteCharAt(p-1);
           Rubrica.getlistClienti().get(v).setRagSoc(a.toString());
       }
           
       listClienti.setItems(listClientiFilt);
    }else if(filterCitta.isSelected()){
        ObservableList<Cliente> listClientiFilt = FXCollections.observableArrayList();
       ArrayList <String> k = new ArrayList<String>();
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
       k.add(Rubrica.getlistClienti().get(i).getCitta());
      
        }
        
      
   Collections.sort(k);

       for(int i=0;i<k.size();i++){
            for(int j=0;j<k.size();j++){
                
               if(k.get(i).equals(Rubrica.getlistClienti().get(j).getCitta())){
                     listClientiFilt.add(new Cliente(Rubrica.getlistClienti().get(j).getAffidabilita(),
                  Rubrica.getlistClienti().get(j).getCap(),Rubrica.getlistClienti().get(j).getEmail(),Rubrica.getlistClienti().get(j).getRagSoc(),Rubrica.getlistClienti().get(j).getPiva()
          ,Rubrica.getlistClienti().get(j).getTelefono(),Rubrica.getlistClienti().get(j).getIndirizzo(),k.get(i),Rubrica.getlistClienti().get(j).getNote()));
                 Rubrica.getlistClienti().get(j).setCitta(Rubrica.getlistClienti().get(j).getCitta()+ ",");
                
               }
           }
        }
       for(int v=0;v<Rubrica.getlistClienti().size();v++){
           StringBuilder a=new StringBuilder(Rubrica.getlistClienti().get(v).getCitta());
           int p=a.length();
           
           a.deleteCharAt(p-1);
           Rubrica.getlistClienti().get(v).setCitta(a.toString());
       }
           
       listClienti.setItems(listClientiFilt);
    }else if(filterAff.isSelected()){
         ObservableList<Cliente> listClientiFilt = FXCollections.observableArrayList();
       ArrayList <Integer> k = new ArrayList<Integer>();
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
       k.add(Rubrica.getlistClienti().get(i).getAffidabilita());
        }
       
         for(int i=0;i<Rubrica.getlistClienti().size();i++){
       System.out.println(k.get(i));
        }
      Collections.sort(k);
     
      for(int i=0;i<k.size();i++){
            for(int j=0;j<k.size();j++){
               if(k.get(i).equals(Rubrica.getlistClienti().get(j).getAffidabilita())){
                     listClientiFilt.add(new Cliente(k.get(i),
                  Rubrica.getlistClienti().get(j).getCap(),Rubrica.getlistClienti().get(j).getEmail(),Rubrica.getlistClienti().get(j).getRagSoc(),Rubrica.getlistClienti().get(i).getPiva()
          ,Rubrica.getlistClienti().get(j).getTelefono(),Rubrica.getlistClienti().get(j).getIndirizzo(),Rubrica.getlistClienti().get(j).getCitta(),Rubrica.getlistClienti().get(j).getNote()));
                   
                  Rubrica.getlistClienti().get(j).setAffidabilita(Rubrica.getlistClienti().get(j).getAffidabilita()+ 10);
               }
           }
        
           
       }
      for(int i=0;i<Rubrica.getlistClienti().size();i++){
          Rubrica.getlistClienti().get(i).setAffidabilita(Rubrica.getlistClienti().get(i).getAffidabilita()-10);
      }
       listClienti.setItems(listClientiFilt);
    }if(filterCap.isSelected()){
         ObservableList<Cliente> listClientiFilt = FXCollections.observableArrayList();
       ArrayList <Integer> k = new ArrayList<Integer>();
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
       k.add(Rubrica.getlistClienti().get(i).getCap());
        }
        
      
   Collections.sort(k);
       for(int i=0;i<k.size();i++){
            for(int j=0;j<k.size();j++){
                
               if(k.get(i).equals(Rubrica.getlistClienti().get(j).getCap())){
                     listClientiFilt.add(new Cliente(Rubrica.getlistClienti().get(j).getAffidabilita(),
                 k.get(i),Rubrica.getlistClienti().get(j).getEmail(),Rubrica.getlistClienti().get(j).getRagSoc(),Rubrica.getlistClienti().get(j).getPiva()
          ,Rubrica.getlistClienti().get(j).getTelefono(),Rubrica.getlistClienti().get(j).getIndirizzo(),Rubrica.getlistClienti().get(j).getCitta(),Rubrica.getlistClienti().get(j).getNote()));
                 Rubrica.getlistClienti().get(j).setCap(Rubrica.getlistClienti().get(j).getCap()+1);
                
               }
           }
        }
       for(int v=0;v<Rubrica.getlistClienti().size();v++){
          Rubrica.getlistClienti().get(v).setCap(Rubrica.getlistClienti().get(v).getCap()-1);
       }
           
       listClienti.setItems(listClientiFilt);
    }
 }
     public void filtrareDecr() {
         if(filterRag.isSelected()){
         ObservableList<Cliente> listClientiFilt = FXCollections.observableArrayList();
       ArrayList <String> k = new ArrayList<String>();
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
       k.add(Rubrica.getlistClienti().get(i).getRagSoc());
      
        }
        
      
   Collections.sort(k);
   Collections.reverse(k);
  for(int i=0;i<Rubrica.getlistClienti().size();i++){
       System.out.println(k.get(i));
      
        }
       for(int i=0;i<k.size();i++){
            for(int j=0;j<k.size();j++){
                
               if(k.get(i).equals(Rubrica.getlistClienti().get(j).getRagSoc())){
                     listClientiFilt.add(new Cliente(Rubrica.getlistClienti().get(j).getAffidabilita(),
                  Rubrica.getlistClienti().get(j).getCap(),Rubrica.getlistClienti().get(j).getEmail(),k.get(i),Rubrica.getlistClienti().get(j).getPiva()
          ,Rubrica.getlistClienti().get(j).getTelefono(),Rubrica.getlistClienti().get(j).getIndirizzo(),Rubrica.getlistClienti().get(j).getCitta(),Rubrica.getlistClienti().get(j).getNote()));
                 Rubrica.getlistClienti().get(j).setRagSoc(Rubrica.getlistClienti().get(j).getRagSoc()+ ",");
                
               }
           }
        }
       for(int v=0;v<Rubrica.getlistClienti().size();v++){
           StringBuilder a=new StringBuilder(Rubrica.getlistClienti().get(v).getRagSoc());
           int p=a.length();
           
           a.deleteCharAt(p-1);
           Rubrica.getlistClienti().get(v).setRagSoc(a.toString());
       }
           
       listClienti.setItems(listClientiFilt);
         }else if(filterCitta.isSelected()){
             ObservableList<Cliente> listClientiFilt = FXCollections.observableArrayList();
       ArrayList <String> k = new ArrayList<String>();
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
       k.add(Rubrica.getlistClienti().get(i).getCitta());
      
        }
        
      
   Collections.sort(k);
   Collections.reverse(k);
       for(int i=0;i<k.size();i++){
            for(int j=0;j<k.size();j++){
                
               if(k.get(i).equals(Rubrica.getlistClienti().get(j).getCitta())){
                     listClientiFilt.add(new Cliente(Rubrica.getlistClienti().get(j).getAffidabilita(),
                  Rubrica.getlistClienti().get(j).getCap(),Rubrica.getlistClienti().get(j).getEmail(),Rubrica.getlistClienti().get(j).getRagSoc(),Rubrica.getlistClienti().get(j).getPiva()
          ,Rubrica.getlistClienti().get(j).getTelefono(),Rubrica.getlistClienti().get(j).getIndirizzo(),k.get(i),Rubrica.getlistClienti().get(j).getNote()));
                 Rubrica.getlistClienti().get(j).setCitta(Rubrica.getlistClienti().get(j).getCitta()+ ",");
                
               }
           }
        }
       for(int v=0;v<Rubrica.getlistClienti().size();v++){
           StringBuilder a=new StringBuilder(Rubrica.getlistClienti().get(v).getCitta());
           int p=a.length();
           
           a.deleteCharAt(p-1);
           Rubrica.getlistClienti().get(v).setCitta(a.toString());
       }
           
       listClienti.setItems(listClientiFilt);
    }else if(filterAff.isSelected()){
         ObservableList<Cliente> listClientiFilt = FXCollections.observableArrayList();
       ArrayList <Integer> k = new ArrayList<Integer>();
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
       k.add(Rubrica.getlistClienti().get(i).getAffidabilita());
        }
       
         for(int i=0;i<Rubrica.getlistClienti().size();i++){
       System.out.println(k.get(i));
        }
         
      Collections.sort(k);
     Collections.reverse(k);
      for(int i=0;i<k.size();i++){
            for(int j=0;j<k.size();j++){
               if(k.get(i).equals(Rubrica.getlistClienti().get(j).getAffidabilita())){
                     listClientiFilt.add(new Cliente(k.get(i),
                  Rubrica.getlistClienti().get(j).getCap(),Rubrica.getlistClienti().get(j).getEmail(),Rubrica.getlistClienti().get(j).getRagSoc(),Rubrica.getlistClienti().get(i).getPiva()
          ,Rubrica.getlistClienti().get(j).getTelefono(),Rubrica.getlistClienti().get(j).getIndirizzo(),Rubrica.getlistClienti().get(j).getCitta(),Rubrica.getlistClienti().get(j).getNote()));
                   
                  Rubrica.getlistClienti().get(j).setAffidabilita(Rubrica.getlistClienti().get(j).getAffidabilita()+ 10);
               }
           }
        } 
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
          Rubrica.getlistClienti().get(i).setAffidabilita(Rubrica.getlistClienti().get(i).getAffidabilita()-10);
      }
       listClienti.setItems(listClientiFilt);
    }if(filterCap.isSelected()){
         ObservableList<Cliente> listClientiFilt = FXCollections.observableArrayList();
       ArrayList <Integer> k = new ArrayList<Integer>();
       for(int i=0;i<Rubrica.getlistClienti().size();i++){
       k.add(Rubrica.getlistClienti().get(i).getCap());
        }
        
      
   Collections.sort(k);
   Collections.reverse(k);
       for(int i=0;i<k.size();i++){
            for(int j=0;j<k.size();j++){
                
               if(k.get(i).equals(Rubrica.getlistClienti().get(j).getCap())){
                     listClientiFilt.add(new Cliente(Rubrica.getlistClienti().get(j).getAffidabilita(),
                 k.get(i),Rubrica.getlistClienti().get(j).getEmail(),Rubrica.getlistClienti().get(j).getRagSoc(),Rubrica.getlistClienti().get(j).getPiva()
          ,Rubrica.getlistClienti().get(j).getTelefono(),Rubrica.getlistClienti().get(j).getIndirizzo(),Rubrica.getlistClienti().get(j).getCitta(),Rubrica.getlistClienti().get(j).getNote()));
                 Rubrica.getlistClienti().get(j).setCap(Rubrica.getlistClienti().get(j).getCap()+1);
                
               }
           }
        }
       for(int v=0;v<Rubrica.getlistClienti().size();v++){
          Rubrica.getlistClienti().get(v).setCap(Rubrica.getlistClienti().get(v).getCap()-1);
       }
           
       listClienti.setItems(listClientiFilt);
    }
     }
    @FXML
    private void filtraggioRag(ActionEvent event) {
        if(filterRag.isSelected()){
        filterAff.setSelected(false);
        filterCap.setSelected(false);
        filterCitta.setSelected(false);
        filterCres.setDisable(false);
        filterDecr.setDisable(false);
        filterCres.setSelected(true);
        filterDecr.setSelected(false);
        filtrareCres();
        }else{
             filterCres.setDisable(true);
        filterDecr.setDisable(true);
        filterCres.setSelected(false);
        filterDecr.setSelected(false);
        listClienti.setItems(Rubrica.getlistClienti());
        }
        
    }

    @FXML
    private void filtraggioAff(ActionEvent event) {
        if(filterAff.isSelected()){
         filterRag.setSelected(false);
        filterCap.setSelected(false);
        filterCres.setDisable(false);
        filterDecr.setDisable(false);
        filterCitta.setSelected(false);
        filterCres.setSelected(true);
        filterDecr.setSelected(false);
        filtrareCres();
        }else{
            filterCres.setDisable(true);
            filterDecr.setDisable(true);
            filterCres.setSelected(false);
            filterDecr.setSelected(false);
            listClienti.setItems(Rubrica.getlistClienti());
        }
    }

    @FXML
    private void filtraggioCitta(ActionEvent event) {
        if(filterCitta.isSelected()){
           filterAff.setSelected(false);
        filterRag.setSelected(false);
        filterCres.setDisable(false);
        filterDecr.setDisable(false);
        filterCap.setSelected(false); 
        filterCres.setSelected(true);
        filterDecr.setSelected(false);
        filtrareCres();
        }else{
            listClienti.setItems(Rubrica.getlistClienti());
            filterCres.setSelected(false);
            filterCres.setDisable(true);
        filterDecr.setDisable(true);
        }
    }

    @FXML
    private void filtraggioCres(ActionEvent event) {
        //if(rag=true,)...
        filterDecr.setSelected(false);
        filterCres.setSelected(true);
        filtrareCres();
    }

    @FXML
    private void filtraggioDecr(ActionEvent event) {
        filterDecr.setSelected(true);
        filterCres.setSelected(false);
        filtrareDecr();
    }

    @FXML
    private void filtraggioCap(ActionEvent event) {
        if(filterCap.isSelected()){
           filterAff.setSelected(false);
        filterRag.setSelected(false);
        filterCitta.setSelected(false); 
        filterCres.setSelected(true);
        filterDecr.setSelected(false);
        filterCres.setDisable(false);
        filterDecr.setDisable(false);
        filtrareCres();
        }else{
            listClienti.setItems(Rubrica.getlistClienti());
            filterCres.setSelected(false);
            filterCres.setDisable(true);
            filterDecr.setSelected(false);
            filterDecr.setDisable(true);
        }
        
    }

   

    /**
     * Initializes the controller class.
     */
    
    static class Cell extends ListCell <Cliente>{
        GridPane hbox=new GridPane();
       
        TextField txtAff= new TextField();
        Text a= new Text();
        Text b=new Text();
        public Cell(){
            super();
            
            hbox.setVgap(-5);
            hbox.add(txtAff,0, 0, 1, 1);
            hbox.add(a, 1, 0, 1, 1);
            hbox.add(b, 1, 1 , 1, 1);
            
        }
        
        public void updateItem(Cliente name, boolean empty){
            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);
            
            if(name !=null && !empty){
                txtAff.setText(String.valueOf(name.getAffidabilita()).toString());
                txtAff.setPrefSize(30, 30);
                txtAff.setEditable(false);
                a.setText(name.toStringBold());
                a.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                b.setText(name.toString());
                b.setFont(Font.font("Verdana", 12));
                setGraphic(hbox);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterCres.setDisable(true);
         filterDecr.setDisable(true);
        listClienti.setItems(Rubrica.getlistClienti());
      listClienti.setCellFactory(param -> new Cell());

        
        hboxCrud.setSpacing(64);
        flowPane.setHgap(10);
        hboxNote.setSpacing(5.0);
        btnEliminaCliente.setDisable(true);
        btnElimina.setDisable(true);
        
        btnModifica.setDisable(true);
        btnAggiungiNota.setDisable(true);
       btnModificaNota.setDisable(true);
       hboxNote.setPadding(new Insets(0, 26, 20, 0));
       
       
 }
    @FXML
    private void selection(MouseEvent event) {
        
        btnModifica.setDisable(false);
       btnEliminaCliente.setDisable(false);
        btnElimina.setDisable(false);
       btnElimina.setSelected(false);
       btnAggiungiNota.setDisable(false);
       btnModificaNota.setDisable(false);
       noteVisual.clear();
        hboxNote.getChildren().clear();
        Cliente a=listClienti.getSelectionModel().getSelectedItem();
        try{
        txtDettagli.setText(a.getDettagli());
        }catch(NullPointerException e){
            System.out.println("a");
        }
        if(a.getNote().size()>0){
        for(int i=0;i<a.getNote().size();i++){
            noteVisual.add(new TextArea(a.getNote().get(i)));
            noteVisual.get(i).setPrefSize(100, 25);
            noteVisual.get(i).setWrapText(true);
            
        }
        for(int b=0;b<noteVisual.size();b++){
        hboxNote.getChildren().add(noteVisual.get(b));
        }
        }else{
            
        }
    }

    @FXML
    private void aggiungiNota(ActionEvent event) {
        btnElimina.setSelected(false);
        TextArea txt = new TextArea("");
        txt.setPrefSize(120, 20);
        Cliente a=listClienti.getSelectionModel().getSelectedItem();
        hboxNote.getChildren().add(0,txt);
        noteVisual.add(txt);
        //a.getNote().add()
    }

    @FXML
    private void modifica(ActionEvent event) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException {
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
         for(int i=0;i<noteVisual.size();i++){
         noteVisual.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {
                final Object selectedNode = event.getSource();
                 final int selectedIdx  = hboxNote.getChildren().indexOf(selectedNode);
                 noteVisual.get(selectedIdx).setEditable(true);
             }
             
         });
         }
    Cliente.create();
}
    @FXML
    private void elimina(ActionEvent event) {
         Cliente a=listClienti.getSelectionModel().getSelectedItem();
         
        for(int i=0;i<noteVisual.size();i++){
           boolean e= btnElimina.isSelected();
           if(e==true){
         noteVisual.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          final Object selectedNode = mouseEvent.getSource();
          final int selectedIdx  = hboxNote.getChildren().indexOf(selectedNode);
                 hboxNote.getChildren().remove(selectedIdx);
                 noteVisual.remove(selectedIdx);
                 System.out.println(e);
       };
               });
        
        }else if(e==false){
                noteVisual.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        final Object selectedNode = event.getSource();
                 final int selectedIdx  = hboxNote.getChildren().indexOf(selectedNode);
                 noteVisual.get(selectedIdx).setEditable(true);
                    };
                });
            
        };
                }
        }
        
    
    @FXML
    private void aggiungiCliente(ActionEvent event) throws IOException {
         
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/edu/gastaldiabba/rubrica/view/Aggiungi.fxml"));
            Parent root = loader.load();
       
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Aggiungi Cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnElimina.getScene().getWindow());
            stage.showAndWait();
               btnEliminaCliente.setDisable(true);
               btnModifica.setDisable(true);
       
        listClienti.setItems(Rubrica.getlistClienti());
    }

    @FXML
    private void eliminaCliente(ActionEvent event) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException {
        boolean a=isOk();
        if(a==true){
        Cliente b=listClienti.getSelectionModel().getSelectedItem();
        for(int r=0;r<Rubrica.getlistClienti().size();r++){
            if(b.toString().equals(Rubrica.getlistClienti().get(r).toString())){
               Rubrica.getlistClienti().remove(r); 
            }
        }
      
        listClienti.setItems(Rubrica.getlistClienti());
        Cliente.create();
       if(filterCres.isSelected()){
           filtrareCres();
        }else if(filterDecr.isSelected()){
           filtrareDecr();
        }
        }
    }



    @FXML
    private void sendData(MouseEvent event) throws ParserConfigurationException, TransformerException, SAXException {
       
   try {
         boolean e=true;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/edu/gastaldiabba/rubrica/view/Modifica.fxml"));
            Parent root = loader.load();
            
            
            ModificaController mod = loader.getController();
            if(filterCres.isSelected()){
                e=true;
            }else{
                e=false;
            }
            mod.transferMessage(listClienti.getSelectionModel().getSelectedItem(),listClienti.getSelectionModel().getSelectedIndex(),e);
 
            int b=listClienti.getSelectionModel().getSelectedIndex();  
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifica Cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnElimina.getScene().getWindow());
           
            stage.showAndWait();
            
         
            btnModifica.setDisable(true);
            txtDettagli.clear();
            txtDettagli.setText(Rubrica.getlistClienti().get(listClienti.getSelectionModel().getSelectedIndex()).getDettagli());
         
           Cliente.create();
           
          // listClienti.getItems().clear();
           
          // listClienti.setItems(Cliente.leggiXml());
          
          listClienti.refresh();
         if(filterCres.isSelected()){
              filtrareCres();
          }else if(filterDecr.isSelected()){
              filtrareDecr();
          }
           btnEliminaCliente.setDisable(true);
          
        } catch (IOException ex) {
            System.err.println(ex);
        } 
    }
    public static boolean isOk(){
        boolean ok=false;
        Alert b = new Alert(AlertType.WARNING);
        b.setContentText("Confermare la scelta di eliminare il cliente? L'operazione è irreversibile\n");
       // b.setHeaderText("Avvertenza");
        b.setTitle("Conferma eliminazione cliente");
        ButtonType annulla= new ButtonType("Annulla", ButtonData.CANCEL_CLOSE);
       b.getDialogPane().getButtonTypes().add(annulla);
        
        Optional <ButtonType> c= b.showAndWait();
        if(c.isPresent() && c.get()==ButtonType.OK){
            ok=true;
        }
       
        
      return ok;
    }
   
}