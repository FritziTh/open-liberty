/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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
package com.ibm.ws.udpchannel.internal;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

/**
 * @author mjohnson
 */
public interface UDPSelectorMonitor {
    void setChannel(DatagramChannel channel, UDPNetworkLayer udpPort) throws IOException;

    void removeChannel(DatagramChannel channel);

}
