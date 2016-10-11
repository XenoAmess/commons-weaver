/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.weaver.maven;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.commons.weaver.CleanProcessor;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Implements weaver preparation.
 */
public abstract class AbstractPrepareMojo extends AbstractCWMojo {

    @Override
    protected void doExecute(final File target, final List<String> classpath, final Properties config)
        throws MojoExecutionException {
        if (!target.isDirectory()) {
            return;
        }
        try {
            final CleanProcessor cleanProcessor = new CleanProcessor(classpath, target, config);
            cleanProcessor.clean();
        } catch (Exception e) {
            throw new MojoExecutionException("cleaning failed due to " + e.getMessage(), e);
        }
    }

}
