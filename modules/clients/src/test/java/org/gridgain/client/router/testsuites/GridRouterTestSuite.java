/* 
 Copyright (C) GridGain Systems. All Rights Reserved.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.client.router.testsuites;

import junit.framework.*;
import org.gridgain.client.router.*;

/**
 * Test suite for router tests.
 *
 * @author @java.author
 * @version @java.version
 */
public class GridRouterTestSuite extends TestSuite {
    /**
     * @return Suite that contains all router tests.
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite("Gridgain Router Test Suite");

        suite.addTest(new TestSuite(GridRouterFactorySelfTest.class));
        suite.addTest(new TestSuite(GridTcpRouterSelfTest.class));
        suite.addTest(new TestSuite(GridTcpSslRouterSelfTest.class));
        suite.addTest(new TestSuite(GridTcpRouterMultiNodeSelfTest.class));
        suite.addTest(new TestSuite(GridClientFailedInitSelfTest.class));

        return suite;
    }
}
