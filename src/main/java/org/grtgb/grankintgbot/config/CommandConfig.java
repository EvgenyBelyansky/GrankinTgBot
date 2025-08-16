package org.grtgb.grankintgbot.config;

import org.grtgb.grankintgbot.botCommand.Command;
import org.grtgb.grankintgbot.enums.UserState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class CommandConfig {

    @Bean
    public Map<UserState, Command> commandMap(List<Command> commands) {
        return commands.stream()
                .collect(Collectors.toMap(Command::getState, Function.identity()));
    }
}
