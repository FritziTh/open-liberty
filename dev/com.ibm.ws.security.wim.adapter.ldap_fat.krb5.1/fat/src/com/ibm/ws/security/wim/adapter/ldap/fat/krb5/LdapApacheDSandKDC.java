/*******************************************************************************
 * Copyright (c) 2021, 2022 IBM Corporation and others.
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
package com.ibm.ws.security.wim.adapter.ldap.fat.krb5;

import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.name.Dn;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.security.wim.adapter.ldap.fat.krb5.utils.LdapKerberosUtils;

import componenttest.custom.junit.runner.FATRunner;

@RunWith(FATRunner.class)
public class LdapApacheDSandKDC extends ApacheDSandKDC {

    private static final Class<?> c = LdapApacheDSandKDC.class;

    // regular user/group
    protected static final String vmmUser1 = "user1";
    protected static final String vmmUser1DN = "uid=vmmUser1," + BASE_DN;
    protected static final String vmmUser1pwd = "password";
    protected static final String vmmGroup1 = "vmmGroup1";
    protected static final String vmmGroup1DN = "cn=" + vmmGroup1 + "," + BASE_DN;

    @BeforeClass
    public static void setup() throws Exception {

        BASE_DN = LdapKerberosUtils.BASE_DN;

        DOMAIN = LdapKerberosUtils.DOMAIN;

        bindPassword = LdapKerberosUtils.BIND_PASSWORD;

        bindUserName = LdapKerberosUtils.BIND_USER;

        bindPrincipalName = LdapKerberosUtils.BIND_PRINCIPAL_NAME;

        ldapUser = "ldap";

        krbtgtUser = "krbtgt";

        setupService();

        addApplicationUserAndGroup();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        tearDownService();
    }

    /**
     * Add a starter user and group for general servlet login and searches
     *
     */
    public static void addApplicationUserAndGroup() throws Exception {
        Log.info(c, "addApplicationUserAndGroup", "Adding basic user and group");
        Entry entry = getDirectoryService().newEntry(new Dn(vmmUser1DN));
        entry.add("objectclass", "inetorgperson");
        entry.add("uid", vmmUser1);
        entry.add("sn", "user1");
        entry.add("cn", "user1");
        entry.add("userPassword", vmmUser1pwd);
        getDirectoryService().getAdminSession().add(entry);

        entry = getDirectoryService().newEntry(new Dn(vmmGroup1DN));
        entry.add("objectclass", "groupOfNames");
        entry.add("member", vmmUser1DN);
        getDirectoryService().getAdminSession().add(entry);
        Log.info(c, "addApplicationUserAndGroup", "Adding basic user and group");
    }

}
