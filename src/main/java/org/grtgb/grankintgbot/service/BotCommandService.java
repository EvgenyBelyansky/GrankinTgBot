package org.grtgb.grankintgbot.service;

import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.botCommand.Command;
import org.grtgb.grankintgbot.botCommand.HelpCommand;
import org.grtgb.grankintgbot.botCommand.RegistrationCommand;
import org.grtgb.grankintgbot.botCommand.StartCommand;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BotCommandService {

    private final Map<String, Command> commandMap = Map.of(
            "/start", new StartCommand(),
            "/help", new HelpCommand(),
            "/registration", new RegistrationCommand()
    );


}
