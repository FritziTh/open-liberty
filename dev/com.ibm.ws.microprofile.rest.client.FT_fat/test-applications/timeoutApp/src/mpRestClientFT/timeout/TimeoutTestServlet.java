/*******************************************************************************
 * Copyright (c) 2019, 2021 IBM Corporation and others.
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
package mpRestClientFT.timeout;

import static componenttest.rules.repeater.MicroProfileActions.MP50_ID;
import static componenttest.rules.repeater.MicroProfileActions.MP60_ID;
import static componenttest.rules.repeater.MicroProfileActions.MP61_ID;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.Test;

import componenttest.annotation.SkipForRepeat;
import componenttest.app.FATServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/TimeoutTestServlet")
public class TimeoutTestServlet extends FATServlet {
    private final static Logger LOG = Logger.getLogger(TimeoutTestServlet.class.getName());

    private final static int WAIT_TIME = 10; //seconds

    @RestClient
    @Inject
    TimeoutClient client;


    @Test
    public void testTimeoutAnnotationOnRestClientAsyncMethod(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        testTimeout(() -> {return client.rcAsync(WAIT_TIME);}, WAIT_TIME);
    }

    @Test()
    public void testTimeoutAndAsyncAnnotationOnMethod(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        testTimeout(() -> {return client.ftAsync(WAIT_TIME);}, WAIT_TIME);
    }

    @Test
    @SkipForRepeat({
        MP50_ID,
        MP60_ID, 
        MP61_ID})  // this is proper behavior - @Timeout not expected to stop blocking I/O operations
                   // note that this works with previous MP Rest Client versions because we set the http
                   // connect and read timeouts based on the value of the @Timeout annotation.
    public void testTimeoutOnSyncMethod(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        testTimeout(() -> {return client.sync(WAIT_TIME);}, WAIT_TIME);
    }

    private <T> T testTimeout(Callable<T> callable, final int waitTime) throws Exception {
        final long startTime = System.nanoTime();
        try {
            return callable.call();
        } catch (Exception expected) {
            LOG.log(Level.INFO, "Caught expected exception", expected);
        } finally {
            final long elapsedTime = System.nanoTime() - startTime;
            assertTrue("Rest Client method did not time out as expected",
                       TimeUnit.NANOSECONDS.toSeconds(elapsedTime) < waitTime );
        }
        return null;
    }
}