
package org.synyx.opencms.upload;

/**
 * Data access object for persisting and retrieving
 * the data of a dialog.
 * @author Florian Hopf, Synyx GmbH & Co. KG
 */
public interface DialogDao {

    /** 
     * Reads the Dialog Data for a given path. 
     * @param path the absolute path to the file for which to store the data
     * @return the data for the dialog or null
     */
    DialogData read(String path);
    
    /** Persists the dialog data. 
     * @param data the DialogData
     */
    void persist(DialogData data);

    /**
     * Returns a default value.
     * @return
     */
    DialogData getDefault();
}
