/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
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
package com.ibm.ws.microprofile.config12.converter.priority.converters;

import javax.annotation.Priority;

import org.eclipse.microprofile.config.spi.Converter;

import com.ibm.ws.microprofile.config12.converter.priority.beans.MyObject;

@Priority(50)
public class Priority50Converter implements Converter<MyObject> {

    /** {@inheritDoc} */
    @Override
    public MyObject convert(String value) {
        return new MyObject(value, "Priority50Converter");
    }

}
