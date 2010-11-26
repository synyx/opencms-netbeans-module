

package org.synyx.opencms.upload;

import java.io.File;
import java.util.regex.Matcher;

/**
 * Helper functionality for the upload.
 * @author Florian Hopf, Synyx GmbH & Co.KG
 */
public class VfsHelper {

    /**
     * Returns a representation of the given path in VFS
     * when the given path resembles a VFS path.
     * @param path the absolute path to a file
     * @return a VFS representation or null if the given path doesn't resemble a VFS path
     */
    public static final String guessVfsPath(String path) {
        String result = null;
        if (path != null) {
            String [] elements = path.split(Matcher.quoteReplacement(File.separator));
            StringBuilder builder = null;
            for (int i = 0; i < elements.length; i++) {
                if ("sites".equalsIgnoreCase(elements[i]) || "system".equalsIgnoreCase(elements[i])) {
                    builder = new StringBuilder("/");
                }
                if (builder != null) {
                    boolean looksLikeFile = (elements[i].indexOf('.') != -1);                    
                    // append if this is a directory part
                    // or if this is the last entry and it is not likely
                    // that the entry is a .
                    if (i < elements.length - 1 || (!looksLikeFile && i == elements.length - 1)) {
                        builder.append(elements[i]);
                        builder.append("/");
                    }
                }
            }
            if (builder != null) {
                result = builder.toString();
            } 
        } 
        return result;
    }
    
    /**
     * Guesses the fileType for the filename.
     * @param path
     * @return the guessed type or null if no type could be guessed.
     * @todo might be good to make this configurable
     * @deprecated use FileTypeConfiguration instead
     */
    public static final String guessType(String path) {
        String result = null;
        if (path != null) {
            String extension = getExtension(path);
            if (extension != null) {
                if (isJsp(extension)) {
                    result = VfsResourceType.TYPE_JSP;
                } else if (isImage(extension)) {
                    result = VfsResourceType.TYPE_IMAGE;
                } 
            }
        }
        return result;
    }
    
    private static final boolean isJsp(String extension) {
        return extension.equalsIgnoreCase("jsp") || extension.equalsIgnoreCase("html") || extension.equalsIgnoreCase("jspx");
    }
    
    private static final boolean isImage(String extension) {
        return extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("gif");
    }
    
    /**
     * Returns the extension for this file.
     * @param path
     * @return the extension or null if no extension could be found
     */
    public static final String getExtension(String path) {
        String extension = null;
        // check that there is at least one dot and that it's not at the last position
        if (path != null && path.indexOf('.') != -1 && path.indexOf('.') < path.length() - 1) {
            extension = path.substring(path.lastIndexOf('.') + 1);
        }
        
        return extension;
    }
}
