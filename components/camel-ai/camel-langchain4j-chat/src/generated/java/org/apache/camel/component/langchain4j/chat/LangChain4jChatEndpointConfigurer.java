/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.langchain4j.chat;

import javax.annotation.processing.Generated;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointSchemaGeneratorMojo")
@SuppressWarnings("unchecked")
public class LangChain4jChatEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        LangChain4jChatEndpoint target = (LangChain4jChatEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": target.setBridgeErrorHandler(property(camelContext, boolean.class, value)); return true;
        case "chatmodel":
        case "chatModel": target.getConfiguration().setChatModel(property(camelContext, dev.langchain4j.model.chat.ChatModel.class, value)); return true;
        case "chatoperation":
        case "chatOperation": target.getConfiguration().setChatOperation(property(camelContext, org.apache.camel.component.langchain4j.chat.LangChain4jChatOperations.class, value)); return true;
        case "exceptionhandler":
        case "exceptionHandler": target.setExceptionHandler(property(camelContext, org.apache.camel.spi.ExceptionHandler.class, value)); return true;
        case "exchangepattern":
        case "exchangePattern": target.setExchangePattern(property(camelContext, org.apache.camel.ExchangePattern.class, value)); return true;
        case "lazystartproducer":
        case "lazyStartProducer": target.setLazyStartProducer(property(camelContext, boolean.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public String[] getAutowiredNames() {
        return new String[]{"chatModel"};
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return boolean.class;
        case "chatmodel":
        case "chatModel": return dev.langchain4j.model.chat.ChatModel.class;
        case "chatoperation":
        case "chatOperation": return org.apache.camel.component.langchain4j.chat.LangChain4jChatOperations.class;
        case "exceptionhandler":
        case "exceptionHandler": return org.apache.camel.spi.ExceptionHandler.class;
        case "exchangepattern":
        case "exchangePattern": return org.apache.camel.ExchangePattern.class;
        case "lazystartproducer":
        case "lazyStartProducer": return boolean.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        LangChain4jChatEndpoint target = (LangChain4jChatEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return target.isBridgeErrorHandler();
        case "chatmodel":
        case "chatModel": return target.getConfiguration().getChatModel();
        case "chatoperation":
        case "chatOperation": return target.getConfiguration().getChatOperation();
        case "exceptionhandler":
        case "exceptionHandler": return target.getExceptionHandler();
        case "exchangepattern":
        case "exchangePattern": return target.getExchangePattern();
        case "lazystartproducer":
        case "lazyStartProducer": return target.isLazyStartProducer();
        default: return null;
        }
    }
}

