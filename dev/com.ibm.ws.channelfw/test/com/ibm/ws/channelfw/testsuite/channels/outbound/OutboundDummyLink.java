/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.channelfw.testsuite.channels.outbound;

import com.ibm.wsspi.channelfw.VirtualConnection;
import com.ibm.wsspi.channelfw.base.OutboundProtocolLink;

/**
 * Simple outbound link class for test usage.
 */
@SuppressWarnings("unused")
public class OutboundDummyLink extends OutboundProtocolLink {
    private OutboundDummyContext context;

    /**
     * Constructor.
     */
    public OutboundDummyLink() {
        super();
        context = new OutboundDummyContext();
    }

    protected void postConnectProcessing(VirtualConnection conn) {
        // nothing
    }

    public Object getChannelAccessor() {
        return context;
    }

}
