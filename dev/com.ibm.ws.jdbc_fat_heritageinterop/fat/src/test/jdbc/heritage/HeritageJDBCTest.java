/*******************************************************************************
 * Copyright (c) 2021 IBM Corporation and others.
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
package test.jdbc.heritage;

import static org.junit.Assert.assertNotNull;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.ShrinkHelper;

import componenttest.annotation.Server;
import componenttest.annotation.TestServlet;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.FATServletClient;
import test.jdbc.heritage.app.JDBCHeritageTestServlet;

@RunWith(FATRunner.class)
public class HeritageJDBCTest extends FATServletClient {
    @Server("com.ibm.ws.jdbc.heritage")
    @TestServlet(servlet = JDBCHeritageTestServlet.class, path = "heritageApp/JDBCHeritageTestServlet")
    public static LibertyServer server;

    @BeforeClass
    public static void setUp() throws Exception {
        // Fake JDBC driver (a layer on top of Derby)
        JavaArchive jdbcDriver = ShrinkWrap
                        .create(JavaArchive.class, "HeritageDriver.jar")
                        .addPackage("test.jdbc.heritage.driver")
                        .addPackage("test.jdbc.heritage.driver.helper");

        ShrinkHelper.exportToServer(server, "jdbc", jdbcDriver);

        // Test application
        WebArchive heritageApp = ShrinkWrap.create(WebArchive.class, "heritageApp.war")
                        .addPackage("test.jdbc.heritage.app");
        ShrinkHelper.exportAppToServer(server, heritageApp);

        server.addInstalledAppForValidation("heritageApp");

        server.startServer();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        try {
            // Verify that DataStoreHelper getPrintWriter successfully overrides the
            // JDBC trace location to System.out (appears in message.log),
            String found = server.waitForStringInLog(".*==> Connection.*.prepareStatement\\(\"VALUES \\('testDefaultQueryTimeout', SQRT\\(196\\)\\)\", 1003, 1007\\).*");
            assertNotNull(found);
        } finally {
            server.stopServer("J2CA0030E", // test attempts to enlist multiple one-phase resources to prove that sharing does not occur
                              "WTRN0062E" // test attempts to enlist multiple one-phase resources to prove that sharing does not occur
            );
        }
    }
}
