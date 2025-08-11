package org.grtgb.grankintgbot.botCommand;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Command {

    SendMessage process(Update update);

}
