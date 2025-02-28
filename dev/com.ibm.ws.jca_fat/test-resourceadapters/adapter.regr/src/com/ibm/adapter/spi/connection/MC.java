/*******************************************************************************
 * Copyright (c) 2003, 2020 IBM Corporation and others.
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

package com.ibm.adapter.spi.connection;

import java.sql.Connection;

import javax.resource.ResourceException;
import javax.security.auth.Subject;
import javax.sql.PooledConnection;

import com.ibm.adapter.spi.ConnectionRequestInfoImpl;
import com.ibm.adapter.spi.ManagedConnectionFactoryImpl;
import com.ibm.adapter.spi.ManagedConnectionImpl;
import com.ibm.ejs.ras.Tr;
import com.ibm.ejs.ras.TraceComponent;

/**
 * This managed connection supports both lazy enlistable optimization and laze
 * associatable optimization.
 */
public class MC extends ManagedConnectionImpl {

    private static final TraceComponent tc = Tr.register(MC.class);

    /**
     * Constructor for MC.
     *
     * @param mcf
     * @param pconn
     * @param conn
     * @param sub
     * @param cxRequestInfo
     * @throws ResourceException
     */
    public MC(ManagedConnectionFactoryImpl mcf, PooledConnection pconn,
              Connection conn, Subject sub,
              ConnectionRequestInfoImpl cxRequestInfo) throws ResourceException {

        super(mcf, pconn, conn, sub, cxRequestInfo);
        if (tc.isDebugEnabled())
            Tr.entry(tc, "<init>", this);
    }

    /**
     * @return boolean Whether the MC supports Lazy Associatable optimization.
     */
    @Override
    public boolean isLazyAssociatable() {
        return false;
    }

    /**
     * @return boolean Whether the MC supports Lazy Enlistable optimization.
     */
    @Override
    public boolean isLazyEnlistable() {
        return false;
    }

}
