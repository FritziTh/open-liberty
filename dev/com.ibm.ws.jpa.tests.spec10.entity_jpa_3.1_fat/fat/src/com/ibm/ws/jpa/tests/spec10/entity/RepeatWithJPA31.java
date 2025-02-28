/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
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

package com.ibm.ws.jpa.tests.spec10.entity;

import com.ibm.ws.testtooling.jpaprovider.JPAPersistenceProvider;

import componenttest.custom.junit.runner.Mode.TestMode;
import componenttest.rules.repeater.JakartaEE10Action;

public class RepeatWithJPA31 extends JakartaEE10Action {
    public static final String ID = "JPA31";

    /**
     * Allow the default repeat action to run on LITE mode
     */
    public RepeatWithJPA31() {
        // Used in componenttest.rules.repeater.RepeatTestAction.isEnabled() to determine if the test should run
        withTestMode(TestMode.LITE);
    }

    @Override
    public String toString() {
        return "JPA 3.1";
    }

    @Override
    public void setup() throws Exception {
        super.setup();
        FATSuite.repeatPhase = "jpa31-cfg.xml";
        FATSuite.provider = JPAPersistenceProvider.DEFAULT;
    }
}
