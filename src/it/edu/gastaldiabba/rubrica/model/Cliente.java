package it.edu.gastaldiabba.rubrica.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.getProperty;
import java.util.ArrayList;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
import org.xml.sax.SAXException;

//prova commitf
public class Cliente {
    private String ragSoc;
    private int affidabilita;    
    private String telefono;
    private String email;
    private String citta;
    private int cap;
    private String piva;
    private String indirizzo;
    private ArrayList <String> note ;
    
   
    static ObservableList<Cliente> listClienti = FXCollections.observableArrayList();
    
     public Cliente(int aff, int cap, String email, String ragsoc, String piva, String tel, String indirizzo, String citta){ // costruttore
      this.affidabilita=aff;
      this.cap=cap;
      this.email= email;
      this.ragSoc=ragsoc;
      this.piva= piva;
      this.telefono=tel;
      this.indirizzo= indirizzo;
      this.citta=citta;
    }
    public Cliente(int aff, int cap, String email, String ragsoc, String piva, String tel, String indirizzo, String citta, ArrayList<String>note){ // costruttore
      this.affidabilita=aff;
      this.cap=cap;
      this.email= email;
      this.ragSoc=ragsoc;
      this.piva= piva;
      this.telefono=tel;
      this.indirizzo= indirizzo;
      this.citta=citta;
      this.note=note;
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
      this.note=cliente.getNote();
   }

    public Cliente() {
      
    }
    
    public ArrayList <String> getNote(){
       return note;
    }
    public String getRagSoc(){
        return ragSoc;
    }
    public String getTelefono(){
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
    
    public void setTelefono(String b){
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
       return("Ragione sociale: "+ getRagSoc()+ "\nCitta: "+getCitta()+ "\nCap: "+getCap()+ "\nTelefono: "+getTelefono()+ 
               "\nEmail: "+getEmail()+ "\nIndirizzo: "+ getIndirizzo()+ "\nPartita Iva: "+ getPiva()+ "\nAffidabilita: "+getAffidabilita());
    } 
    public String toStringBold(){
        return("  "+getRagSoc()+" "+getCitta()+" "+getCap());
    }
    public String toStringNorm(){
        return("  "+getEmail()+" "+" "+getTelefono()+" "+getIndirizzo()+" "+getPiva()); 
    }
     public String toString(){
        return("Ragione sociale: "+ getRagSoc()+ "Citta: "+getCitta()+ "Cap: "+getCap()+ "Telefono: "+getTelefono()+ 
               "Email: "+getEmail()+ "Indirizzo: "+ getIndirizzo()+ "Partita Iva: "+ getPiva()+ "Affidabilita: "+getAffidabilita()); 
    }
    
   
    public static  final String xmlFilePath(){
       String p=getProperty("user.home")+getProperty("file.separator");
       String x=p+"rubrica.xml";
       return x;
    }
    

    public static void create() throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException {
        File file=new File(xmlFilePath());
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rubrica=doc.createElement("rubrica");
        doc.appendChild(rubrica);
        for(int i=0; i<listClienti.size();i++){
    
            Element cliente= doc.createElement("cliente");
                rubrica.appendChild(cliente);
        
            Element ragsoc = doc.createElement("ragsoc");
            ragsoc.appendChild(doc.createTextNode(listClienti.get(i).getRagSoc()));
                cliente.appendChild(ragsoc);
        
            Element indirizzo = doc.createElement("indirizzo");
            indirizzo.appendChild(doc.createTextNode(listClienti.get(i).getIndirizzo()));
                cliente.appendChild(indirizzo);
        
            Element citta = doc.createElement("citta");
            citta.appendChild(doc.createTextNode(listClienti.get(i).getCitta()));
                cliente.appendChild(citta);
        
            Element cap = doc.createElement("cap");
            cap.appendChild(doc.createTextNode(String.valueOf(listClienti.get(i).getCap())));
                cliente.appendChild(cap);
        
            Element telefono = doc.createElement("telefono");
            telefono.appendChild(doc.createTextNode(String.valueOf(listClienti.get(i).getTelefono())));
                cliente.appendChild(telefono);
        
            Element piva = doc.createElement("piva");
            piva.appendChild(doc.createTextNode(listClienti.get(i).getPiva()));
                cliente.appendChild(piva);
        
            Element mail  = doc.createElement("email");
            mail.appendChild(doc.createTextNode(listClienti.get(i).getEmail()));
                cliente.appendChild(mail);
        
            Element affidabilita = doc.createElement("affidabilita");
            affidabilita.appendChild(doc.createTextNode(String.valueOf(listClienti.get(i).getAffidabilita())));
                cliente.appendChild(affidabilita);
        
        
            Element note = doc.createElement("note");
            for(int k=0;k<listClienti.get(i).getNote().size();k++){
                if(k==listClienti.get(i).getNote().size()-1){
                note.appendChild(doc.createTextNode(listClienti.get(i).getNote().get(k)));
            }
                else{
                note.appendChild(doc.createTextNode(listClienti.get(i).getNote().get(k)+"/"));
                }
            }   
               cliente.appendChild(note);
        
        }
            


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFilePath());
        transformer.transform(source, result);
        
    }
        
   
    public static ObservableList<Cliente> leggiXml () throws TransformerException, SAXException, IOException, ParserConfigurationException{
    
       try{
           File file= new File(Cliente.xmlFilePath());
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc= db.parse(file);
           doc.getDocumentElement().normalize();
           NodeList nodelist = doc.getElementsByTagName("cliente");
           
           for( int t=0;t< nodelist.getLength();t++){
              Node node = nodelist.item(t);
              if(node.getNodeType()== Node.ELEMENT_NODE){
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
                listClienti.add(b);
              } //fine if
           }//fine for
             
        }catch(FileNotFoundException E){
         Cliente.newFile();
        }
        return listClienti;
    }//fine metodo 
    
    public static ObservableList<Cliente> newFile() throws TransformerException, SAXException, IOException, ParserConfigurationException{
         
      File file= new File(Cliente.xmlFilePath());
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc= db.newDocument();
           
        Element rubrica=doc.createElement("rubrica");
            doc.appendChild(rubrica);
 
        Element cliente= doc.createElement("cliente");
            rubrica.appendChild(cliente);
        
        Element ragsoc = doc.createElement("ragsoc");
        ragsoc.appendChild(doc.createTextNode("Cliente Esempio"));
            cliente.appendChild(ragsoc);
        
        Element indirizzo = doc.createElement("indirizzo");
        indirizzo.appendChild(doc.createTextNode("Via Napoli"));
            cliente.appendChild(indirizzo);
        
        Element citta = doc.createElement("citta");
        citta.appendChild(doc.createTextNode("Genova"));
            cliente.appendChild(citta);
        
        Element cap = doc.createElement("cap");
        cap.appendChild(doc.createTextNode("16100"));
            cliente.appendChild(cap);
        
        Element telefono = doc.createElement("telefono");
        telefono.appendChild(doc.createTextNode("3319263164"));
            cliente.appendChild(telefono);
        
        Element piva = doc.createElement("piva");
        piva.appendChild(doc.createTextNode("123456789"));
            cliente.appendChild(piva);
        
        Element mail  = doc.createElement("email");
        mail.appendChild(doc.createTextNode("fieschi@gmail.com"));
            cliente.appendChild(mail);
        
        Element affidabilita = doc.createElement("affidabilita");
        affidabilita.appendChild(doc.createTextNode("7"));
            cliente.appendChild(affidabilita);
        
        
        Element note = doc.createElement("note");
        note.appendChild(doc.createTextNode("Cliente affidabile / Sede a Milano"));
            cliente.appendChild(note);
        
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(xmlFilePath()));
      transformer.transform(source, result);
      NodeList nodelist = doc.getElementsByTagName("cliente");
      for( int t=0;t< nodelist.getLength();t++){
        Node node = nodelist.item(t);
        if(node.getNodeType()== Node.ELEMENT_NODE){
            Element element = (Element) node;
            ArrayList<String> arrNote = new ArrayList<String>();
            String ragSoc=(element.getElementsByTagName("ragsoc").item(0).getTextContent());
            String ind =(element.getElementsByTagName("indirizzo").item(0).getTextContent());
            String paiva=(element.getElementsByTagName("piva").item(0).getTextContent());
            String citta1=(element.getElementsByTagName("citta").item(0).getTextContent());
            String telefono1=element.getElementsByTagName("telefono").item(0).getTextContent();
            String email=(element.getElementsByTagName("email").item(0).getTextContent());
            int cap1=(Integer.parseInt(element.getElementsByTagName("cap").item(0).getTextContent()));
            int aff=(Integer.parseInt(element.getElementsByTagName("affidabilita").item(0).getTextContent()));
            String note1=(element.getElementsByTagName("note").item(0).getTextContent());
            String [] ciao=note1.split("/");
            for(int g=0;g<ciao.length;g++){
                arrNote.add(ciao[g]);
            }
            Cliente b=new Cliente( aff, cap1, email, ragSoc, paiva, telefono1, ind, citta1,arrNote);
            listClienti.add(b);     
        }        
      }
     return listClienti;
    }
}