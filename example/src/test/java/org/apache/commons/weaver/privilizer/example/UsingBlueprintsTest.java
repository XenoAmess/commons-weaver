/*
 *  Copyright the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.commons.weaver.privilizer.example;

import static org.junit.Assert.*;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.junit.Before;
import org.junit.Test;

public class UsingBlueprintsTest {

    private UsingBlueprints usingBlueprints;

    @Before
    public void setUp() throws Exception {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                System.setProperty("foo", "foo-value");
                System.setProperty("bar", "bar-value");
                System.setProperty("baz", "baz-value");
                return null;
            }
        });
        usingBlueprints = new UsingBlueprints();
    }

    @Test
    public void testUtilsReadPublicConstant() {
        assertEquals(Utils.FOO, usingBlueprints.utilsReadPublicConstant());
    }

    @Test
    public void testUtilsReadPrivateField() {
        assertEquals(999, usingBlueprints.utilsReadPrivateField());
    }

    @Test
    public void testUtilsGetProperty() {
        assertEquals("foo-value", usingBlueprints.utilsGetProperty());
    }

    @Test
    public void testUtilsGetProperty_String() {
        assertEquals("foo-value", usingBlueprints.utilsGetProperty("foo"));
        assertEquals("bar-value", usingBlueprints.utilsGetProperty("bar"));
        assertEquals("baz-value", usingBlueprints.utilsGetProperty("baz"));
    }

    @Test
    public void testUtilsGetProperty_int_String() {
        assertEquals("foo-value", usingBlueprints.utilsGetProperty(2, "foo"));
        assertEquals("bar-value", usingBlueprints.utilsGetProperty(2, "bar"));
        assertEquals("baz-value", usingBlueprints.utilsGetProperty(2, "baz"));
    }

    @Test
    public void testMoreGetProperty() {
        assertEquals("bar-value", usingBlueprints.moreGetProperty());
    }

    @Test
    public void testMoreGetTopStackElementClassName() {
        assertEquals(Utils.More.class.getName(), usingBlueprints.moreGetTopStackElementClassName());
    }

}
