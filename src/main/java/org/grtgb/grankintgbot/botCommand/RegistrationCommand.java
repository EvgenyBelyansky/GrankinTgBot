package org.grtgb.grankintgbot.botCommand;

import org.grtgb.grankintgbot.dto.UserDto;
import org.grtgb.grankintgbot.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class RegistrationCommand implements Command{

    private UserService userService;
    private UserDto userDto;


    @Override
    public SendMessage process(Update update) {

//        userService.registration(userDto);

        System.out.println(update.getMessage().getText());

        return new SendMessage(
                update.getMessage().getChatId().toString(),
                "Привет %s. Ты зарегистрирован, можешь записываться на занятия"
                        .formatted(update
                                .getMessage()
                                .getFrom()
                                .getFirstName()
                        )
        );
    }
}
