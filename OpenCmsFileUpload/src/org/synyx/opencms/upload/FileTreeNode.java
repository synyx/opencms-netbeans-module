/*
 * FileTreeNode.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;


import java.io.File;
import java.util.ArrayList;

/*
 * Action to create a new instance of the tree Node.
 *
 * @author <a href="mailto:nichyk@synyx.de">Kateryna Nichyk</a> Synyx GmbH
 * & Co. KG Karlsruhe, Germany
 */

public class FileTreeNode {
    
    File file = null;
    ArrayList childList = new ArrayList();
    
    /** Creates a new instance of FileTreeNode */
    public FileTreeNode(File file) {
        this.file = file;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void addChild(FileTreeNode childNode) {
        childList.add(childNode);
    }
    
    public ArrayList getChildNodes() {
        return childList;
    }
    
    public boolean isDirectory() {
        return (childList.size() > 0);
    }
    
    public String getParentPath(int level) {
        String parentPath="";
        
        File parentFile = this.file.getParentFile();
        for (int i=level; (i>0 && parentFile != null); i--) {
            parentPath = parentFile.getName()+"/" + parentPath;
            parentFile = parentFile.getParentFile();
        }
        
        return parentPath;
    }
    
}
