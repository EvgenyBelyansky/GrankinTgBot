package org.grtgb.grankintgbot;

import org.grtgb.grankintgbot.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BotConfig.class)
public class GrankinTgBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrankinTgBotApplication.class, args);
    }

}
