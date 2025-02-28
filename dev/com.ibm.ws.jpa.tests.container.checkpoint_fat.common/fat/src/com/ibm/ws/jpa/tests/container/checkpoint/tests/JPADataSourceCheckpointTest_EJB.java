/*******************************************************************************
 * Copyright (c) 2023 IBM Corporation and others.
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
package com.ibm.ws.jpa.tests.container.checkpoint.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.ShrinkHelper;
import com.ibm.websphere.simplicity.config.Application;
import com.ibm.websphere.simplicity.config.ClassloaderElement;
import com.ibm.websphere.simplicity.config.ConfigElementList;
import com.ibm.websphere.simplicity.config.ServerConfiguration;
import com.ibm.ws.testtooling.vehicle.web.JPAFATServletClient;

import componenttest.annotation.Server;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.custom.junit.runner.Mode;
import componenttest.custom.junit.runner.Mode.TestMode;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.PrivHelper;
import io.openliberty.checkpoint.spi.CheckpointPhase;

@RunWith(FATRunner.class)
@Mode(TestMode.FULL)
public class JPADataSourceCheckpointTest_EJB extends JPAFATServletClient {

    private final static String RESOURCE_ROOT = "test-applications/datasourceApp/";
    private final static String appFolder = "ejb";
    private final static String appName = "jpadatasourceEjb";
    private final static String appNameEar = appName + ".ear";

    private final static String MSG_APP_START = "CWWKZ0001I.*" + appName;
    private final static String MSG_APP_RESTART = "CWWKZ0003I.*" + appName;
    private final static String MSG_CHECKPOINT = "CWWKC0451I.*";

    private final static String EJB_SF_SERVLET_NAME = "TestDataSource_EJB_SF_Servlet";
    private final static String EJB_SFEX_SERVLET_NAME = "TestDataSource_EJB_SFEx_Servlet";
    private final static String EJB_SL_SERVLET_NAME = "TestDataSource_EJB_SL_Servlet";

    private static final List<String> CHECKPOINT_INACTIVE = Collections.emptyList();
    private static final List<String> CHECKPOINT_BEFORE_APP_START = Arrays.asList("--internal-checkpoint-at=beforeAppStart");
    private static final List<String> CHECKPOINT_AFTER_APP_START = Arrays.asList("--internal-checkpoint-at=afterAppStart");

    private static long timestart = 0;

    @Server("JPACheckpointServer")
    public static LibertyServer server;

    @BeforeClass
    public static void setUp() throws Exception {
        PrivHelper.generateCustomPolicy(server, AbstractFATSuite.JAXB_PERMS);
        bannerStart(JPADataSourceCheckpointTest_EJB.class);
        timestart = System.currentTimeMillis();

        int appStartTimeout = server.getAppStartTimeout();
        if (appStartTimeout < (120 * 1000)) {
            server.setAppStartTimeout(120 * 1000);
        }

        int configUpdateTimeout = server.getConfigUpdateTimeout();
        if (configUpdateTimeout < (120 * 1000)) {
            server.setConfigUpdateTimeout(120 * 1000);
        }

        setupTestApplication();
    }

    @Before
    public void configSetUp() throws Exception {
        // Every server start/stop clears environment variables, set on each test run
        server.addEnvVar("repeat_phase", AbstractFATSuite.repeatPhase);
    }

    private static void setupTestApplication() throws Exception {
        JavaArchive ejbApp = ShrinkWrap.create(JavaArchive.class, appName + ".jar");
        ejbApp.addPackages(true, "com.ibm.ws.jpa.datasource.ejblocal");
        ejbApp.addPackages(true, "com.ibm.ws.jpa.datasource.model");
        ejbApp.addPackages(true, "com.ibm.ws.jpa.datasource.testlogic");
        ShrinkHelper.addDirectory(ejbApp, RESOURCE_ROOT + appFolder + "/" + appName + ".jar");

        WebArchive webApp = ShrinkWrap.create(WebArchive.class, appName + ".war");
        webApp.addPackages(true, "com.ibm.ws.jpa.datasource.ejb");
        ShrinkHelper.addDirectory(webApp, RESOURCE_ROOT + appFolder + "/" + appName + ".war");

        final JavaArchive testApiJar = buildTestAPIJar();

        final EnterpriseArchive app = ShrinkWrap.create(EnterpriseArchive.class, appNameEar);
        app.addAsModule(ejbApp);
        app.addAsModule(webApp);
        app.addAsLibrary(testApiJar);
        ShrinkHelper.addDirectory(app, RESOURCE_ROOT + appFolder, new org.jboss.shrinkwrap.api.Filter<ArchivePath>() {
            @Override
            public boolean include(ArchivePath arg0) {
                if (arg0.get().startsWith("/META-INF/")) {
                    return true;
                }
                return false;
            }
        });

        ShrinkHelper.exportToServer(server, "apps", app);

        Application appRecord = new Application();
        appRecord.setLocation(appNameEar);
        appRecord.setName(appName);

        // setup the thirdparty classloader for Hibernate and OpenJPA
        if (AbstractFATSuite.repeatPhase != null && AbstractFATSuite.repeatPhase.contains("hibernate")) {
            ConfigElementList<ClassloaderElement> cel = appRecord.getClassloaders();
            ClassloaderElement loader = new ClassloaderElement();
            loader.getCommonLibraryRefs().add("HibernateLib");
            cel.add(loader);
        } else if (AbstractFATSuite.repeatPhase != null && AbstractFATSuite.repeatPhase.contains("openjpa")) {
            ConfigElementList<ClassloaderElement> cel = appRecord.getClassloaders();
            ClassloaderElement loader = new ClassloaderElement();
            loader.getCommonLibraryRefs().add("OpenJPALib");
            cel.add(loader);
        }

        ServerConfiguration sc = server.getServerConfiguration();
        sc.getApplications().add(appRecord);
        server.updateServerConfiguration(sc);
    }

    private static void setCheckpointPhase(CheckpointPhase phase, LibertyServer server) throws Exception {
        Map<String, String> jvmOptions = server.getJvmOptionsAsMap();
        switch (phase) {
            case BEFORE_APP_START:
                jvmOptions.put("-Dio.openliberty.checkpoint.stub.criu", "true");
                server.setExtraArgs(CHECKPOINT_BEFORE_APP_START);
                break;
            case AFTER_APP_START:
                jvmOptions.put("-Dio.openliberty.checkpoint.stub.criu", "true");
                server.setExtraArgs(CHECKPOINT_AFTER_APP_START);
                break;
            default:
                jvmOptions.remove("-Dio.openliberty.checkpoint.stub.criu");
                server.setExtraArgs(CHECKPOINT_INACTIVE);
        }
        server.setJvmOptions(jvmOptions);
    }

    // testing for CheckpointPhase.INACTIVE

    @Test
    public void jpa_INACTIVE_testCheckpointDataSource_SF_AMJTA_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.INACTIVE;
        final String resourceType = "SF_AMJTA";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_INACTIVE_testCheckpointDataSource_SF_AMRL_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.INACTIVE;
        final String resourceType = "SF_AMRL";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_INACTIVE_testCheckpointDataSource_SF_CMTS_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.INACTIVE;
        final String resourceType = "SF_CMTS";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_INACTIVE_testCheckpointDataSource_SFEx_CMEX_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.INACTIVE;
        final String resourceType = "SFEx_CMEX";
        testCheckpointDataSource(EJB_SFEX_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_INACTIVE_testCheckpointDataSource_SL_AMJTA_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.INACTIVE;
        final String resourceType = "SL_AMJTA";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_INACTIVE_testCheckpointDataSource_SL_AMRL_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.INACTIVE;
        final String resourceType = "SL_AMRL";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_INACTIVE_testCheckpointDataSource_SL_CMTS_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.INACTIVE;
        final String resourceType = "SL_CMTS";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    // testing for CheckpointPhase.BEFORE_APP_START

    @Test
    public void jpa_BAS_testCheckpointDataSource_SF_AMJTA_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.BEFORE_APP_START;
        final String resourceType = "SF_AMJTA";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_BAS_testCheckpointDataSource_SF_AMRL_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.BEFORE_APP_START;
        final String resourceType = "SF_AMRL";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_BAS_testCheckpointDataSource_SF_CMTS_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.BEFORE_APP_START;
        final String resourceType = "SF_CMTS";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_BAS_testCheckpointDataSource_SFEx_CMEX_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.BEFORE_APP_START;
        final String resourceType = "SFEx_CMEX";
        testCheckpointDataSource(EJB_SFEX_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_BAS_testCheckpointDataSource_SL_AMJTA_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.BEFORE_APP_START;
        final String resourceType = "SL_AMJTA";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_BAS_testCheckpointDataSource_SL_AMRL_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.BEFORE_APP_START;
        final String resourceType = "SL_AMRL";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_BAS_testCheckpointDataSource_SL_CMTS_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.BEFORE_APP_START;
        final String resourceType = "SL_CMTS";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    // testing for CheckpointPhase.AFTER_APP_START

    @Test
    public void jpa_AAS_testCheckpointDataSource_SF_AMJTA_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.AFTER_APP_START;
        final String resourceType = "SF_AMJTA";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_AAS_testCheckpointDataSource_SF_AMRL_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.AFTER_APP_START;
        final String resourceType = "SF_AMRL";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_AAS_testCheckpointDataSource_SF_CMTS_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.AFTER_APP_START;
        final String resourceType = "SF_CMTS";
        testCheckpointDataSource(EJB_SF_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_AAS_testCheckpointDataSource_SFEx_CMEX_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.AFTER_APP_START;
        final String resourceType = "SFEx_CMEX";
        testCheckpointDataSource(EJB_SFEX_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_AAS_testCheckpointDataSource_SL_AMJTA_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.AFTER_APP_START;
        final String resourceType = "SL_AMJTA";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_AAS_testCheckpointDataSource_SL_AMRL_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.AFTER_APP_START;
        final String resourceType = "SL_AMRL";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    @Test
    public void jpa_AAS_testCheckpointDataSource_SL_CMTS_EJB() throws Exception {
        final CheckpointPhase phase = CheckpointPhase.AFTER_APP_START;
        final String resourceType = "SL_CMTS";
        testCheckpointDataSource(EJB_SL_SERVLET_NAME, phase, resourceType);
    }

    /**
     * Test that enabling Checkpoint will force JPA Container to delay EntityManagerFactory creation until the application is used
     * Validate that after the server starts, the EntityManagerFactory created is using a GenericDatasource.
     */
    private void testCheckpointDataSource(String servletName, CheckpointPhase checkpointPhase, String resourceType) throws Exception {
        Assert.assertFalse("Server is already started!", server.isStarted());

        // set Checkpoint
        JPADataSourceCheckpointTest_EJB.setCheckpointPhase(checkpointPhase, server);

        // Start the server
        server.startServer();
        try {
            if (CheckpointPhase.INACTIVE.equals(checkpointPhase)) {
                // Verify that the server application started
                Assert.assertNotNull(appName + " was expected to start, but did not", server.waitForStringInLog(MSG_APP_START, server.getDefaultTraceFile()));
                // Make sure that the checkpoint was not requested
                Assert.assertTrue("Expected a server checkpoint to be requested, but it was not", server.findStringsInTrace(MSG_CHECKPOINT).isEmpty());
                // Verify that the application didn't restart after checkpoint
                // Using DefaultTraceFile as checkpoint occurred during Server Start and trace will have split at that time
                Assert.assertTrue("Application restarted unexpectedly", server.findStringsInLogsUsingMark(MSG_APP_RESTART, server.getDefaultTraceFile()).isEmpty());

                // Verify that JPA Container didn't use a GenericDatasource
                Assert.assertTrue("Expected JPA Container to log returning a GenericDatasource",
                                  server.findStringsInTrace("JPAPUnitInfo.*getJPADataSource.*GenericDataSource.*Exit").isEmpty());
                // Verify that JPA Container has created a real Datasource
                Assert.assertFalse("Expected JPA Container to not log returning a WSJdbcDataSource",
                                   server.findStringsInTrace("JPAPUnitInfo.*getJPADataSource.*WSJdbcDataSource.*Exit").isEmpty());

                System.out.println("setTraceMarkToEndOfDefaultTrace");
                server.setTraceMarkToEndOfDefaultTrace();

                // Poke the application so that JPA Container will access the datasource
                String method = "jpa_checkpoint_datasource_testInsert_EJB_" + resourceType + "_Web";
                runTest(server, appName + '/' + servletName, method);

                // Verify that the application method was invoked
                Assert.assertNotNull("Expected method " + method + " to be called", server.waitForStringInTraceUsingMark("END.*" + method));
                // Verify that the application didn't restart once the application was used
                Assert.assertTrue("Application restarted unexpectedly", server.findStringsInLogsUsingMark(MSG_APP_RESTART, server.getDefaultTraceFile()).isEmpty());
            } else {
                // Verify that the server application started
                Assert.assertNotNull(appName + " was expected to start, but did not", server.waitForStringInLog(MSG_APP_START, server.getDefaultTraceFile()));
                // Make sure that the checkpoint was requested
                Assert.assertFalse("Expected a server checkpoint to be requested, but it was not", server.findStringsInTrace(MSG_CHECKPOINT).isEmpty());
                // Verify that the application didn't restart after checkpoint
                // Using DefaultTraceFile as checkpoint occurred during Server Start and trace will have split at that time
                Assert.assertTrue("Application restarted unexpectedly", server.findStringsInLogsUsingMark(MSG_APP_RESTART, server.getDefaultTraceFile()).isEmpty());

                // Verify that JPA Container used a GenericDatasource
                Assert.assertFalse("Expected JPA Container to log returning a GenericDatasource",
                                   server.findStringsInTrace("JPAPUnitInfo.*getJPADataSource.*GenericDataSource.*Exit").isEmpty());
                // Verify that JPA Container hasn't created a real Datasource yet
                Assert.assertTrue("Expected JPA Container to not log returning a WSJdbcDataSource",
                                  server.findStringsInTrace("JPAPUnitInfo.*getJPADataSource.*WSJdbcDataSource.*Exit").isEmpty());

                System.out.println("setTraceMarkToEndOfDefaultTrace");
                server.setTraceMarkToEndOfDefaultTrace();

                // Poke the application so that JPA Container will perform a datasource lookup
                String method = "jpa_checkpoint_datasource_testInsert_EJB_" + resourceType + "_Web";
                runTest(server, appName + '/' + servletName, method);

                // Verify that the application method was invoked
                Assert.assertNotNull("Expected method " + method + " to be called", server.waitForStringInTraceUsingMark("END.*" + method));
                // Verify that the application didn't restart once the application was used
                Assert.assertTrue("Application restarted unexpectedly", server.findStringsInLogsUsingMark(MSG_APP_RESTART, server.getDefaultTraceFile()).isEmpty());
                // Verify that JPA Container no longer used a GenericDatasource
                Assert.assertTrue("Expected JPA Container lookup datasource",
                                  server.findStringsInLogsUsingMark("JPAPUnitInfo.*getJPADataSource.*GenericDataSource.*Exit", server.getDefaultTraceFile()).isEmpty());
                // Verify that JPA Container performed a lookup for a real Datasource now
                Assert.assertFalse("Expected JPA Container lookup datasource",
                                   server.findStringsInLogsUsingMark("JPAPUnitInfo.*getJPADataSource.*WSJdbcDataSource.*Exit", server.getDefaultTraceFile()).isEmpty());
            }
        } finally {
            if (server.isStarted()) {
                server.stopServer("CWWJP9991W", // From Eclipselink drop-and-create tables option
                                  "WTRN0074E: Exception caught from before_completion synchronization operation" // RuntimeException test, expected
                );
            }
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        bannerEnd(JPADataSourceCheckpointTest_EJB.class, timestart);
    }
}
