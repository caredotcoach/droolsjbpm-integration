/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.kie.server.controller.plugin;

import java.util.Arrays;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.kie.server.controller.api.model.spec.ContainerSpecList;
import org.kie.server.controller.log.LogHelper;

/**
 * Display container info
 */
@Mojo( name = "get-containers", defaultPhase = LifecyclePhase.DEPLOY, threadSafe = true, requiresProject = false)
public class GetContainersMojo extends KieControllerMojo {

    @Parameter(property = "kie-ctrl.template-id", required = true)
    protected String templateId;

    @Override
    public void executeCommand() throws MojoExecutionException, MojoFailureException {

        getLog().info("Server Template Id: " + templateId);

        ContainerSpecList containers = kieControllerGateway.getContainers(templateId);

        LogHelper.logContainers(getLog(), Arrays.asList(containers.getContainerSpecs()));

    }

}
