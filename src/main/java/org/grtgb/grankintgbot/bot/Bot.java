package org.grtgb.grankintgbot.bot;


import org.grtgb.grankintgbot.entity.UserEntity;
import org.grtgb.grankintgbot.repository.UserRepository;
import org.grtgb.grankintgbot.service.LessonService;
import org.grtgb.grankintgbot.useCase.StateProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Instant;

@Component
public class Bot extends TelegramLongPollingBot {
    private final LessonService lessonService;

    private final StateProcessor stateProcessor;
    private final UserRepository userRepository;


    public Bot(LessonService lessonService, StateProcessor stateProcessor, UserRepository userRepository) {
        super("8442309871:AAGbJLKum_xpRaMYUPNwv4zq8O7XBsdRTi0");
        this.lessonService = lessonService;
        this.stateProcessor = stateProcessor;
        this.userRepository = userRepository;
    }



    @Override
    @Transactional
    public void onUpdateReceived(Update update) {

        System.out.println(update.getMessage());
        registerOrUpdateLoginDate(update.getMessage().getChatId());
        final SendMessage sendMessage = stateProcessor.execute(update);

        executeMessage(sendMessage);
    }


    private void executeMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        return "grankinChess_bot";
    }


    protected void registerOrUpdateLoginDate(Long chatId) {

        UserEntity user = userRepository.findByChatId(chatId);

        if (user == null) {
            user = UserEntity.builder()
                    .chatId(chatId)
                    .build();
        } else {
            user.setLoginDate(Instant.now());
        }


        userRepository.save(user);

    }
}
