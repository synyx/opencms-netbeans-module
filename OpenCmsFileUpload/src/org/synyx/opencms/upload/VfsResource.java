

package org.synyx.opencms.upload;

/**
 * Represents a resource to be uploaded to VFS
 * @author Florian Hopf, Synyx GmbH & Co.KG
 */
public class VfsResource {
    
    
    private String vfsPath = null;
    private VfsResourceType type = null;

    public VfsResourceType getType() {
        return type;
    }

    public void setType(VfsResourceType type) {
        this.type = type;
    }

    public String getVfsPath() {
        return vfsPath;
    }

    public void setVfsPath(String vfsPath) {
        this.vfsPath = vfsPath;
    }
    
}
