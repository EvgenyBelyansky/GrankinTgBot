package org.grtgb.grankintgbot.botCommand.defaultCommand;

import org.grtgb.grankintgbot.dto.UserDto;
import org.grtgb.grankintgbot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/registration")
public class RegistrationDefaultCommand implements DefaultCommand {

    private UserService userService;
    private UserDto userDto;


    @Override
    public SendMessage process(Update update) {
        // Если пришёл CallbackQuery (нажатие inline-кнопки)
        if (update.hasCallbackQuery()) {
            return new SendMessage(
                    update.getCallbackQuery().getMessage().getChatId().toString(),
                    "Введите ваше имя:"
            );
        }
        // Если команда вызвана текстом (например, /registration)
        else if (update.hasMessage()) {
            return new SendMessage(
                    update.getMessage().getChatId().toString(),
                    "Введите ваше имя:"
            );
        }
        return null;
    }
}
