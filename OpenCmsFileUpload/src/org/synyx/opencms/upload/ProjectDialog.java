/*
 * ProjectDialog.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import org.xml.sax.SAXParseException;


/*
 * Action to connect with HTTPServer and sends the request data.
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a> Synyx GmbH
 * & Co. KG Karlsruhe, Germany
 */


public class ProjectDialog extends javax.swing.JDialog {
    
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    
    String netbeansPath, cmsPath, ok, netbeans;
    String cms = null;
    boolean result;
    Document doc;
    
      private Logger log = Logger.getLogger(this.getClass().toString());
        
    /** Creates a new instance of ProjectDialog */
    public ProjectDialog() {
        
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        
        jTextField1.getAccessibleContext().setAccessibleName("netbeansPath");
        jTextField2.getAccessibleContext().setAccessibleName("cmsPath");
        jButton1.getAccessibleContext().setAccessibleName("ok");
        
        jCheckBox1.setText("No");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        jCheckBox2.setText("Yes");
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        jLabel1.setText("Project Path");
        
        jLabel2.setText("OpenCms Path");
        
        jButton1.setText("Ok");
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            
            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (jCheckBox1.isSelected()) {
                    cms = "none";
                    
                } else {
                    if (jCheckBox2.isSelected()) {
                        netbeans = jTextField1.getText();
                        cms = jTextField2.getText();
                    }
                }
            }
        });
        
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jCheckBox1)
                .addContainerGap(279, Short.MAX_VALUE))
                .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jCheckBox2)
                .addContainerGap(274, Short.MAX_VALUE))
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jLabel1)
                .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 32, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .add(jButton1)
                .addContainerGap())
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .add(jTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .add(27, 27, 27))
                .add(jPanel1Layout.createSequentialGroup()
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 162, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()))))
                );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jCheckBox1)
                .add(18, 18, 18)
                .add(jCheckBox2)
                .add(21, 21, 21)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel1)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel1Layout.createSequentialGroup()
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2)))
                .add(20, 20, 20)
                .add(jButton1)
                .addContainerGap())
                );
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
                );
    }
    
    
    public String getValueProjectData() {
        while (cms == null);
        if (!(cms.equalsIgnoreCase("none"))) {
            setValueProjectData();
        }
        return cms;
    }
    
    
    private void setValueProjectData() {
        
        String xmlFile = (System.getProperty("netbeans.user")) + "/var/OpenCms/ProjectData.xml";
        
        try {
            // initialize a XML Document
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new File(xmlFile));
            // normalize text representation
            doc.getDocumentElement().normalize();
            
            NodeList dialogData = doc.getElementsByTagName("root");
            doc.createElement(netbeans);
            
            for (int i=0;i<dialogData.getLength();i++) {
                Element newel = doc.createElement(netbeans);
                Node newelText = doc.createTextNode(cms);
                newel.appendChild(newelText);
                dialogData.item(i).appendChild(newel);
            }
            
            
        } catch (SAXParseException err) {
            log.info("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            log.info(" " + err.getMessage());
           log.log(log.getLevel().SEVERE, err.getMessage());
            
        } catch (SAXException e) {
            Exception x = e.getException();
            log.log(log.getLevel().SEVERE, ((x == null) ? e : x).toString());
            
        } catch (Throwable t) {
            log.log(log.getLevel().SEVERE, t.getMessage());
        }
        log.info("Saving XML file... " + xmlFile);
        // open output stream where XML Document will be saved
        File xmlOutputFile = new File(xmlFile);
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
            log.log(log.getLevel().SEVERE, e.getMessage());
        }
        log.info("XML file saved.");
        
    }
    
}









