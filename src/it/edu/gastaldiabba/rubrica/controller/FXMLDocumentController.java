 /* To change this license header, choose License Headers in Project Properties.
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author galor
 */
public class FXMLDocumentController implements Initializable {
    ObservableList <Cliente> lista= FXCollections.observableArrayList(Rubrica.getlistClienti());
    
  ArrayList <TextArea> noteVisual = new ArrayList<TextArea>();
    @FXML
    private RadioButton ciao;
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
        }
    }

    @FXML
    private void aggiungiNota(ActionEvent event) {
        btnElimina.setSelected(false);
        TextArea txt = new TextArea("");
        txt.setPrefSize(120, 20);
        Cliente a=listClienti.getSelectionModel().getSelectedItem();
        hboxNote.getChildren().add(txt);
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

       
        listClienti.setItems(Rubrica.getlistClienti());
    }

    @FXML
    private void eliminaCliente(ActionEvent event) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException {
        int i=listClienti.getSelectionModel().getSelectedIndex();
        Rubrica.getlistClienti().remove(i);
        listClienti.setItems(Rubrica.getlistClienti());
        Cliente.create();
    }



    @FXML
    private void sendData(MouseEvent event) throws ParserConfigurationException, TransformerException, SAXException {
       
   try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/edu/gastaldiabba/rubrica/view/Modifica.fxml"));
            Parent root = loader.load();
            
            
            ModificaController mod = loader.getController();
            
            mod.transferMessage(listClienti.getSelectionModel().getSelectedItem(),listClienti.getSelectionModel().getSelectedIndex());
 
                        
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifica Cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnElimina.getScene().getWindow());
            stage.showAndWait();
            Cliente.create();
            btnModifica.setDisable(true);
            txtDettagli.clear();
            txtDettagli.setText(Rubrica.getlistClienti().get(listClienti.getSelectionModel().getSelectedIndex()).getDettagli());
            listClienti.getItems().clear();
           listClienti.setItems(Cliente.leggiXml());
         
          
        } catch (IOException ex) {
            System.err.println(ex);
        } 
    }
    
   
}