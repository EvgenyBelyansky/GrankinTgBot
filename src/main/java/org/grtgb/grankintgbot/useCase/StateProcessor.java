package org.grtgb.grankintgbot.useCase;

import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.botCommand.Command;
import org.grtgb.grankintgbot.enums.UserState;
import org.grtgb.grankintgbot.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StateProcessor {

    private final UserRepository userRepository;

    private final Map<UserState, Command> commandMap;

    public SendMessage execute(Update update) {

        final UserState userState = userRepository.findByChatId(update.getMessage().getChatId()).getUserState();

        System.out.println(commandMap.keySet());

        final Command command = commandMap.get(userState);

        return command.process(update);
    }

}
