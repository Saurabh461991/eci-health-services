package org.cdac.gist;

import org.joda.time.MutableDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;


/**
 * Created by kamalp on 26-01-2015.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application;
    }


    @Configuration
    static class ExtendedWebSecurityConfigurerAdapter extends
            WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            MutableDateTime mutableDateTime = new MutableDateTime(new Date());
            mutableDateTime.addDays(3);

            http
                    .headers()
                    .httpStrictTransportSecurity()
                    .xssProtection()
                    .frameOptions()
                    .disable()
                    .headers()
                    .addHeaderWriter(new StaticHeadersWriter("Server", "Secured App Servers"))
                    .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                    .addHeaderWriter(new StaticHeadersWriter("Expires", mutableDateTime.toString()))
                    .addHeaderWriter(new StaticHeadersWriter("Cache-Control", "no-cache,no-store,must-revalidate"))
                    .addHeaderWriter(new StaticHeadersWriter("Keep-Alive", "300"))
                    .addHeaderWriter(new StaticHeadersWriter("Pragma", "no-cache"))
            ;
        }
    }

    @Configuration
    static class ExtendedWebMvcConfiguration extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addRedirectViewController("/", "/index");
            // registry.addViewController("/formscount");
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/resources/css/**")
                    .addResourceLocations("/resources/public/css/")
                    .setCachePeriod(31556926);

            registry
                    .addResourceHandler("/resources/js/**")
                    .addResourceLocations("/resources/public/js/")
                    .setCachePeriod(31556926);
        }
    }
}
