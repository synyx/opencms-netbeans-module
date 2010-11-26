
/*
 * DialogDataGet.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/*
 * Action to parses DialogData.xml file, return XML document and get the values of the nodes path, username and password
 * for the instances of UploadDialog class.
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a> Synyx GmbH
 * & Co. KG Karlsruhe, Germany
 */
public class DialogDataGet {
    
    /**
     * The root element of the DialogData.xml
     */
    private Node dialogDataNode;
    /**
     * Netbeans Logger.
     */
    private static final Logger log = Logger.getLogger(DialogDataGet.class.toString());
    
    /**
     * Absolute path to xmlFile
     */
    private String xmlFile = null;
    
    /**
     * Constructor for DialogDataGet class. Proves if XML File DialogData.xml exists and if not creates a file and
     * writes the data into it.
     * @param xmlFile the File to persist to
     */
    public DialogDataGet(String xmlFile) {
        this.xmlFile = xmlFile;
        File file = new File(xmlFile);
        boolean exists = file.exists();
        
        if (exists) {
            log.info(xmlFile.concat(" exists"));
            parseXml();
        } else {
            try {
                log.info(xmlFile + " doesn't exist");
                log.info("Creating DialogData.xml and writing data.");
                if (file.getParentFile() != null && ! file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                
                // Create file if it does not exist
                file.createNewFile();
                PrintWriter out = new PrintWriter(new FileOutputStream(xmlFile));
                out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!--");
                out.println("Document   : DialogData.xml");
                out.println("Description:");
                out.println("Saves the data from Dialog.-->");
                out.println("<root>");
                out.println("<path>system</path>");
                out.println("<type> </type>");
                out.println("<publish> </publish>");
                out.println("<username>Admin</username>");
                out.println("<password>admin</password>");
                out.println("<cmsApp>http://localhost:8080/opencms/UploadServlet</cmsApp>");
                out.println("</root>");
                out.close();
                parseXml();
            } catch (IOException e) { log.log(Level.SEVERE, e.getMessage());
            log.info(e.getMessage()); }
            
        }
    }
    /**
     * Open DialogData.xml for parsing and initialize a XML Document.
     */
    private void parseXml() {
        
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(xmlFile));
            // normalize text representation
            doc.getDocumentElement().normalize();
            NodeList dialogData = doc.getElementsByTagName("root");
            dialogDataNode = dialogData.item(0);
        } catch (SAXParseException err) {
            log.log(Level.SEVERE, err.getMessage(), err);
        } catch (SAXException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        } catch (Throwable t) {
            log.log(Level.SEVERE, t.getMessage(), t);
        }
    }
    
    
    /**
     * Get the value of the node path from XML Document.
     * @return path
     */
    public String getDialogValuePath() {
        String path = "";
        if (dialogDataNode.getNodeType() == Node.ELEMENT_NODE) {
            Element firstDialogDataElement = (Element) dialogDataNode;
            NodeList pathList = firstDialogDataElement.getElementsByTagName("path");
            Element pathElement = (Element) pathList.item(0);
            NodeList textPathList = pathElement.getChildNodes();
            path = (textPathList.item(0)).getNodeValue().trim(); }
        return path; }
    
    
    /**
     * Get the value of the node username from from XML Document.
     * @return username
     */
    public String getDialogValueUsername() {
        String username = "";
        if (dialogDataNode.getNodeType() == Node.ELEMENT_NODE) {
            Element firstDialogDataElement = (Element) dialogDataNode;
            NodeList usernameList = firstDialogDataElement.getElementsByTagName("username");
            Element usernameElement = (Element) usernameList.item(0);
            NodeList textUsernameList = usernameElement.getChildNodes();
            username = (textUsernameList.item(0)).getNodeValue().trim(); }
        return username; }
    
    
    /**
     * Get thee value of the node password from from XML Document.
     * @return password
     */
    public String getDialogValuePassword() {
        String password = "";
        if (dialogDataNode.getNodeType() == Node.ELEMENT_NODE) {
            Element firstDialogDataElement = (Element) dialogDataNode;
            NodeList passwordList = firstDialogDataElement.getElementsByTagName("password");
            Element passwordElement = (Element) passwordList.item(0);
            NodeList textPasswordList = passwordElement.getChildNodes();
            password = (textPasswordList.item(0)).getNodeValue().trim(); }
        return password; }
    
    /**
     * Get thee value of the node cms from from XML Document.
     * @return cms
     */
    public String getDialogValueCms() {
        String cms = "";
        if (dialogDataNode.getNodeType() == Node.ELEMENT_NODE) {
            Element firstDialogDataElement = (Element) dialogDataNode;
            NodeList cmsList = firstDialogDataElement.getElementsByTagName("cmsApp");
            Element cmsElement = (Element) cmsList.item(0);
            NodeList textcmsList = cmsElement.getChildNodes();
            cms = (textcmsList.item(0)).getNodeValue().trim(); }
        return cms; }
    
    
    /**
     * Get thee value of the node type from from XML Document.
     * @return type
     */
    public String getDialogValueType() {
        String type = "";
        if (dialogDataNode.getNodeType() == Node.ELEMENT_NODE) {
            Element firstDialogDataElement = (Element) dialogDataNode;
            NodeList typeList = firstDialogDataElement.getElementsByTagName("type");
            Element typeElement = (Element) typeList.item(0);
            NodeList texttypeList = typeElement.getChildNodes();
            type = (texttypeList.item(0)).getNodeValue().trim(); }
        return type; }
        
    /**
     * Get thee value of the node publish from from XML Document.
     * @return publish
     */
    public String getDialogValuePublish() {
        String publish = "";
        if (dialogDataNode.getNodeType() == Node.ELEMENT_NODE) {
            Element firstDialogDataElement = (Element) dialogDataNode;
            NodeList publishList = firstDialogDataElement.getElementsByTagName("publish");
            Element publishElement = (Element) publishList.item(0);
            NodeList textpublishList = publishElement.getChildNodes();
            publish = (textpublishList.item(0)).getNodeValue().trim(); }
        return publish; }
    
}