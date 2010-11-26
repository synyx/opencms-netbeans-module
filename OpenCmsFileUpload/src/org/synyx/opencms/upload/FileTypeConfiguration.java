

package org.synyx.opencms.upload;

import java.util.List;

/**
 * Provides methods for file type configuration.
 * @author Florian Hopf, Synyx GmbH & Co.KG
 */
public interface FileTypeConfiguration {

    /**
     * Returns all VfsResourceTypes configured.
     * @return a List of VfsResourceType
     */
    public List getTypes();
    
    /**
     * Returns the VfsResourceType configured for a path or a default delection
     * @param path
     * @return the resource type to be used
     */
    public VfsResourceType getTypeForPath(String path);
    
    /**
     * Returns the type for the given name.
     * @param name
     * @return a preconfigured type
     */
    public VfsResourceType getType(String name);
}
