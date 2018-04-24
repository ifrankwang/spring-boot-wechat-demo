package me.frank.spring.boot.wechat;

import me.frank.spring.boot.wechat.properties.WechatProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationConfigTests {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WechatProperties properties;

    @Test
    public void propertiesTes() {
        logger.info(properties.getAppId());
    }
}
