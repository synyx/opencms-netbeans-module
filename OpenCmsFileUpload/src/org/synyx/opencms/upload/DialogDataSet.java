
/*
 * DialogDataSet.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DialogDataSet {
    
    /**
     * Netbeans Logger.
     */
    private static final Logger log = Logger.getLogger(DialogDataSet.class.getName());

    private String xmlFile = null;
    
    /**
     * Sets the new values of the nodes cms, path, username and password from the XML Document of.
     * the XML file DialogData.xml
     * @param path The value to set of the node path of the XML Document     *
     * @param username The value to set of the node username of the XML Document
     * @param password The value to set of the node password of the XML Document
     * @param cmsApp The value to set of the node cmsApp of the XML Document
     * @param type The value to set of the node type of the XML Document
     * @param publish The value to set of the node publish of the XML Document
     */
    protected DialogDataSet(String xmlFile, String path, String username, String password, String cmsApp, String type, String publish) {
        this.xmlFile = xmlFile;
        try {
            // initialize a XML Document
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(xmlFile));
            // normalize text representation
            doc.getDocumentElement().normalize();
            NodeList dialogData = doc.getElementsByTagName("root");
            Node dialogDataNode = dialogData.item(0);
            
            
            if (dialogDataNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element firstDialogDataElement = (Element) dialogDataNode;
                // set a new value of the node path
                NodeList pathList = firstDialogDataElement.getElementsByTagName("path");
                Element pathElement = (Element) pathList.item(0);
                NodeList textPathList = pathElement.getChildNodes();
                textPathList.item(0).setNodeValue(path);
                // set a new value of the node username
                NodeList usernameList = firstDialogDataElement.getElementsByTagName("username");
                Element usernameElement = (Element) usernameList.item(0);
                NodeList textUsernameList = usernameElement.getChildNodes();
                textUsernameList.item(0).setNodeValue(username);
                // set a new value of the node password
                NodeList passwordList = firstDialogDataElement.getElementsByTagName("password");
                Element passwordElement = (Element) passwordList.item(0);
                NodeList textPasswordList = passwordElement.getChildNodes();
                textPasswordList.item(0).setNodeValue(password);
                // set a new value of the node cms
                NodeList cmsList = firstDialogDataElement.getElementsByTagName("cmsApp");
                Element cmsElement = (Element) cmsList.item(0);
                NodeList textcmsList = cmsElement.getChildNodes();
                textcmsList.item(0).setNodeValue(cmsApp);
                // set a new value of the node type
                NodeList typeList = firstDialogDataElement.getElementsByTagName("type");
                Element typeElement = (Element) typeList.item(0);
                NodeList texttypeList = typeElement.getChildNodes();
                texttypeList.item(0).setNodeValue(type);
                // set a new value of the node publish
                NodeList publishList = firstDialogDataElement.getElementsByTagName("publish");
                Element publishElement = (Element) publishList.item(0);
                NodeList textpublishList = publishElement.getChildNodes();
                textpublishList.item(0).setNodeValue(publish);
            }
            saveXMLDocument(xmlFile, doc);
            
        } catch (SAXParseException err) {
            log.log(Level.SEVERE, err.getMessage(), err);
            
        } catch (SAXException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            
        } catch (Throwable t) {
            log.log(Level.SEVERE, t.getMessage(), t);
        }
    }
    
    
    /**
     * Saves XML Document into XML file DialogData.xml.
     * @param fileName The name of the XML file to save
     * @param doc XML Document to save in XML file
     */
    public final void saveXMLDocument(String fileName, Document doc) {
        log.info("Saving XML file... " + xmlFile);
        // open output stream where XML Document will be saved
        File xmlOutputFile = new File(fileName);
        FileOutputStream fos = null;
        Transformer transformer = null;
        try {
            fos = new FileOutputStream(xmlOutputFile);
        } catch (FileNotFoundException e) {
            log.info("Error occured: " + e.getMessage());
            log.log(log.getLevel().SEVERE, e.getMessage());
        }
        // Use a Transformer for output
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            log.info("Transformer configuration error: " + e.getMessage());
            log.log(log.getLevel().SEVERE, e.getMessage());
            
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(fos);
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            log.info("Error transform: " + e.getMessage());
            log.info(e.getCause().toString());
            log.log(log.getLevel().SEVERE, e.getMessage());
        }
        log.info("XML file saved.");
        
    }
}
