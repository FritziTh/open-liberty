/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
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
package com.ibm.ws.jaxrs.fat.securitycontext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.ibm.ws.jaxrs.fat.securitycontext.xml.SecurityContextInfo;

@Path("/context/securitycontext/bean")
public class SecurityContextBeanResource {

    private SecurityContext s = null;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public SecurityContextInfo requestSecurityInfo() {
        return SecurityContextUtils.securityContextToJSON(s);
    }

    @Context
    public void setSecurityInfo(SecurityContext secContext) {
        this.s = secContext;
    }
}
