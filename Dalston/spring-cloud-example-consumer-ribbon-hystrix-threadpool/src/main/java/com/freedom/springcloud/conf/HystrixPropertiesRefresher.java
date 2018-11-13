package com.freedom.springcloud.conf;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class HystrixPropertiesRefresher implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(HystrixPropertiesRefresher.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) {
        boolean hystrixPropertiesChanged = false;

        //判断发送变更的配置中是否有hystrix相关的
        for (String changedKey : changeEvent.changedKeys()) {
            if (changedKey.startsWith("hystrix.")) {
                hystrixPropertiesChanged = true;
                break;
            }
        }

        if (hystrixPropertiesChanged) {
            refreshHystrixProperties(changeEvent);
        }
    }

    private void refreshHystrixProperties(ConfigChangeEvent changeEvent) {
        logger.info("Refreshing hystrix properties!");

        /**
         * rebind configuration beans
         * @see org.springframework.cloud.netflix.archaius.ArchaiusAutoConfiguration.PropagateEventsConfiguration#onApplicationEvent
         * @see org.springframework.cloud.context.properties.ConfigurationPropertiesRebinder#onApplicationEvent
         */
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));

        logger.info("Hystrix properties refreshed!");
    }

}
