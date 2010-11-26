

package org.synyx.opencms.upload;

/**
 * Stores all information that is relevant for
 * persisting the dialog information.
 * @author Florian Hopf, Synyx GmbH & Co.KG
 */
public class DialogData {
    
    private String localPath = null;
    private VfsResource vfsResource = null;
    private boolean publish = false;
    private String username = null;
    private String password = null;
    private String uploadUrl = null;

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public VfsResource getVfsResource() {
        return vfsResource;
    }

    public void setVfsResource(VfsResource vfsResource) {
        this.vfsResource = vfsResource;
    }
    
    
}
