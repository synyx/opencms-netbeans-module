/*
 * GetProjectData.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import org.openide.util.NbBundle;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/*
 * Action to 
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a> Synyx GmbH
 * & Co. KG Karlsruhe, Germany
 */


public class GetProjectData {
    /**
     * The root element of the GetProjectData.xml
     */
    private Node dialogDataNode;
    /**
     * Initialize Log File
     */
//    private Jdk14Logger log = new Jdk14Logger(this.log.toString());
    
    String xmlFile = (System.getProperty("netbeans.user")) + "/var/OpenCms/ProjectData.xml";
    String projectData = "";
    String cmsData = "";
    
    private Logger log = Logger.getLogger(this.getClass().toString());
    

    /**
     * Creates a new instance of GetProjectData
     */
    public GetProjectData(String project) {
        
        boolean exists = (new File(xmlFile)).exists();
        if (exists) {
            log.info(xmlFile + " exists");
        } else {
            try {
                log.info(xmlFile + " doesn't exist");
                log.info("Creating ProjectData.xml and writing data.");
                // Create a directory
                try {boolean success = (new File(System.getProperty("netbeans.user") + "/var/OpenCms/")).mkdir();
                } catch (Exception e) {
                    log.info(" " + e.getMessage());
                    log.log(log.getLevel().SEVERE, e.getMessage());
                }
                
                File file = new File(xmlFile);
                // Create file if it does not exist
                file.createNewFile();
                PrintWriter out = new PrintWriter(new FileOutputStream(xmlFile));
                out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!--");
                out.println("Document   : ProjectData.xml");
                out.println("Description:");
                out.println("Saves projects data.-->");
                out.println("<root>");
                out.println("<path>system</path>");
                out.println("</root>");
                out.close();
            } catch (IOException e) {
               log.log(log.getLevel().SEVERE, e.getMessage());
               log.info(e.getMessage());
            }
        }
        
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(xmlFile));
            // normalize text representation
            doc.getDocumentElement().normalize();
            NodeList dialogData = doc.getElementsByTagName("root");
            dialogDataNode = dialogData.item(0);
        } catch (SAXParseException err) {
            log.info("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            log.info(" " + err.getMessage());
            log.log(log.getLevel().SEVERE, err.getMessage());
            
        } catch (SAXException e) {
            Exception x = e.getException();
            log.info(((x == null) ? e : x).getMessage());
            log.log(log.getLevel().SEVERE, e.getMessage());
        } catch (Throwable t) {
            log.info(" " + t.getMessage());
            log.log(log.getLevel().SEVERE, t.getMessage());
        }
        if (dialogDataNode.getNodeType() == Node.ELEMENT_NODE) {
            Element firstDialogDataElement = (Element) dialogDataNode;
            NodeList projectList = firstDialogDataElement.getElementsByTagName(project);
            if (projectList != null) {
                JMenuBar menuBar;
                // Create a new UploadDialog object with path as a parameter
                ProjectDialog dialog = new ProjectDialog();
                // Set the dialog title
                dialog.setTitle(NbBundle.getMessage(UploadDialog.class, "BarTitle"));
                // Create a new JMenuBar object
                menuBar = new JMenuBar();
                menuBar.add(new JMenu(NbBundle.getMessage(ProjectDialog.class, "BarFile"))).
                        add(new JMenuItem(NbBundle.getMessage(ProjectDialog.class, "BarExit")));
                // Set JMenuBar object into the dialog
                dialog.setJMenuBar(menuBar);
                // Set size of the dialog window
                dialog.setSize(200, 200);
                // Set dialog location
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                // Make dialog visible
                dialog.setVisible(true);
                projectData = dialog.getValueProjectData();
            } else {
                
                //getValueProjectName(projectList);
                
            }
        }
        
        
    }
    
    public String getNewValueProjectName(NodeList projectList) {
        return projectData;
       }
    
    public String getValueProjectName(NodeList projectList) {
        Element projectElement = (Element) projectList.item(0);
        NodeList textProjectList = projectElement.getChildNodes();
        projectData = ((Node) textProjectList.item(0)).getNodeValue().trim();
        return projectData;
       }
       
}
