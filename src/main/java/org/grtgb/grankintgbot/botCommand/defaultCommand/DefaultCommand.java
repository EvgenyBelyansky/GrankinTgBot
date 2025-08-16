package org.grtgb.grankintgbot.botCommand.defaultCommand;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface DefaultCommand {

    SendMessage process(Update update);

}
