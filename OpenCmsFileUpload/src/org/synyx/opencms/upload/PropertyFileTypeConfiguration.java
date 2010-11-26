

package org.synyx.opencms.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.NbBundle;

/**
 * Configuration for the module in properties.
 * @author Florian Hopf, Synyx GmbH & Co.KG
 */
public class PropertyFileTypeConfiguration implements FileTypeConfiguration {

    /* contains the extensions as key and the VfsResourceType as value */
    private Map typeByExtension = new HashMap();
    
    private List types = new ArrayList();  
    
    private Logger logger = Logger.getLogger(PropertyFileTypeConfiguration.class.getName());
    
    private VfsResourceType defaultType = null;
    
    public PropertyFileTypeConfiguration() {
        init();
    }
    
    private void init() {
        String typeString = getMessageOrNull("fileTypes");
        if (typeString != null) {
            String [] typeValues = typeString.split(",");
            for (int i = 0; i < typeValues.length; i++) {
                String currentTypeValue = typeValues[i].trim();
                VfsResourceType type = getType(currentTypeValue);
                if (defaultType == null) {
                    defaultType = type;
                }
                addExtensions(type);
                types.add(type);
            }
        } else {
            logger.log(Level.CONFIG, typeString);
        }
    }
    
    private void addExtensions(VfsResourceType type) {
        String extensionString = getMessageOrNull("extensions." + type.getName());
        if (extensionString != null) {
            String [] extensions = extensionString.split(",");
            for (int i = 0; i < extensions.length; i++) {
                typeByExtension.put(extensions[i], type);
            }
        }
    }
    
    private String getMessageOrNull(String key) {
        String result = null;
        try {
            result = NbBundle.getMessage(PropertyFileTypeConfiguration.class, key);
        } catch (MissingResourceException ex) {
            logger.fine(ex.getMessage());
        }
        return result;
    }
    
    public VfsResourceType getType(String name) {
        VfsResourceType type = new VfsResourceType(name);
        String label = getMessageOrNull("fileType.".concat(name));
        if (label != null) {
            type.setLabel(label);
                    
        }
        return type;
    }
    
    public List getTypes() {
        return types;
    }

    public VfsResourceType getTypeForPath(String path) {
        String extension = VfsHelper.getExtension(path);
        VfsResourceType result = null;
        if (extension != null) {
            result = (VfsResourceType) typeByExtension.get(extension);
        }
        if (result == null) {
            result = defaultType;
        }
        return result;
    }

    
    
}
