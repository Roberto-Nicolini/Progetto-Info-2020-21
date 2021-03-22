package it.edu.gastaldiabba.rubrica.model;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//prova commitf
public class Cliente {
    private String ragSoc;
    private int affidabilita;    
    private int telefono;
    private String email;
    private String citta;
    private int cap;
    private String piva;
    private String indirizzo;
    
    
   // ArrayList<String> note
    static ObservableList<Cliente> listClienti = FXCollections.observableArrayList();
   
    public Cliente(int aff, int cap, String email, String ragsoc, String piva, int tel, String indirizzo, String citta){ // costruttore
      this.affidabilita=aff;
      this.cap=cap;
      this.email= email;
      this.ragSoc=ragsoc;
      this.piva= piva;
      this.telefono=tel;
      this.indirizzo= indirizzo;
      this.citta=citta;
   }
   
   public Cliente(Cliente cliente){ // costruttore copia
      this.affidabilita=cliente.getAffidabilita();
      this.cap=cliente.getCap();
      this.email= cliente.getEmail();
      this.ragSoc= cliente.getRagSoc();
      this.piva= cliente.getPiva();
      this.telefono= cliente.getTelefono();
      this.indirizzo= cliente.getIndirizzo();
      this.citta = cliente.getCitta();
   }

    public String getRagSoc(){
        return ragSoc;
    }
    public int getTelefono(){
        return telefono;
    }
    
    public int getAffidabilita(){
        return affidabilita;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getCap(){
        return cap;
    }
    
    public String getPiva(){
        return piva;
    }
    
    public String getIndirizzo(){
        return indirizzo;
    }
   
   public String getCitta(){
       return citta;
   }
    public void setRagSoc(String a){
        ragSoc=a;
    }
    
    public void setCitta(String h){
        citta=h;
    }
    
    public void setTelefono(int b){
        telefono=b;
    }
    
    public void setAffidabilita(int c){
        affidabilita=c;
    }
    
    public void setEmail(String d){
        email=d;
    }
    
    public void setIndirizzo(String e){
        indirizzo=e;
    }
    
    public void setCap(int f){
        cap=f;
    }
     
    public void setPiva(String g){
        piva=g;
    }
       
   public String getDettagli(){
       return("Dettagli:\n" + "Ragione sociale: "+ getRagSoc()+ "\nCitta: "+getCitta()+ "\nCap: "+getCap()+ "\nTelefono: "+getTelefono()+ 
               "\nEmail: "+getEmail()+ "\nIndirizzo "+ getIndirizzo()+ "\nAffidabilita: "+getAffidabilita()+ "\nPartita Iva:"+ getPiva());
   }
   public String toString(){
       return("Ragione sociale: "+ getRagSoc()+ " Citta: "+getCitta()+ "Cap: "+getCap()+ "\n Telefono: "+getTelefono()+ " Email: "+getEmail()+ " Indirizzo "+ getIndirizzo()+"Affidabilita: "+getAffidabilita()+ "Partita Iva:"+ getPiva());
       
   }
   
    /*
    private static File xmlFile = new File("clienti.xml");

      public static void create() throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
         Element rubrica=doc.createElement("Rubrica");
        doc.appendChild(rubrica);
        
        for(int i=0; i<listClienti.size();i++){
         
        
        Element cliente= doc.createElement("Cliente");
        rubrica.appendChild(cliente);
        
         Element ragsoc = doc.createElement("Ragionesociale");
        ragsoc.appendChild(doc.createTextNode(listClienti.get(i).getRagSoc()));
        cliente.appendChild(ragsoc);
        
        Element citta = doc.createElement("Citta");
        citta.appendChild(doc.createTextNode(listClienti.get(i).getCitta()));
        cliente.appendChild(citta);
        
        Element cap = doc.createElement("Cap");
        cap.appendChild(doc.createTextNode(String.valueOf(listClienti.get(i).getCap())));
        cliente.appendChild(cap);
        
        Element telefono = doc.createElement("Telefono");
        telefono.appendChild(doc.createTextNode(String.valueOf(listClienti.get(i).getTelefono())));
        cliente.appendChild(telefono);
        
        Element piva = doc.createElement("Partita Iva");
        piva.appendChild(doc.createTextNode(listClienti.get(i).getPiva()));
        cliente.appendChild(piva);
        
        Element affidabilita = doc.createElement("Affidabilita");
        affidabilita.appendChild(doc.createTextNode(String.valueOf(listClienti.get(i).getAffidabilità())));
        cliente.appendChild(affidabilita);
        
         Element indirizzo = doc.createElement("Indirizzo");
        indirizzo.appendChild(doc.createTextNode(listClienti.get(i).getIndirizzo()));
        cliente.appendChild(indirizzo);
        
        Element mail  = doc.createElement("Email");
        mail.appendChild(doc.createTextNode(listClienti.get(i).getEmail()));
        cliente.appendChild(mail);
        
        Element note = doc.createElement("Note");
        note.appendChild(doc.createTextNode("Note"));
        cliente.appendChild(note);
        for(int g=0;i < listanote.size();g++ ){
        Element nota = doc.createElement("Nota");
        nota.appendChild(doc.createTextNode(listanote.get(g)));
        note.appendChild(nota);
        }
        }
            


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        if (!xmlFile.exists()) {
            xmlFile.createNewFile();
             StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        }
      }
        
    /**
     *
     * @param name
     
    public static Cliente[] leggiXml (){
         Cliente [] pers = null;
         JFileChooser J=new JFileChooser();
        if(J.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
       try{
           File selectedFile = J.getSelectedFile();
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc= db.parse(selectedFile);
           doc.getDocumentElement().normalize();
           NodeList nodelist = doc.getElementsByTagName("cliente");
           pers= new Cliente [nodelist.getLength()];
           for( int t=0;t< nodelist.getLength();t++){
               Node node = nodelist.item(t);
           
           if(node.getNodeType()== Node.ELEMENT_NODE){
               Element element = (Element) node;
               pers[t]= new Cliente();
               pers[t].setRagSoc(element.getElementsByTagName("ragsoc").item(0).getTextContent());
                pers[t].setIndirizzo(element.getElementsByTagName("indirizzo").item(0).getTextContent());
                 pers[t].setPiva(element.getElementsByTagName("piva").item(0).getTextContent());
                pers[t].setCitta(element.getElementsByTagName("citta").item(0).getTextContent());
                 pers[t].setTelefono(Integer.parseInt(element.getElementsByTagName("telefono").item(0).getTextContent()));
                  pers[t].setEmail(element.getElementsByTagName("email").item(0).getTextContent());
                  pers[t].setCap(Integer.parseInt(element.getElementsByTagName("cap").item(0).getTextContent()));
                 pers[t].setAffidabilita(Integer.parseInt(element.getElementsByTagName("affidabilita").item(0).getTextContent()));
    }
           }
       }
       catch(Exception E){
          E.printStackTrace();
       }
        return pers;
    
}
        return pers;
}
*/
}
    