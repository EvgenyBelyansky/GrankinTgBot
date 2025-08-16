package org.grtgb.grankintgbot.botCommand.defaultCommand;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/help")
public class HelpDefaultCommand implements DefaultCommand {
    @Override
    public SendMessage process(Update update) {
        return new SendMessage(
                update.getMessage().getChatId().toString(),
                ("Привет %s. Вот список доступных команд: " +
                        "\n/start - начало работы с ботом; " +
                        "\n/registration - регистрация нового пользователя;" +
                        "\n/lesson - записаться на урок").formatted(update.getMessage().getFrom().getFirstName())
        );
    }
}
