package org.grtgb.grankintgbot.botCommand;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartCommand implements Command{

    @Override
    public SendMessage process(Update update) {

        return new SendMessage(
                update.getMessage().getChatId().toString(),
                "Привет %s. Хочешь записаться на урок?".formatted(update.getMessage().getFrom().getFirstName())
        );
    }
}
