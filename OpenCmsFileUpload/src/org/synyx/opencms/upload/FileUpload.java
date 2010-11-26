/*
 * FileUpload.java
 *
 * Created on 19. April 2007, 19:56
 */

package org.synyx.opencms.upload;

import java.io.File;
import java.util.logging.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;



public class FileUpload {
    
    /**
     * Set the url of the servlet for upload.
     */
    private String uploadUrl = null;
    
    /**
     * The absolute path to the file to upload.
     */
    private String fileToUpload = null;
    
    /**
     * Representation of resource to upload in Vfs.
     */
    private VfsResource vfsResource = null;

    /**
     * Defines if the file to upload must be published.
     */
    private boolean publish = false;
    
    /**
     * Username in OpenCms.
     */
    private String username;
    /**
     * Password in OpenSms for the user from username.
     */
    private String password;

    private Logger log = Logger.getLogger(this.getClass().toString());
    
    
    /**
     * Initialize Post Method, RequestEntity, Http Client and send request
     * to the UploadServlet (org.synyx.opencms.deployment).
     *
     * @throws Exception if unable to execute the Post Method
     * @return response the UploadResponse
     */
    public final UploadResponse executeUpload() throws Exception {

        File file = new File(fileToUpload);
        
        // Initialize a Post Method with attributes
        PostMethod filePost = new PostMethod(uploadUrl);
        
        Part[] parts = {new StringPart("username", username),
        new StringPart("password", password),
        new StringPart("openCmsPath", vfsResource.getVfsPath()),
        new StringPart("publish", String.valueOf(publish)),
        new StringPart("fileType", vfsResource.getType().getName()),
        new StringPart("cmsDirectory", "none"),
        new StringPart("isDirectory", "false"),
        new FilePart("fileToUpload", file)};
        
        // Initialize a RequestEntity
        filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
        
        // Initialize a Http Client
        HttpClient client = new HttpClient();
        // Execute Post Method
        int status = client.executeMethod(filePost);
        String message = filePost.getResponseBodyAsString();
        UploadResponse response = new UploadResponse(status, message);
        
        log.info("Uploaded file " + fileToUpload + " to " + vfsResource.getVfsPath() + " in OpenCms.");
        log.info("StatusLine: " + filePost.getStatusLine());

        return response;
    }
    
    
    /**
     * Sets the vfs resource representation of the uploaded file.
     * @param vfsResource
     */
    public void setVfsResource(VfsResource vfsResource) {
        this.vfsResource = vfsResource;
    }
    
    
    /**
     * Setter method for uploadurl.
     *
     * @param uploadUrl
     */
    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
    /**
     * Getter method for the fileToUpload.
     *
     * @return fileToUpload the value of the fileToUpload.
     */
    public String getfileToUpload() {
        return fileToUpload;
    }
    
    /**
     * Setter method for fileToUpload.
     *
     * @param fileToUpload the absolute path to the file for upload
     */
    public void setfileToUpload(String fileToUpload) {
        this.fileToUpload = fileToUpload;
    }

    /**
     * Setter method for username.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets whether the resource should be published.
     *
     * @param publish
     */
    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    /**
     * Setter method for password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    /**
     * Represents a response received from UploadServlet.
     */
    public static final class UploadResponse {
        
        private int status = -1;
        private String message = null;
        
        /**
         * Creates a new instance
         * @param status the status code
         * @param message a message received
         */
        public UploadResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }
    }
    
}


