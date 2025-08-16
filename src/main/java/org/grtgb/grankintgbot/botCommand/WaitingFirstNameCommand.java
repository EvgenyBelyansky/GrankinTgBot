package org.grtgb.grankintgbot.botCommand;

import org.grtgb.grankintgbot.enums.UserState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WaitingFirstNameCommand implements Command{
    @Override
    public SendMessage process(Update update) {
        return null;
    }

    @Override
    public UserState getState() {
        return null;
    }
}
