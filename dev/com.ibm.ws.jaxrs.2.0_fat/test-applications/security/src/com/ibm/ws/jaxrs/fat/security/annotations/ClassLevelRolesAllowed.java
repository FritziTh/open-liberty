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
package com.ibm.ws.jaxrs.fat.security.annotations;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * A sample resource that demonstrates
 * Class level RolesAllowed
 */
@Path(value = "/ClassRolesAllowed")
@RolesAllowed({ "Role1", "Role1appbnd" })
public class ClassLevelRolesAllowed {

    @GET
    @Produces(value = "text/plain")
    public String getMessage() {
        return "remotely accessible only to users in Role1";
    }
}
