/*
 * FolderUploadAction.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

/**
 * Action to create an OpenCms Folder Upload action dependent on the cookies of the selected nodes.
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a>,
 *         <a href="http://www.synyx.de/" target="_new">Synyx GmbH & Co. KG</a>
 *         Karlsruhe Germany
 * @todo this is a singleton, make it Threadsafe
 */

public class FolderUploadAction extends CookieAction {
    /**
     * The width of the context menu.
     */
    final public static int width = 460;
    /**
     * The height of the context menu.
     */
    final public static int height = 500;
    
    private String path;
    private String fileList [];
    private File dir;
    private List list = new ArrayList();
    private List dirList = new ArrayList();
    private int j = 1;
    /**
     * Pattern for hidden files
     */
    private static final String PATTERN = "^(CVS|SCCS|vssver\\.scc|#.*#|%.*%|\\.(cvsignore|svn|DS_Store))$|^\\.[#_%].*|~$";
    /**
     * Netbeans Logger.
     */
    private Logger log = Logger.getLogger(this.getClass().toString());
    
    public void performAction(Node[] activatedNodes) {
        
        DataObject c = (DataObject) activatedNodes[0].getCookie(DataObject.class);
        
        path = c.toString();
        path = "/" + path.substring(path.lastIndexOf("[") + 1, path.length() - 2);
        dir = new File(path);
        dirList.add(dir.getName());
             
        FileTreeNode rootNode = new FileTreeNode(dir);
        rootNode = generateFileTree(rootNode);
        
        String vfsPath = VfsHelper.guessVfsPath(path);
        
        JMenuBar menuBar;
        // Create a new FolderUploadDialog object with path as a parameter
        FolderUploadDialog dialog = new FolderUploadDialog(rootNode, vfsPath);
        // Set the dialog title
        dialog.setTitle(NbBundle.getMessage(FolderUploadDialog.class, "BarTitle"));
        // Create a new JMenuBar object
        menuBar = new JMenuBar();
        menuBar.add(new JMenu(NbBundle.getMessage(UploadDialog.class, "BarFile"))).
                add(new JMenuItem(NbBundle.getMessage(UploadDialog.class, "BarExit")));
        // Set JMenuBar object into the dialog
        dialog.setJMenuBar(menuBar);
        // Set size of the dialog window
        dialog.setSize(width, height);
        // Set dialog location
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // Make dialog visible
        dialog.setVisible(true);
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
     * Get a human presentable name of the action. This may be presented as an item in a menu
     *
     * @return the name of the action
     */
    public String getName() {
        return NbBundle.getMessage(FolderUploadAction.class, "CTL_FolderUploadAction");
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
    
     /**
     * Initialize the action.
     */
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
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
     * Generate a file tree starting at the given node.
     *
     * @param node The node to start from.
     */
    private FileTreeNode generateFileTree(FileTreeNode node) {
             
        File file = node.getFile();
        if (file.isDirectory() && matchPattern(file.getName())) {
            List directoryContent = Arrays.asList(file.listFiles());
            for (Iterator contentIt = directoryContent.iterator(); contentIt.hasNext();) {
                File singleContent = (File)contentIt.next();
                if (matchPattern(singleContent.getName())) {
                    FileTreeNode singleContentNode = new FileTreeNode(singleContent);
                    node.addChild(generateFileTree(singleContentNode));
                } 
            }
        }
        return node;
    }
    
    /**
     * Get a human presentable name of the action. This may be presented as an item in a menu
     *
     * @param string to match
     *
     * @return false if match matches the PATTERN
     */
    private boolean matchPattern(String match) {
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(match);
        boolean b = m.matches();        
        return !b;
    }
    
}

