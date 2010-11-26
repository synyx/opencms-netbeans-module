
package org.synyx.opencms.schemacompletion;

import java.awt.Image;

import java.beans.PropertyChangeListener;

import java.io.IOException;

import java.util.Arrays;
import java.util.Iterator;

import org.netbeans.modules.xml.catalog.spi.CatalogDescriptor;
import org.netbeans.modules.xml.catalog.spi.CatalogListener;
import org.netbeans.modules.xml.catalog.spi.CatalogReader;

import org.openide.util.ImageUtilities;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Registers the opencms aware entity resolver.
 *
 * @author  Florian Hopf, Synyx GmbH & Co. KG, hopf@synyx.de
 */
public class RegisterCatalog implements CatalogReader, CatalogDescriptor, EntityResolver {

    //private static final String PURCHASE_ORDER_XSD = "po.xsd";
    private static final String XMLCONTENT = "opencms://opencms-xmlcontent.xsd";
    private static final String XMLCONTENT_URL = "nbres:/org/synyx/opencms/schemacompletion/opencms-xmlcontent.xsd";
    private static final String XMLCONTENT_ID = "SCHEMA:" + XMLCONTENT;

    private static final String OPENCMS_MODULE_PREFIX = "opencms://system/modules/";

    public Iterator getPublicIDs() {

        return Arrays.asList(new String[] { XMLCONTENT }).iterator();
    }


    public void refresh() {

        // do nothing
    }


    public String getSystemID(String publicId) {

        if (XMLCONTENT.equals(publicId)) {
            return XMLCONTENT_URL;
        } else {
            return null;
        }
    }


    public String resolveURI(String arg0) {

        return null;
    }


    public String resolvePublic(String arg0) {

        return null;
    }


    public void addCatalogListener(CatalogListener arg0) {
    }


    public void removeCatalogListener(CatalogListener arg0) {
    }


    public Image getIcon(int arg0) {

        return ImageUtilities.loadImage("org/myorg/codecompletion/catalog/resources/Schema.gif");
    }


    public String getDisplayName() {

        return "OpenCms XMLContent";
    }


    public String getShortDescription() {

        return "Support for editing OpenCms XMLContent";
    }


    public void addPropertyChangeListener(PropertyChangeListener arg0) {
    }


    public void removePropertyChangeListener(PropertyChangeListener arg0) {
    }


    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {

        if (systemId.equals("opencms-xmlcontent.xsd")) {
            return new InputSource(XMLCONTENT_URL);
        } else if (isOpenCmsInclude(systemId)) {
            return new InputSource(splitModuleFolder(systemId));
        } else {
            return null;
        }
    }


    private boolean isOpenCmsInclude(String systemId) {

        return systemId.startsWith(OPENCMS_MODULE_PREFIX);
    }


    /**
     * Splits the given opencms include of the form opencms://system/modules/... so that only the path below the schema
     * folder is included.
     *
     * @param   opencmsInclude
     * @return
     */
    private String splitModuleFolder(String opencmsInclude) {

        return opencmsInclude.replaceFirst(OPENCMS_MODULE_PREFIX.concat("[\\w.]+/\\w/"), "");
    }
}
