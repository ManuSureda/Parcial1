package com.utn.TPFinal.config;

import com.utn.TPFinal.session.AdminSessionFilter;
import com.utn.TPFinal.session.SessionFilter;
import com.utn.TPFinal.session.SuperSessionFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@org.springframework.context.annotation.Configuration
@PropertySource("application.properties")
@EnableScheduling
@EnableCaching
@AllArgsConstructor
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;

    @Autowired
    SuperSessionFilter superSessionFilter;

    @Autowired
    AdminSessionFilter adminSessionFilter;



    @Bean
    public FilterRegistrationBean myFilterEmployee() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(superSessionFilter);
        registration.addUrlPatterns("/backoffice/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean myFilterClient() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/web/*");

        return registration;
     }

    @Bean
    public FilterRegistrationBean myFilterAdmin() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(adminSessionFilter);
        registration.addUrlPatterns("/admin/*");
        return registration;
    }
}
