/*
 * FolderUpload.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/*
 * Action to create a  dialog window for OpenCms folder upload, invoke FileUplod class and pass
 * dialog input data.
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a> Synyx GmbH
 * & Co. KG Karlsruhe, Germany
*/
class FolderUploadDialog extends javax.swing.JDialog {
    
    // Initialize dailogs elements.
    private JPanel jPanel1 = new JPanel();
    private JLabel jLabel1 = new javax.swing.JLabel();
    private JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
    private JTextArea fileList = new javax.swing.JTextArea();
    private JTextField cmsPath = new javax.swing.JTextField();
    private JTextField passw = new javax.swing.JTextField();
    private JTextField cmsApp = new javax.swing.JTextField();
    private JButton upload = new javax.swing.JButton();
    private JTextField user = new javax.swing.JTextField();
    private JCheckBox publishing = new javax.swing.JCheckBox();
    private JLabel jLabel2 = new javax.swing.JLabel();
    private JLabel jLabel3 = new javax.swing.JLabel();
    private JLabel jLabel4 = new javax.swing.JLabel();
    private JLabel jLabel5 = new javax.swing.JLabel();
    private JLabel jLabel6 = new javax.swing.JLabel();
    private JLabel jLabel7 = new javax.swing.JLabel();
    
    private String path, username, password, cms, cvspath;
    
    private String pathXml = "";
    
    private boolean publish = false;
    
    private FileTreeNode rootNode = null;
    /**
     * Netbeans Logger.
     */
    private Logger log = Logger.getLogger(this.getClass().toString());
    
    // Create new form NewJDialog
    /**
     * Constructor FolderUploadDialog without parameters.
     */
    public FolderUploadDialog() {
        
        initComponents();
    }
    /**
     * Constructor FolderUploadDialog.
     *
     *@param rootNode
     *@param cvspath
     **/
    public FolderUploadDialog(FileTreeNode rootNode, String cvspath) {
        this.rootNode = rootNode;
        this.cvspath = cvspath;
        initComponents();
    }
    /**
     * Initialize OpenCmsFileUpload dialog components.
     */
    private void initComponents() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        // Set JPanel name and borders
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        // Set text for the labels
        jLabel1.setText(NbBundle.getMessage(UploadDialog.class, "titleLabel"));
        jLabel2.setText(NbBundle.getMessage(UploadDialog.class, "fileListLabel"));
        jLabel3.setText(NbBundle.getMessage(UploadDialog.class, "filepathLabel"));
        jLabel4.setText(NbBundle.getMessage(UploadDialog.class, "publishLabel"));
        jLabel5.setText(NbBundle.getMessage(UploadDialog.class, "usernameLable"));
        jLabel6.setText(NbBundle.getMessage(UploadDialog.class, "passwordLabel"));
        jLabel7.setText(NbBundle.getMessage(UploadDialog.class, "cmsLabel"));
        
        //  Set the name of the file to upload
        fileList.setColumns(20);
        fileList.setRows(5);
        jScrollPane1.setViewportView(fileList);
        
        fileList.setText(getFileListMessage(rootNode, 0));
        
        // Set cmsPath layout
        cmsPath.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        cmsPath.getAccessibleContext().setAccessibleName("path");
        
        // Set publishing layout
        publishing.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        publishing.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        
        // Set Upload button text and add ActionListener
        upload.setText("Upload");
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });
        
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        // Set access names for file, user and passw
        
        fileList.getAccessibleContext().setAccessibleName("fileList");
        
        user.getAccessibleContext().setAccessibleName("username");
        
        passw.getAccessibleContext().setAccessibleName("password");
        
        cmsApp.getAccessibleContext().setAccessibleName("cms");
        
        // TODO refactor
        DialogDataGet dialogData = new DialogDataGet("");
        
        if (cvspath.equalsIgnoreCase("null")) {
            cmsPath.setText(dialogData.getDialogValuePath());
        } else {
            cmsPath.setText(cvspath);
            pathXml = dialogData.getDialogValuePath();
        }
        user.setText(dialogData.getDialogValueUsername());
        passw.setText(dialogData.getDialogValuePassword());
        cmsApp.setText(dialogData.getDialogValueCms());
        
        // Set JPanel Layout
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .add(29, 29, 29)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabel7)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jLabel6)
                .add(jLabel4)
                .add(jLabel5))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 38, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(user, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(passw, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(cmsApp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 252, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(publishing)
                .add(cmsPath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 252, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(upload)))
                .add(jPanel1Layout.createSequentialGroup()
                .add(145, 145, 145)
                .add(jLabel1))
                .add(jPanel1Layout.createSequentialGroup()
                .add(160, 160, 160)
                .add(jLabel2))
                .add(jPanel1Layout.createSequentialGroup()
                .add(63, 63, 63)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 298, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
                );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .add(18, 18, 18)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(cmsPath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabel3))
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                .add(jLabel4)
                .add(publishing))
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel5)
                .add(user, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel6)
                .add(passw, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel7)
                .add(cmsApp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(43, 43, 43)
                .add(upload)
                .addContainerGap())
                );
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(443, Short.MAX_VALUE))
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
                );
        pack();
    }
    
    /**
     * Set method for cmsPath.
     */
    public void setCmsPath(JTextField cmsPath) {
        this.cmsPath = cmsPath;
    }
    
    /**
     * Get method for cmsPath.
     */
    public JTextField getCmsPath() {
        return cmsPath;
    }
    
    /**
     * Method for folder upload activation.
     */
    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {
        // Initialize parameter for fileUpload method.
        this.path = cmsPath.getText();
        // Get value of the selected item in checkbox
        this.publish = publishing.isSelected();
        // Get the value of username and password
        this.username = user.getText();
        this.password = passw.getText();
        this.cms = cmsApp.getText();
        
        // Call fileUpload Method with parameters
        String message = folderUpload(rootNode, 0);
        
        if (!(cvspath.equalsIgnoreCase("null"))) {
            path = pathXml;
        }
        // Save parameters for later recovery
        // TODO use dao
        //DialogDataSet dialogData = new DialogDataSet(path, username, password, cms, "default", "false");
        this.setVisible(false);
        
        // show result message
        NotifyDescriptor descriptorInform = new NotifyDescriptor.Message(message, NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(descriptorInform);
    }
    
    
    /**
     * Method to activate FileUpload and set the values for its variables.
     * 
     * @return message
     */
    private String folderUpload(FileTreeNode node, int level) {
        String message = "";
        if (node.isDirectory()) {
            message += createVFSFolder(node, level);
            for (Iterator contentIt = node.getChildNodes().iterator(); contentIt.hasNext();) {
                message += folderUpload((FileTreeNode) contentIt.next(), level + 1);
            }
        } else {
            message += createVFSFile(node, level);
        }
        return message;
    }
    
     /**
     * Method to initialize values of the class variables of the class FileUpload 
     * and call a class method executeFolderUpload.
     * 
     * @return message
     */
    private String createVFSFolder(FileTreeNode directoryNode, int level) {
   
        /*
        String message = "";
        String parentPath = directoryNode.getParentPath(level);
        File directory = directoryNode.getFile();
        FileUpload upload = new FileUpload();
        upload.setfileToUpload(directory.getName());
        upload.setPublish("false");
        upload.setFileType("default");
        upload.setDirectory(parentPath + directory.getName());
        upload.setIsDirectory("true");
        upload.setOpenCmsPath(path);
        upload.setUsername(username);
        upload.setPassword(password);
        upload.setUploadUrl(cms);
        upload.setAbsFilename(directory.getAbsolutePath());
        
        log.info("FILEUPLOAD: Creating Directory " + directory.getAbsolutePath());
        
        
        try {
            message = upload.executeFolderUpload();
        } catch (Exception ex) {
            NotifyDescriptor descriptor = new NotifyDescriptor.Message(ex.getMessage(), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(descriptor);
            log.log(log.getLevel().SEVERE, ex.getMessage());
            log.info(ex.getMessage());
        }
        
        return message;
         */
        return null;
    }
    
     /**
     * Method to initialize values of the class variables of the class FileUpload 
     * and call a class method executeFolderUpload.
     * 
     * @return message
     */
    private String createVFSFile(FileTreeNode fileNode, int level) {
        
        String message = "";
        String parentPath = fileNode.getParentPath(level);
        File file = fileNode.getFile();
        
        String fileEnding = file.getName().substring(file.getName().indexOf(".") + 1);
        String fileType = (String) (getFileTypeHashmap().get(fileEnding));
        if (fileType == null) {
            fileType = "default";
        }
        
        FileUpload upload = new FileUpload();
        upload.setfileToUpload(file.getName());
        upload.setPublish(publish);
        /*
        upload.setFileType(fileType);
        upload.setDirectory(parentPath);
        upload.setIsDirectory("false");
        upload.setOpenCmsPath(path);
        upload.setUsername(username);
        upload.setPassword(password);
        upload.setUploadUrl(cms);
        upload.setAbsFilename(file.getAbsolutePath());
        */
        log.info("FILEUPLOAD: Creating File " + file.getAbsolutePath());
        
        try {
            //FileUpload.UploadResponse response = upload.executeFolderUpload();
        } catch (Exception ex) {
            NotifyDescriptor descriptor = new NotifyDescriptor.Message(ex.getMessage(), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(descriptor);
            log.log(log.getLevel().SEVERE, ex.getMessage());
            log.info(ex.getMessage());
        }
        
        return message;
    }
    
    private static final HashMap getFileTypeHashmap() {
        HashMap fileTypeMap = new HashMap();
        
        // mapping key: file ending - value: file type
        fileTypeMap.put("jsp", "jsp");
        fileTypeMap.put("png", "img");
        fileTypeMap.put("gif", "img");
        fileTypeMap.put("jpg", "img");
        fileTypeMap.put("bin", "bin");
        fileTypeMap.put("xml", "xml");
        
        return fileTypeMap;
    }
    
    private String getFileListMessage(FileTreeNode node, int level) {
        String message = "";
        
        if (node.isDirectory()) {
            message += "[" + node.getParentPath(level) + node.getFile().getName() + "]\n";
            for (Iterator contentIt = node.getChildNodes().iterator(); contentIt.hasNext();) {
                message += getFileListMessage((FileTreeNode) contentIt.next(), level + 1);
            }
        } else {
            for (int i = 0; i < level * 2; i++) {
                message += " ";
            }
            message += node.getFile().getName() + "\n";
        }
        
        return message;
    }
}