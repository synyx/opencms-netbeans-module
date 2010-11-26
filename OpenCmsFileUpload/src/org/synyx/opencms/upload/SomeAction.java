/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.synyx.opencms.upload;

import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

public final class SomeAction extends CookieAction {

    protected void performAction(Node[] activatedNodes) {
        DataObject dataObject = (DataObject) activatedNodes[0].getLookup().lookup(DataObject.class);
    // TODO use dataObject
    }

    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    public String getName() {
        return NbBundle.getMessage(SomeAction.class, "CTL_SomeAction");
    }

    protected Class[] cookieClasses() {
        return new Class[]{DataObject.class};
    }

    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() Javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    protected boolean asynchronous() {
        return false;
    }
}

