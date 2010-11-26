/*
 * UploadDialog.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;



import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/*
 * Action to create a  dialog window for OpenCms File Upload, invoke FileUplod class and pass the
 * dialog input data.
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a> Synyx GmbH
 * & Co. KG Karlsruhe, Germany
 */
/*
 * Action to create a  dialog window for OpenCms File Upload, invoke FileUplod class and pass the
 * dialog input data.
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a> Synyx GmbH
 * & Co. KG Karlsruhe, Germany
 */

class UploadDialog extends javax.swing.JDialog {
    
    
    // Initialize dailogs elements.
    private JPanel titlePanel = new JPanel();
    private JLabel filenameLabel = new JLabel();
    private JLabel filepathLabel = new JLabel();
    private JTextField vfsPathField = new JTextField();
    private JLabel publishLabel = new JLabel();
    private JCheckBox publishing = new JCheckBox();
    private JLabel fileTypeLabel = new JLabel();
    private JComboBox type = new JComboBox();
    private JButton upload = new JButton();
    private JLabel titleLabel = new JLabel();
    private JTextField localFileField = new JTextField();
    private JLabel usernameLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();
    private JLabel cmsLabel = new JLabel();
    private JTextField userField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JTextField uploadUrlField = new JTextField();
    
    private FileTypeConfiguration configuration = new PropertyFileTypeConfiguration();

    private DialogData initialData = null;
    
    private DialogDao dialogDao = null;
    
    /**
     * Netbeans Logger.
     */
    private Logger log = Logger.getLogger(this.getClass().toString());
    
    /**
     * Constructor UploadDialog with a string parameter.
     * @param dialogData 
     */
    public UploadDialog(DialogDao dao, DialogData dialogData) {
        this.initialData = dialogData;
        this.dialogDao = dao;
        initComponents();
    }
    
    
    /**
     * Initialize OpenCmsFileUpload dialog components.
     */
    private void initComponents() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Set JPanel name and borders
        titlePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        // Set text for the labels
        titlePanel.setName(NbBundle.getMessage(UploadDialog.class, "titleLabel"));
        
        filenameLabel.setText(NbBundle.getMessage(UploadDialog.class, "filenameLabel"));
        filepathLabel.setText(NbBundle.getMessage(UploadDialog.class, "filepathLabel"));
        publishLabel.setText(NbBundle.getMessage(UploadDialog.class, "publishLabel"));
        fileTypeLabel.setText(NbBundle.getMessage(UploadDialog.class, "filetypeLabel"));
        titleLabel.setText(NbBundle.getMessage(UploadDialog.class, "titleLabel"));
        usernameLabel.setText(NbBundle.getMessage(UploadDialog.class, "usernameLable"));
        passwordLabel.setText(NbBundle.getMessage(UploadDialog.class, "passwordLabel"));
        cmsLabel.setText(NbBundle.getMessage(UploadDialog.class, "cmsLabel"));
        
        // Set cmsPath layout
        vfsPathField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        vfsPathField.getAccessibleContext().setAccessibleName("path");
        vfsPathField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keyAction(e);
            }
        });
        
        // Set publishing layout
        publishing.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        publishing.setMargin(new java.awt.Insets(0, 0, 0, 0));

        // TODO use configurable names
        type.setModel(new javax.swing.DefaultComboBoxModel(configuration.getTypes().toArray()));
        /*
        type.setSelectedItem(configuration.getType(dialogData.getDialogValueType()));
        */
        // Set Upload button text and add ActionListener
        upload.setText(NbBundle.getMessage(UploadDialog.class, "uploadButton"));
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadAction();
            }
        });
        
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        // Set access names for file, user and passw
        
        localFileField.getAccessibleContext().setAccessibleName("fileName");
        localFileField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keyAction(e);
            }
        });
        
        userField.getAccessibleContext().setAccessibleName("username");
        userField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keyAction(e);
            }
        });

        passwordField.getAccessibleContext().setAccessibleName("password");
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keyAction(e);
            }
        });
        
        uploadUrlField.getAccessibleContext().setAccessibleName("cms");
        uploadUrlField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keyAction(e);
            }
        });

        // initialize the values
        if (initialData != null) {
            vfsPathField.setText(initialData.getVfsResource().getVfsPath());
            publishing.setSelected(initialData.isPublish());
            localFileField.setText(initialData.getLocalPath());
            userField.setText(initialData.getUsername());
            passwordField.setText(initialData.getPassword());
            uploadUrlField.setText(initialData.getUploadUrl());
            type.setSelectedItem(initialData.getVfsResource().getType());
        }
        
        // Set JPanel Layout
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(titlePanel);
        titlePanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .add(127, 127, 127)
                .add(titleLabel)
                .addContainerGap(154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(filenameLabel)
                .add(fileTypeLabel)
                .add(filepathLabel)
                .add(publishLabel)
                .add(usernameLabel)
                .add(passwordLabel)
                .add(cmsLabel))
                .add(50, 50, 50)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .add(userField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(vfsPathField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(localFileField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(uploadUrlField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(type, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(180, 180, 180))
                .add(jPanel1Layout.createSequentialGroup()
                .add(publishing)
                .addContainerGap()))
                .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                .add(upload)
                .add(passwordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())))
                
                );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                .add(titleLabel)
                .add(36, 36, 36)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(filenameLabel)
                .add(localFileField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(56, 56, 56)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(filepathLabel)
                .add(vfsPathField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(29, 29, 29)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(fileTypeLabel)
                .add(type, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(29, 29, 29)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(publishLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(publishing))
                .add(19, 19, 19)
                
                .add(15, 15, 15)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(usernameLabel)
                .add(userField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                .add(passwordLabel)
                .add(passwordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(19, 19, 19)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                .add(cmsLabel)
                .add(uploadUrlField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(26, 26, 26)
                .add(upload)
                .add(13, 13, 13))
                );
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(titlePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 420, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(titlePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 405, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
                );
        pack();
    }
    
    
    private void keyAction(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            uploadAction();
        } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        }
    }
    
    /**
     * Method for file upload activation
     */
    private void uploadAction() {
        // Get value of the selected item in combobox
        VfsResourceType selectedType = (VfsResourceType) type.getSelectedItem();
        
        VfsResource resource = new VfsResource();
        resource.setType(selectedType);
        resource.setVfsPath(vfsPathField.getText());
        
        // Call fileUpload Method with parameters
        fileUpload(localFileField.getText(), resource, publishing.isSelected(), 
                userField.getText(), new String(passwordField.getPassword()), uploadUrlField.getText());
    }
    
    
    
    
    /**
     * Method to activate FileUpload and set the values for its variables.
     * @param localFilePath file to upload
     * @param publish defines if the file should be published
     * @param username username in OpenCms
     * @param password password in OpenCms
     * @param absfilename absolute name of the file to upload
     * @param cms URL of UploadServlet in OpenCms
     *
     * @throws Exception if executeUpload method from the class FileUpload could'nt be executed
     */
    private void fileUpload(String localFilePath, VfsResource vfsResource, boolean publish, String username, String password, String uploadUrl) {
        
        FileUpload fileUpload = new FileUpload();
        fileUpload.setfileToUpload(localFilePath);
        fileUpload.setPublish(publish);
        fileUpload.setVfsResource(vfsResource);
        fileUpload.setUsername(username);
        fileUpload.setPassword(password);
        fileUpload.setUploadUrl(uploadUrl);
        this.setVisible(false);

        DialogData data = new DialogData();
        data.setLocalPath(localFilePath);
        data.setPassword(password);
        data.setPublish(publish);
        data.setUploadUrl(uploadUrl);
        data.setUsername(username);
        data.setVfsResource(vfsResource);
        
        dialogDao.persist(data);
        
        try {
            FileUpload.UploadResponse response = fileUpload.executeUpload();
            // TODO check for response code
            // TODO do we need invoke later?
            NotifyDescriptor descriptor = new NotifyDescriptor.Message(response.getMessage(), NotifyDescriptor.PLAIN_MESSAGE);
            DialogDisplayer.getDefault().notify(descriptor);
        } catch (Exception ex) {
            // TODO do we need invoke later?
            NotifyDescriptor descriptor = new NotifyDescriptor.Message(ex.getMessage(), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(descriptor);
            log.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }


}
