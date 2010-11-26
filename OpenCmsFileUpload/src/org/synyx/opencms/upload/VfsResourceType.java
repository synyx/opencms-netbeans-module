

package org.synyx.opencms.upload;

/**
 * Represents a resource type in VFS.
 * @author Florian Hopf, Synyx GmbH & Co.KG
 */
public class VfsResourceType {

    public static final String TYPE_PLAIN = "default";
    public static final String TYPE_JSP = "jsp";
    public static final String TYPE_XML_PAGE = "xml";
    public static final String TYPE_IMAGE = "img";
    public static final String TYPE_BINARY = "bin";
    
    private String label = null;
    private String name = null;

    /**
     * Creates a new instance with the given name
     * @param name
     */
    public VfsResourceType(String name) {
        setName(name);
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (label == null) {
            label = name;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VfsResourceType other = (VfsResourceType) obj;
        if (this.label != other.label && (this.label == null || !this.label.equals(other.label))) {
            return false;
        }
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
 
    public String toString() {
        return label;
    }
    
}
