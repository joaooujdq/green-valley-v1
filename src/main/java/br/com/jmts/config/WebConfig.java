package br.com.jmts.config;

import br.com.jmts.serialization.converter.YamlJackson2HttpMesageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMesageConverter());
    }

    private static final MediaType MEDIA_TYPE_APPLICATION_YAML = MediaType.valueOf("application/x-yaml");

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {


        /*
       Content Negotiation define quais formatos de responses são aceitos pela aplicação, por exemplo,
       xml, csv, json. E tambem define onde são declarados os parametros das requisições, no Header ou na Query.

       Via Query:

       configurer.favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);

       Via Header:                                                 */

       configurer.favorParameter(false).ignoreAcceptHeader(false)
               .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
               .mediaType("json", MediaType.APPLICATION_JSON)
               .mediaType("xml", MediaType.APPLICATION_XML)
               .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YAML);
    }
}
