/*******************************************************************************
 * Copyright (c) 2018-2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package componenttest.application.manager.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
                FATTest.class,
                AutoExtractTest.class,
                AppOrderTests.class,
                DropinsTests.class,
                AppPrereqTest.class
})
public class FATSuite {

}
