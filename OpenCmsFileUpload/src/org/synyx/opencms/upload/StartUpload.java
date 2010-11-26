/*
 * StartUpload.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;


import java.io.File;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

/**
 * Action to create an OpenCms File Upload action dependent on the cookies of the selected nodes.
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a>,
 *         <a href="http://www.synyx.de/" target="_new">Synyx GmbH & Co. KG</a>
 *         Karlsruhe Germany
 */
public class StartUpload extends CookieAction {
    
    /**
     * The width of the dialog.
     */
    final public static int WIDTH = 400;
    
    /**
     * The height of the dialog.
     */
    final public static int HEIGHT = 500;
    
    /**
     * Netbeans Logger.
     */
    private Logger log = Logger.getLogger(this.getClass().toString());
    
    private FileTypeConfiguration typeConfig = null;
    private DialogDao dialogDao = null;
    
    
    public StartUpload() {
        typeConfig = new PropertyFileTypeConfiguration();
        dialogDao = new XMLDialogDao(typeConfig);
    }
    
    /**
     * Calls an OpenCms File Upload action.
     *
     * @param activatedNodes
     */
    public void performAction(Node[] activatedNodes) {
        
        // Get Cookie of the activated Node
        DataObject dataObject = (DataObject) activatedNodes[0].getCookie(DataObject.class);
        fileUploadAction(dataObject);
    }
    
    
    /**
     * Initializes OpenCms File Upload Dialog.
     * @param dataObject
     */
    public void fileUploadAction(DataObject dataObject) {
        
        String localPath = getLocalPath(dataObject);
        
        if (localPath != null) {
        
            log.info("Local path: " + localPath);

            DialogData initial = dialogDao.read(localPath);
            if (initial == null) {
                initial = dialogDao.getDefault();
                initial.setLocalPath(localPath);
                VfsResource resource = new VfsResource();
                resource.setType(typeConfig.getTypeForPath(localPath));
                resource.setVfsPath(getVfsPath(dataObject));
                initial.setVfsResource(resource);
            }

            // Create a new UploadDialog object with path as a parameter
            UploadDialog dialog = new UploadDialog(dialogDao, initial);
            // Set the dialog title
            dialog.setTitle(NbBundle.getMessage(UploadDialog.class, "BarTitle"));

            // Set size of the dialog window
            dialog.setSize(WIDTH, HEIGHT);
            // Set dialog location
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            // Make dialog visible
            dialog.setVisible(true);
        } else {
            // its a file that currently is not supported
            NotifyDescriptor descriptor = new NotifyDescriptor.Message(
                    NbBundle.getMessage(StartUpload.class, "error.fileTypeUnsupported"), 
                    NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(descriptor);
        }
    }

    
    private String getVfsPath(DataObject dataObject) {
        // TODO add the stored name
        String guessed = VfsHelper.guessVfsPath(getLocalPath(dataObject));
        if (guessed != null) {
            return guessed;
        } else {
            return "/";
        }
    }
    
    private String getLocalPath(DataObject dataObject) {
        String result = null;
        // dataObjects can consist of several files (e.g. a Matisse GUI consists of 
        // .form and .java. For uploading it should always be enough to use the primary file
        if (dataObject.getPrimaryFile() != null) {
            File file = FileUtil.toFile(dataObject.getPrimaryFile());
            if (file != null) {
                result = file.getAbsolutePath();
            }
        }
        return result;
    }
    
    
    /**
     * Get a human presentable name of the action. This may be presented as an item in a menu
     *
     * @return the name of the action
     */
    public String getName() {
        return NbBundle.getMessage(StartUpload.class, "CTL_StartUpload");
    }
    
    
    /**
     * Initialize the action.
     */
    protected void initialize() {
        super.initialize();
        //Set a property noIconInMenu
        putValue("noIconInMenu", Boolean.TRUE);
        // initialize the dao
    }
    
    
    /**
     * Get a help context for the action.
     *
     * @return the default help context for the action.
     */
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    
    /**
     * The action will be performed synchronously as called in the event thread.
     *
     * @return false if the action was performed synchronously.
     */
    protected boolean asynchronous() {
        return false;
    }
    
    
    /**
     * The mode: Action will be enabled if there is exactly one selected node and it supports the
     * given cookies.
     *
     * @return the mode of the CookieAktion.
     */
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }
    
    
    /**
     * Get the cookies that this action requires.
     *
     * @return new cookie class.
     */
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class
        };
    }
    
}
