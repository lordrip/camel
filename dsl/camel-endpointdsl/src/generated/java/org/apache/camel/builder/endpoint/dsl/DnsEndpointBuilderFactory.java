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
package org.apache.camel.builder.endpoint.dsl;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
import javax.annotation.processing.Generated;
import org.apache.camel.builder.EndpointConsumerBuilder;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.AbstractEndpointBuilder;

/**
 * Perform DNS queries using DNSJava.
 * 
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointDslMojo")
public interface DnsEndpointBuilderFactory {


    /**
     * Builder for endpoint for the DNS component.
     */
    public interface DnsEndpointBuilder extends EndpointProducerBuilder {
        default AdvancedDnsEndpointBuilder advanced() {
            return (AdvancedDnsEndpointBuilder) this;
        }
    }

    /**
     * Advanced builder for endpoint for the DNS component.
     */
    public interface AdvancedDnsEndpointBuilder
            extends
                EndpointProducerBuilder {
        default DnsEndpointBuilder basic() {
            return (DnsEndpointBuilder) this;
        }
        /**
         * Whether the producer should be started lazy (on the first message).
         * By starting lazy you can use this to allow CamelContext and routes to
         * startup in situations where a producer may otherwise fail during
         * starting and cause the route to fail being started. By deferring this
         * startup to be lazy then the startup failure can be handled during
         * routing messages via Camel's routing error handlers. Beware that when
         * the first message is processed then creating and starting the
         * producer may take a little time and prolong the total processing time
         * of the processing.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: false
         * Group: producer (advanced)
         * 
         * @param lazyStartProducer the value to set
         * @return the dsl builder
         */
        default AdvancedDnsEndpointBuilder lazyStartProducer(
                boolean lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
        /**
         * Whether the producer should be started lazy (on the first message).
         * By starting lazy you can use this to allow CamelContext and routes to
         * startup in situations where a producer may otherwise fail during
         * starting and cause the route to fail being started. By deferring this
         * startup to be lazy then the startup failure can be handled during
         * routing messages via Camel's routing error handlers. Beware that when
         * the first message is processed then creating and starting the
         * producer may take a little time and prolong the total processing time
         * of the processing.
         * 
         * The option will be converted to a &lt;code&gt;boolean&lt;/code&gt;
         * type.
         * 
         * Default: false
         * Group: producer (advanced)
         * 
         * @param lazyStartProducer the value to set
         * @return the dsl builder
         */
        default AdvancedDnsEndpointBuilder lazyStartProducer(
                String lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
    }

    public interface DnsBuilders {
        /**
         * DNS (camel-dns)
         * Perform DNS queries using DNSJava.
         * 
         * Category: networking
         * Since: 2.7
         * Maven coordinates: org.apache.camel:camel-dns
         * 
         * @return the dsl builder for the headers' name.
         */
        default DnsHeaderNameBuilder dns() {
            return DnsHeaderNameBuilder.INSTANCE;
        }
        /**
         * DNS (camel-dns)
         * Perform DNS queries using DNSJava.
         * 
         * Category: networking
         * Since: 2.7
         * Maven coordinates: org.apache.camel:camel-dns
         * 
         * Syntax: <code>dns:dnsType</code>
         * 
         * Path parameter: dnsType (required)
         * The type of the lookup.
         * There are 4 enums and the value can be one of: dig, ip, lookup,
         * wikipedia
         * 
         * @param path dnsType
         * @return the dsl builder
         */
        default DnsEndpointBuilder dns(String path) {
            return DnsEndpointBuilderFactory.endpointBuilder("dns", path);
        }
        /**
         * DNS (camel-dns)
         * Perform DNS queries using DNSJava.
         * 
         * Category: networking
         * Since: 2.7
         * Maven coordinates: org.apache.camel:camel-dns
         * 
         * Syntax: <code>dns:dnsType</code>
         * 
         * Path parameter: dnsType (required)
         * The type of the lookup.
         * There are 4 enums and the value can be one of: dig, ip, lookup,
         * wikipedia
         * 
         * @param componentName to use a custom component name for the endpoint
         * instead of the default name
         * @param path dnsType
         * @return the dsl builder
         */
        default DnsEndpointBuilder dns(String componentName, String path) {
            return DnsEndpointBuilderFactory.endpointBuilder(componentName, path);
        }
    }

    /**
     * The builder of headers' name for the DNS component.
     */
    public static class DnsHeaderNameBuilder {
        /**
         * The internal instance of the builder used to access to all the
         * methods representing the name of headers.
         */
        private static final DnsHeaderNameBuilder INSTANCE = new DnsHeaderNameBuilder();

        /**
         * The DNS class of the lookup. Should match the values of
         * org.xbill.dns.DClass. Optional.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: lookup dig
         * 
         * @return the name of the header {@code dns.class}.
         */
        public String dnsClass() {
            return "dns.class";
        }

        /**
         * The name to lookup.
         * 
         * The option is a: {@code String} type.
         * 
         * Required: true
         * Group: lookup
         * 
         * @return the name of the header {@code dns.name}.
         */
        public String dnsName() {
            return "dns.name";
        }

        /**
         * The domain name.
         * 
         * The option is a: {@code String} type.
         * 
         * Required: true
         * Group: ip
         * 
         * @return the name of the header {@code dns.domain}.
         */
        public String dnsDomain() {
            return "dns.domain";
        }

        /**
         * The server in particular for the query. If none is given, the default
         * one specified by the OS will be used. Optional.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: dig
         * 
         * @return the name of the header {@code dns.server}.
         */
        public String dnsServer() {
            return "dns.server";
        }

        /**
         * The type of the lookup. Should match the values of
         * org.xbill.dns.Type. Optional.
         * 
         * The option is a: {@code String} type.
         * 
         * Group: lookup dig
         * 
         * @return the name of the header {@code dns.type}.
         */
        public String dnsType() {
            return "dns.type";
        }

        /**
         * The term.
         * 
         * The option is a: {@code } type.
         * 
         * Required: true
         * Group: wikipedia
         * 
         * @return the name of the header {@code term}.
         */
        public String term() {
            return "term";
        }
    }
    static DnsEndpointBuilder endpointBuilder(String componentName, String path) {
        class DnsEndpointBuilderImpl extends AbstractEndpointBuilder implements DnsEndpointBuilder, AdvancedDnsEndpointBuilder {
            public DnsEndpointBuilderImpl(String path) {
                super(componentName, path);
            }
        }
        return new DnsEndpointBuilderImpl(path);
    }
}