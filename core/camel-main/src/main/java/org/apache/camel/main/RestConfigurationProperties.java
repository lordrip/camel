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
package org.apache.camel.main;

import java.util.HashMap;

import org.apache.camel.spi.BootstrapCloseable;
import org.apache.camel.spi.Configurer;
import org.apache.camel.spi.RestConfiguration;

/**
 * Global configuration for Rest DSL.
 */
@Configurer(extended = true)
public class RestConfigurationProperties extends RestConfiguration implements BootstrapCloseable {

    private MainConfigurationProperties parent;

    public RestConfigurationProperties(MainConfigurationProperties parent) {
        this.parent = parent;
    }

    public MainConfigurationProperties end() {
        return parent;
    }

    @Override
    public void close() {
        parent = null;
        setComponentProperties(null);
        setEndpointProperties(null);
        setConsumerProperties(null);
        setDataFormatProperties(null);
        setApiProperties(null);
        setCorsHeaders(null);
        setValidationLevels(null);
    }

    // getter and setters
    // --------------------------------------------------------------

    // these are inherited from the parent class

    // fluent builders
    // --------------------------------------------------------------

    /**
     * The Camel Rest component to use for the REST transport (consumer), such as netty-http, jetty, servlet, undertow.
     * If no component has been explicit configured, then Camel will lookup if there is a Camel component that
     * integrates with the Rest DSL, or if a org.apache.camel.spi.RestConsumerFactory is registered in the registry. If
     * either one is found, then that is being used.
     */
    public RestConfigurationProperties withComponent(String component) {
        setComponent(component);
        return this;
    }

    /**
     * The name of the Camel component to use as the REST API (such as swagger)
     */
    public RestConfigurationProperties withApiComponent(String apiComponent) {
        setApiComponent(apiComponent);
        return this;
    }

    /**
     * Sets the name of the Camel component to use as the REST producer
     */
    public RestConfigurationProperties withProducerComponent(String producerComponent) {
        setProducerComponent(producerComponent);
        return this;
    }

    /**
     * The scheme to use for exposing the REST service. Usually http or https is supported.
     * <p/>
     * The default value is http
     */
    public RestConfigurationProperties withScheme(String scheme) {
        setScheme(scheme);
        return this;
    }

    /**
     * The hostname to use for exposing the REST service.
     */
    public RestConfigurationProperties withHost(String host) {
        setHost(host);
        return this;
    }

    /**
     * To use a specific hostname for the API documentation (such as swagger or openapi)
     * <p/>
     * This can be used to override the generated host with this configured hostname
     */
    public RestConfigurationProperties withApiHost(String apiHost) {
        setApiHost(apiHost);
        return this;
    }

    /**
     * Whether to use X-Forward headers to set host etc. for OpenApi.
     *
     * This may be needed in special cases involving reverse-proxy and networking going from HTTP to HTTPS etc. Then the
     * proxy can send X-Forward headers (X-Forwarded-Proto) that influences the host names in the OpenAPI schema that
     * camel-openapi-java generates from Rest DSL routes.
     */
    public RestConfigurationProperties withUseXForwardHeaders(boolean useXForwardHeaders) {
        setUseXForwardHeaders(useXForwardHeaders);
        return this;
    }

    /**
     * The port number to use for exposing the REST service. Notice if you use servlet component then the port number
     * configured here does not apply, as the port number in use is the actual port number the servlet component is
     * using. eg if using Apache Tomcat its the tomcat http port, if using Apache Karaf its the HTTP service in Karaf
     * that uses port 8181 by default etc. Though in those situations setting the port number here, allows tooling and
     * JMX to know the port number, so its recommended to set the port number to the number that the servlet engine
     * uses.
     */
    public RestConfigurationProperties withPort(int port) {
        setPort(port);
        return this;
    }

    /**
     * Sets the location of the api document (swagger api) the REST producer will use to validate the REST uri and query
     * parameters are valid accordingly to the api document. This requires adding camel-openapi-java to the classpath,
     * and any miss configuration will let Camel fail on startup and report the error(s).
     * <p/>
     * The location of the api document is loaded from classpath by default, but you can use <tt>file:</tt> or
     * <tt>http:</tt> to refer to resources to load from file or http url.
     */
    public RestConfigurationProperties withProducerApiDoc(String producerApiDoc) {
        setProducerApiDoc(producerApiDoc);
        return this;
    }

    /**
     * Sets a leading context-path the REST services will be using.
     * <p/>
     * This can be used when using components such as <tt>camel-servlet</tt> where the deployed web application is
     * deployed using a context-path. Or for components such as <tt>camel-jetty</tt> or <tt>camel-netty-http</tt> that
     * includes a HTTP server.
     */
    public RestConfigurationProperties withContextPath(String contextPath) {
        setContextPath(contextPath);
        return this;
    }

    /**
     * Sets a leading API context-path the REST API services will be using.
     * <p/>
     * This can be used when using components such as <tt>camel-servlet</tt> where the deployed web application is
     * deployed using a context-path.
     */
    public RestConfigurationProperties withApiContextPath(String apiContextPath) {
        setApiContextPath(apiContextPath);
        return this;
    }

    /**
     * Sets the route id to use for the route that services the REST API.
     * <p/>
     * The route will by default use an auto assigned route id.
     */
    public RestConfigurationProperties withApiContextRouteId(String apiContextRouteId) {
        setApiContextRouteId(apiContextRouteId);
        return this;
    }

    /**
     * Whether vendor extension is enabled in the Rest APIs. If enabled then Camel will include additional information
     * as vendor extension (eg keys starting with x-) such as route ids, class names etc. Not all 3rd party API gateways
     * and tools supports vendor-extensions when importing your API docs.
     */
    public RestConfigurationProperties withApiVendorExtension(boolean apiVendorExtension) {
        setApiVendorExtension(apiVendorExtension);
        return this;
    }

    /**
     * If no hostname has been explicit configured, then this resolver is used to compute the hostname the REST service
     * will be using. The possible values are: allLocalIp, localIp, localHostName
     */
    public RestConfigurationProperties withHostNameResolver(String hostNameResolver) {
        setHostNameResolver(hostNameResolver);
        return this;
    }

    /**
     * Sets the binding mode to use.
     * <p/>
     * The possible values are: auto, off, json, xml, json_xml The default value is off
     */
    public RestConfigurationProperties withBindingMode(String bindingMode) {
        setBindingMode(bindingMode);
        return this;
    }

    /**
     * Package name to use as base (offset) for classpath scanning of POJO classes are located when using binding mode
     * is enabled for JSon or XML. Multiple package names can be separated by comma.
     */
    public RestConfigurationProperties withBindingPackageScan(String bindingPackageScan) {
        setBindingPackageScan(bindingPackageScan);
        return this;
    }

    /**
     * Whether to skip binding on output if there is a custom HTTP error code header. This allows to build custom error
     * messages that do not bind to json / xml etc, as success messages otherwise will do.
     */
    public RestConfigurationProperties withSkipBindingOnErrorCode(boolean skipBindingOnErrorCode) {
        setSkipBindingOnErrorCode(skipBindingOnErrorCode);
        return this;
    }

    /**
     * Whether to enable validation of the client request to check:
     *
     * 1) Content-Type header matches what the Rest DSL consumes; returns HTTP Status 415 if validation error. 2) Accept
     * header matches what the Rest DSL produces; returns HTTP Status 406 if validation error. 3) Missing required data
     * (query parameters, HTTP headers, body); returns HTTP Status 400 if validation error. 4) Parsing error of the
     * message body (JSon, XML or Auto binding mode must be enabled); returns HTTP Status 400 if validation error.
     */
    public RestConfigurationProperties withClientRequestValidation(boolean clientRequestValidation) {
        setClientRequestValidation(clientRequestValidation);
        return this;
    }

    /**
     * Whether to check what Camel is returning as response to the client:
     *
     * 1) Status-code and Content-Type matches Rest DSL response messages. 2) Check whether expected headers is included
     * according to the Rest DSL repose message headers. 3) If the response body is JSon then check whether its valid
     * JSon. Returns 500 if validation error detected.
     */
    public RestConfigurationProperties withClientResponseValidation(boolean clientResponseValidation) {
        setClientResponseValidation(clientResponseValidation);
        return this;
    }

    /**
     * Whether to enable CORS headers in the HTTP response.
     * <p/>
     * The default value is false.
     */
    public RestConfigurationProperties withEnableCORS(boolean enableCORS) {
        setEnableCORS(enableCORS);
        return this;
    }

    /**
     * Inline routes in rest-dsl which are linked using direct endpoints.
     *
     * By default, each service in Rest DSL is an individual route, meaning that you would have at least two routes per
     * service (rest-dsl, and the route linked from rest-dsl). Enabling this allows Camel to optimize and inline this as
     * a single route, however this requires to use direct endpoints, which must be unique per service.
     *
     * This option is default <tt>false</tt>.
     */
    public RestConfigurationProperties withInlineRoutes(boolean inlineRoutes) {
        setInlineRoutes(inlineRoutes);
        return this;
    }

    /**
     * Name of specific json data format to use. By default jackson will be used. Important: This option is only for
     * setting a custom name of the data format, not to refer to an existing data format instance.
     */
    public RestConfigurationProperties withJsonDataFormat(String jsonDataFormat) {
        setJsonDataFormat(jsonDataFormat);
        return this;
    }

    /**
     * Name of specific XML data format to use. By default jaxb will be used. Important: This option is only for setting
     * a custom name of the data format, not to refer to an existing data format instance.
     */
    public RestConfigurationProperties withXmlDataFormat(String xmlDataFormat) {
        setXmlDataFormat(xmlDataFormat);
        return this;
    }

    /**
     * Adds a component property
     */
    public RestConfigurationProperties withComponentProperty(String key, Object value) {
        if (getComponentProperties() == null) {
            setComponentProperties(new HashMap<>());
        }
        getComponentProperties().put(key, value);
        return this;
    }

    /**
     * Adds a endpoint property
     */
    public RestConfigurationProperties withEndpointProperty(String key, Object value) {
        if (getEndpointProperties() == null) {
            setEndpointProperties(new HashMap<>());
        }
        getEndpointProperties().put(key, value);
        return this;
    }

    /**
     * Adds a consumer property
     */
    public RestConfigurationProperties withConsumerProperty(String key, Object value) {
        if (getConsumerProperties() == null) {
            setConsumerProperties(new HashMap<>());
        }
        getConsumerProperties().put(key, value);
        return this;
    }

    /**
     * Adds a data format property
     */
    public RestConfigurationProperties withDataFormatProperty(String key, Object value) {
        if (getDataFormatProperties() == null) {
            setDataFormatProperties(new HashMap<>());
        }
        getDataFormatProperties().put(key, value);
        return this;
    }

    /**
     * Adds a api property
     */
    public RestConfigurationProperties withApiProperty(String key, Object value) {
        if (getApiProperties() == null) {
            setApiProperties(new HashMap<>());
        }
        getApiProperties().put(key, value);
        return this;
    }

    /**
     * Adds a CORS header property
     */
    public RestConfigurationProperties withCorsHeader(String key, String value) {
        if (getCorsHeaders() == null) {
            setCorsHeaders(new HashMap<>());
        }
        getCorsHeaders().put(key, value);
        return this;
    }

    /**
     * Adds a validation error property
     */
    public RestConfigurationProperties withValidationLevel(String key, String value) {
        if (getValidationLevels() == null) {
            setValidationLevels(new HashMap<>());
        }
        getValidationLevels().put(key, value);
        return this;
    }

}
