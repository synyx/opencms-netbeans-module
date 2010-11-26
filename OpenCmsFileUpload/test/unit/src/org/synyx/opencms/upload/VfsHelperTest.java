/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.synyx.opencms.upload;

import junit.framework.TestCase;

/**
 *
 * @author flo
 */
public class VfsHelperTest extends TestCase {
    
    public VfsHelperTest(String testName) {
        super(testName);
    }            

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testGuessVfsPath() {
        assertEquals(null, VfsHelper.guessVfsPath(null));
        assertEquals(null, VfsHelper.guessVfsPath(null));
        assertEquals(null, VfsHelper.guessVfsPath(""));
        assertEquals(null, VfsHelper.guessVfsPath("/some/path"));
        assertEquals("/system/modules/", VfsHelper.guessVfsPath("/system/modules/"));
        assertEquals("/system/modules/", VfsHelper.guessVfsPath("/path/to/system/modules/"));
        assertEquals("/system/modules/", VfsHelper.guessVfsPath("/system/modules"));
        assertEquals("/system/modules/", VfsHelper.guessVfsPath("/path/to/system/modules"));
        assertEquals("/system/modules/", VfsHelper.guessVfsPath("/system/modules/some.file"));
        assertEquals("/system/modules/", VfsHelper.guessVfsPath("/path/to/system/modules/some.file"));
    }

    public void testGuessType() {
        assertEquals(null, VfsHelper.guessType(null));
        assertEquals(null, VfsHelper.guessType(""));
        assertEquals(null, VfsHelper.guessType("klick-mich.exe"));
        assertEquals(VfsResourceType.TYPE_JSP, VfsHelper.guessType("hallo.jsp"));
        assertEquals(VfsResourceType.TYPE_IMAGE, VfsHelper.guessType("/some/path/to/FILE.PNG"));
        
    }
    
    
    public void testGetExtension() {
        assertEquals(null, VfsHelper.getExtension(null));
        assertEquals(null, VfsHelper.getExtension(""));
        assertEquals(null, VfsHelper.getExtension("hallo"));
        assertEquals(null, VfsHelper.getExtension("/some/path"));
        assertEquals("png", VfsHelper.getExtension("image.png"));
        assertEquals("gif", VfsHelper.getExtension("/path/to/system/modules/image.gif"));
    }
}
