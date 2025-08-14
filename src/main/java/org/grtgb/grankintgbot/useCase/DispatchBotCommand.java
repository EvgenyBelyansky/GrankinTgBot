package org.grtgb.grankintgbot.useCase;

import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.botCommand.*;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class DispatchBotCommand {

    private final ErrorCommand errorCommand = new ErrorCommand();

    private final Map<String, Command> commandMap;
//            = Map.of(
//            "/start", new StartCommand(),
//            "/help", new HelpCommand(),
//            "/registration", new RegistrationCommand()
//    );

    public SendMessage execute(Update update) {
        String text = update.getMessage().getText();

        System.out.println(commandMap.keySet());


        Pattern pattern = Pattern.compile("^(/\\w+)\\s*(.*)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            text = matcher.group(1);
        }

        final Command command = commandMap.getOrDefault(text, errorCommand);

        return command.process(update);
    }


}
