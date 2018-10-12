package ealen.bookmanager.config;

import ealen.bookmanager.util.FreemarkerExceptionHandler;
import ealen.bookmanager.util.UTF8StringHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
public class FreeMarkerConfig {
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected FreeMarkerViewResolver resolver;

    @PostConstruct
    public void setSharedVariable() {
        configuration.setTagSyntax(freemarker.template.Configuration.AUTO_DETECT_TAG_SYNTAX);
        configuration.setTemplateUpdateDelayMilliseconds(500);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setURLEscapingCharset("UTF-8");
        configuration.setLocale(Locale.CHINA);
        configuration.setBooleanFormat("true,false");
        configuration.setDateTimeFormat("yyyy-MM-dd HH\\:mm\\:ss");
        configuration.setDateFormat("yyyy-MM-dd");
        configuration.setTimeFormat("HH\\:mm\\:ss");
        configuration.setNumberFormat("0.######");
        configuration.setWhitespaceStripping(true);
        configuration.setTemplateExceptionHandler(new FreemarkerExceptionHandler());
        resolver.setContentType("text/html;charset=utf-8");
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".html");
        resolver.setAllowRequestOverride(true);
        resolver.setOrder(0);
    }

    @Bean(name = "freemarkerConfig")
    public FreeMarkerConfigurer getFreemarkerConfig() {
        Map<String, Object> freemarkerVariables = new HashMap<String, Object>();
        freemarkerVariables.put("context", "/bookmanager");
        freemarkerVariables.put("static", "/bookmanager/static");
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setFreemarkerVariables(freemarkerVariables);
        configurer.setTemplateLoaderPath("classpath:/templates/");
        return configurer;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/locale/messages");
        return messageSource;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolverBean() {
        return new SessionLocaleResolver();
    }

    @Bean
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new UTF8StringHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        adapter.setMessageConverters(converters);
        return adapter;
    }
}