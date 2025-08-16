package org.grtgb.grankintgbot.botCommand.defaultCommand;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ErrorDefaultCommand implements DefaultCommand {
    @Override
    public SendMessage process(Update update) {
        return new SendMessage(
                update.getMessage().getChatId().toString(),
                "%s отправил несуществующую команду!!!!".formatted(update.getMessage().getFrom().getFirstName())
        );
    }
}
