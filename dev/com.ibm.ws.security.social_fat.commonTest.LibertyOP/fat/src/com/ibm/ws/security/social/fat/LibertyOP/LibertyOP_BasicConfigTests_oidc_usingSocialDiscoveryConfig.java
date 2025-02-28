/*******************************************************************************
 * Copyright (c) 2017, 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.security.social.fat.LibertyOP;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.security.oauth_oidc.fat.commonTest.Constants;
import com.ibm.ws.security.oauth_oidc.fat.commonTest.RSCommonTestTools;
import com.ibm.ws.security.social.fat.commonTests.Social_BasicDiscoveryConfigTests;
import com.ibm.ws.security.social.fat.utils.SocialConstants;
import com.ibm.ws.security.social.fat.utils.SocialMessageConstants;
import com.ibm.ws.security.social.fat.utils.SocialTestSettings;

import componenttest.custom.junit.runner.FATRunner;
import componenttest.custom.junit.runner.Mode;
import componenttest.custom.junit.runner.Mode.TestMode;
import componenttest.rules.repeater.RepeatTests;
import componenttest.topology.impl.LibertyServerWrapper;

@RunWith(FATRunner.class)
@LibertyServerWrapper
@Mode(TestMode.FULL)
public class LibertyOP_BasicConfigTests_oidc_usingSocialDiscoveryConfig extends Social_BasicDiscoveryConfigTests {

    public static Class<?> thisClass = LibertyOP_BasicConfigTests_oidc_usingSocialDiscoveryConfig.class;

    public static RSCommonTestTools rsTools = new RSCommonTestTools();

    @ClassRule
    public static RepeatTests r = RepeatTests.withoutModification();

    @BeforeClass
    public static void setUp() throws Exception {
        classOverrideValidationEndpointValue = Constants.USERINFO_ENDPOINT;

        isTestingOidc = true; // affect superclass behavior
        List<String> startMsgs = new ArrayList<String>();
        startMsgs.add("CWWKT0016I.*" + SocialConstants.SOCIAL_DEFAULT_CONTEXT_ROOT);

        List<String> extraApps = new ArrayList<String>();
        extraApps.add(SocialConstants.HELLOWORLD_SERVLET);

        // TODO fix
        List<String> opStartMsgs = new ArrayList<String>();
        //        opStartMsgs.add("CWWKS1600I.*" + SocialConstants.OIDCCONFIGMEDIATOR_APP);
        opStartMsgs.add("CWWKS1631I.*");

        // TODO fix
        List<String> opExtraApps = new ArrayList<String>();
        opExtraApps.add(SocialConstants.OP_SAMPLE_APP);

        String[] propagationTokenTypes = rsTools.chooseTokenSettings(SocialConstants.OIDC_OP);
        String tokenType = propagationTokenTypes[0];
        String certType = propagationTokenTypes[1];
        Log.info(thisClass, "setupBeforeTest", "inited tokenType to: " + tokenType);

        socialSettings = new SocialTestSettings();
        testSettings = socialSettings;

        // TODO - vary whether OP runs with access_token or jwt as access_token
        testOPServer = commonSetUp(SocialConstants.SERVER_NAME + ".LibertyOP.op", "op_server_disc_orig.xml", SocialConstants.OIDC_OP, null, SocialConstants.DO_NOT_USE_DERBY, opStartMsgs, null, SocialConstants.OIDC_OP, true, true, tokenType, certType);
        genericTestServer = commonSetUp(SocialConstants.SERVER_NAME + ".LibertyOP.socialDisc", "server_LibertyOP_basicConfigTests_oidc_usingSocialConfig.xml", SocialConstants.GENERIC_SERVER, extraApps, SocialConstants.DO_NOT_USE_DERBY, startMsgs);
        // following added for hostnameverificationtest
        genericTestServer.addIgnoredServerException(SocialMessageConstants.CWWKS6029E_NO_SIGNING_KEY);
        genericTestServer.addIgnoredServerException(SocialMessageConstants.CWWKS6031E_JWT_CONSUMER_CANNOT_PROCESS_STRING);
        genericTestServer.addIgnoredServerException(SocialMessageConstants.CWWKS5453E_PROBLEM_CREATING_JWT);
        genericTestServer.addIgnoredServerException(SocialMessageConstants.CWWKO0801E_CANNOT_INIT_SSL); // 248970

        setActionsForProvider(SocialConstants.LIBERTYOP_PROVIDER, SocialConstants.OIDC_OP);

        setGenericVSSpeicificProviderFlags(GenericConfig, "server_LibertyOP_basicTests_oidc_usingSocialConfig");

        addServerExceptions();

        socialSettings = updateLibertyOPSettings(socialSettings);

    }

}
