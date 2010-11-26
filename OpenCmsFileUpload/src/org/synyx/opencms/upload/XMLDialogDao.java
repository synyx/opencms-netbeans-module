package org.synyx.opencms.upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 * XML config implementation of the DialogDao.
 * @author Florian Hopf, Synyx GmbH & Co.KG
 */
public class XMLDialogDao implements DialogDao {

    // base path for all configuration
    private String basePath = (System.getProperty("netbeans.user")) + 
            File.separator + "var" + File.separator + "OpenCmsUpload" + File.separator;
    // path for the file configuration
    private String baseFilePath = basePath.concat("files" + File.separator);
    private FileTypeConfiguration typeConfig = null;

    public XMLDialogDao(FileTypeConfiguration typeConfig) {
        this.typeConfig = typeConfig;
        initialize();
    }

    private void copy(String from, String to) throws IOException {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new PrintWriter(new FileWriter(to));
            String line = null;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                // NOOP
                }
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private final void initialize() {
        File configFile = new File(getDefaultConfigLocation());
        if (!configFile.exists()) {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(XMLDialogDao.class.getResourceAsStream("defaultData.xml")));
                createFileWithFolders(configFile);
                writer = new PrintWriter(configFile);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }
            } catch (Exception ex) {
                // TODO the module can still be used without the file
                // there just is no configuration enabled
                throw new RuntimeException(ex);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    public DialogData read(String path) {
        DialogData data = null;
        File xmlFile = new File(getConfigPathForFilePath(path));
        if (xmlFile.exists()) {
            data = readFromXml(xmlFile.getAbsolutePath());
            data.setLocalPath(path);
        }
        return data;

    }

    public void persist(DialogData data) {
        String configPath = getConfigPathForFilePath(data.getLocalPath());
        // create initial File if necessary
        try {
            File file = new File(configPath);
            if (!file.exists()) {
                createFileWithFolders(file);
                copy(getDefaultConfigLocation(), configPath);
            }
            new DialogDataSet(configPath, data.getVfsResource().getVfsPath(), data.getUsername(),
                    data.getPassword(), data.getUploadUrl(), data.getVfsResource().getType().getName(),
                    String.valueOf(data.isPublish()));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public DialogData getDefault() {
        return readFromXml(getDefaultConfigLocation());
    }

    private DialogData readFromXml(String xmlPath) {
        DialogDataGet get = new DialogDataGet(xmlPath);
        DialogData data = new DialogData();
        data.setPassword(get.getDialogValuePassword());
        data.setPublish(Boolean.valueOf(get.getDialogValuePublish()).booleanValue());
        data.setUploadUrl(get.getDialogValueCms());
        data.setUsername(get.getDialogValueUsername());
        VfsResource vfsResource = new VfsResource();
        vfsResource.setType(typeConfig.getType(get.getDialogValueType()));
        vfsResource.setVfsPath(get.getDialogValuePath());
        data.setVfsResource(vfsResource);
        return data;
    }

    private String getDefaultConfigLocation() {
        return basePath.concat(NbBundle.getMessage(XMLDialogDao.class, "defaultConfig"));
    }

    private String getConfigPathForFilePath(
            String filePath) {
        // TODO how to implement OS independent?
        return baseFilePath.concat(filePath.substring(1)).concat(".xml");
    }

    private void createFileWithFolders(File file) throws IOException {

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }
}
