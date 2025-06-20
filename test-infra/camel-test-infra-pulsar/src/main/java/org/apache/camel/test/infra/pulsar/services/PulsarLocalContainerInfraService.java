/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.test.infra.pulsar.services;

import java.time.Duration;

import org.apache.camel.spi.annotations.InfraService;
import org.apache.camel.test.infra.common.LocalPropertyResolver;
import org.apache.camel.test.infra.common.services.ContainerEnvironmentUtil;
import org.apache.camel.test.infra.common.services.ContainerService;
import org.apache.camel.test.infra.pulsar.common.PulsarProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.PulsarContainer;
import org.testcontainers.utility.DockerImageName;

@InfraService(service = PulsarInfraService.class,
              description = "Distributed messaging and streaming platform",
              serviceAlias = { "pulsar" })
public class PulsarLocalContainerInfraService implements PulsarInfraService, ContainerService<PulsarContainer> {

    private static final Logger LOG = LoggerFactory.getLogger(PulsarLocalContainerInfraService.class);

    private final PulsarContainer container;

    public PulsarLocalContainerInfraService() {
        this(LocalPropertyResolver.getProperty(PulsarLocalContainerInfraService.class, PulsarProperties.PULSAR_CONTAINER));
    }

    public PulsarLocalContainerInfraService(String imageName) {
        container = initContainer(imageName);
    }

    public PulsarLocalContainerInfraService(PulsarContainer container) {
        this.container = container;
    }

    protected PulsarContainer initContainer(String imageName) {
        class TestInfraPulsarContainer extends PulsarContainer {
            public TestInfraPulsarContainer(boolean fixedPort) {
                super(DockerImageName.parse(imageName)
                        .asCompatibleSubstituteFor("apachepulsar/pulsar"));

                withStartupTimeout(Duration.ofMinutes(3L));

                if (fixedPort) {
                    addFixedExposedPort(6650, 6650);
                    addFixedExposedPort(8085, 8080);
                }
            }
        }

        return new TestInfraPulsarContainer(ContainerEnvironmentUtil.isFixedPort(this.getClass()));
    }

    @Override
    public void registerProperties() {
        System.setProperty(PulsarProperties.PULSAR_ADMIN_URL, getPulsarAdminUrl());
        System.setProperty(PulsarProperties.PULSAR_BROKER_URL, getPulsarBrokerUrl());
    }

    @Override
    public void initialize() {
        LOG.info("Trying to start the Pulsar container");
        container.start();

        registerProperties();
        LOG.info("Pulsar instance running at {}", getPulsarAdminUrl());
        LOG.info("Pulsar admin URL available at {}", getPulsarAdminUrl());
    }

    @Override
    public void shutdown() {
        LOG.info("Stopping the Pulsar container");
        container.stop();
    }

    @Override
    public PulsarContainer getContainer() {
        return container;
    }

    @Override
    public String getPulsarAdminUrl() {
        return container.getHttpServiceUrl();
    }

    @Override
    public String getPulsarBrokerUrl() {
        return container.getPulsarBrokerUrl();
    }
}
