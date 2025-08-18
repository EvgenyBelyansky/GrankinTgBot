package org.grtgb.grankintgbot.bot;


import lombok.Getter;
import org.grtgb.grankintgbot.config.BotConfig;
import org.grtgb.grankintgbot.entity.UserEntity;
import org.grtgb.grankintgbot.repository.UserRepository;
import org.grtgb.grankintgbot.service.LessonService;
import org.grtgb.grankintgbot.useCase.StateProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class Bot extends TelegramLongPollingBot {

    private final LessonService lessonService;
    private final String botToken;
    private final String botUsername;
    private final StateProcessor stateProcessor;
    private final UserRepository userRepository;

    public Bot(LessonService lessonService, BotConfig botConfig, StateProcessor stateProcessor, UserRepository userRepository) {
        this.lessonService = lessonService;
        this.botToken = botConfig.getBotToken();
        this.botUsername = botConfig.getUserName();
        this.stateProcessor = stateProcessor;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() || update.hasChatMember()) {
            long chatId;

            if (update.hasMessage()) {
                chatId = update.getMessage().getChatId();
            } else {
                chatId = update.getChatMember().getChat().getId();
            }

            sendStartButton(chatId);
        }


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

    private void sendStartButton(Long chatId) {



        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Привет, для начала нажми [Старт!]");


        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow rowStart = new KeyboardRow();
        rowStart.add(new KeyboardButton("/start"));

        keyboardRows.add(rowStart);
        keyboardMarkup.setKeyboard(keyboardRows);

        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
