package io.jacy.drafts.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Components {


    @Bean(initMethod = "init", destroyMethod = "destroy")
    BeanCreate beanCreate() {
        return new BeanCreate();
    }
}
