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

package com.ibm.websphere.security.wim.scim20.exceptions;

/**
 * The request body message structure was invalid or did not conform to the
 * request schema.
 */
public class InvalidSyntaxException extends SCIMException {

    private static final long serialVersionUID = -1623651734212311148L;

    public InvalidSyntaxException(String msg) {
        super(400, "invalidSyntax", msg);
    }
}
