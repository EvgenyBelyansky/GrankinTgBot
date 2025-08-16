package org.grtgb.grankintgbot.botCommand;

import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.botCommand.defaultCommand.DefaultCommand;
import org.grtgb.grankintgbot.botCommand.defaultCommand.ErrorDefaultCommand;
import org.grtgb.grankintgbot.enums.UserState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DispatchBotCommand implements Command{

    private final ErrorDefaultCommand errorCommand = new ErrorDefaultCommand();

    private final Map<String, DefaultCommand> commandMap;

    @Override
    public SendMessage process(Update update) {
        String text = update.getMessage().getText();

        System.out.println(commandMap.keySet());

        final DefaultCommand defaultCommand = commandMap.getOrDefault(text, errorCommand);

        return defaultCommand.process(update);
    }


    @Override
    public UserState getState() {
        return UserState.DEFAULT;
    }
}
