/*******************************************************************************
 * Copyright (c) 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package com.ibm.ws.jsf22.fat.cdiflows.beans;

import java.io.Serializable;

import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple FlowScoped bean used in the faces flows initialize and finalize test case.
 */
@Named(value = "testBeanInitFinalize")
@FlowScoped(value = "initializeFinalize")
public class TestBeanInitFinalize implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CountBean countBean;
    private String testValue;

    public TestBeanInitFinalize() {
        this.testValue = "";
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getReturnValue() {
        return "/JSF22Flows_return";
    }

    // flows initializer method: should be called upon flow initialization
    public void testInitialize() {
        testValue = "test string";
    }

    // finalizer method: should be called after this flow is finalized
    public void testFinalize() {
        countBean.increment();
    }
}
