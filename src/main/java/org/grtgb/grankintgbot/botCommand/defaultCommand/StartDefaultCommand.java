package org.grtgb.grankintgbot.botCommand.defaultCommand;

import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component("/start")
@RequiredArgsConstructor
public class StartDefaultCommand implements DefaultCommand {

    private final UserRepository userRepository;

//    @Override
//    public SendMessage process(Update update) {
//
//        final Long chatId = update.getMessage().getChatId();
//
//        registerOrUpdateLoginDate(chatId);
//
//
//        return new SendMessage(
//                chatId.toString(),
//                "Привет %s. Хочешь записаться на урок?".formatted(update.getMessage().getFrom().getFirstName())
//        );
//
//
//    }

    @Override
    public SendMessage process(Update update) {

        final Long chatId = update.getMessage().getChatId();



            String messageText = "Привет %s. Если хочешь записаться на урок, зарегистрируйся"
                    .formatted(update.getMessage().getFrom().getFirstName());

            InlineKeyboardButton button = new InlineKeyboardButton("✅ Зарегистрироваться");
            button.setCallbackData("/registration");

            InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
            keyboard.setKeyboard(List.of(
                    List.of(button)
            ));

            return SendMessage.builder()
                    .chatId(chatId.toString())
                    .text(messageText)
                    .replyMarkup(keyboard)
                    .build();

    }







}
