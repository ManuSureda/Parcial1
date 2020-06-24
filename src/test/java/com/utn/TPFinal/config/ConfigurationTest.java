package com.utn.TPFinal.config;

import com.utn.TPFinal.session.SessionFilter;
import com.utn.TPFinal.session.SuperSessionFilter;
import com.utn.TPFinal.session.AdminSessionFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class ConfigurationTest {
    Configuration configuration;

    @Mock
    SessionFilter sessionFilter;

    @Mock
    SuperSessionFilter superSessionFilter;

    @Mock
    AdminSessionFilter adminSessionFilter;
    @BeforeEach
    void setUp() {
        initMocks(this);
        configuration = new Configuration(sessionFilter, superSessionFilter, adminSessionFilter);
    }

//    @Test
//    void myFilterEmployee() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(superSessionFilter);
//        registration.addUrlPatterns("/backoffice/*");
//
//        FilterRegistrationBean aux = configuration.myFilterEmployee();
//        assertEquals(aux.getFilter(), registration.getFilter());
//    }
//
//    @Test
//    void myFilterClient() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(sessionFilter);
//        registration.addUrlPatterns("/web/*");
//
//        FilterRegistrationBean aux = configuration.myFilterClient();
//        assertEquals(aux.getFilter(), registration.getFilter());
//    }
//
//    @Test
//    void myFilterAdmin() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(adminSessionFilter);
//        registration.addUrlPatterns("/admin/*");
//
//        FilterRegistrationBean aux = configuration.myFilterAdmin();
//        assertEquals(aux.getFilter(), registration.getFilter());
//    }
}