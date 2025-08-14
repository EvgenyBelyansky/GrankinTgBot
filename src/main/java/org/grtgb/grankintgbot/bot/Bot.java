package org.grtgb.grankintgbot.bot;


import org.grtgb.grankintgbot.service.LessonService;
import org.grtgb.grankintgbot.useCase.DispatchBotCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    private final LessonService lessonService;
    private final DispatchBotCommand dispatchBotCommand;



    @Override
    public void onUpdateReceived(Update update) {
        final SendMessage sendMessage = dispatchBotCommand.execute(update);

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

    @Autowired
    public Bot(LessonService lessonService, DispatchBotCommand dispatchBotCommand) {
        super("8442309871:AAGbJLKum_xpRaMYUPNwv4zq8O7XBsdRTi0");
        this.lessonService = lessonService;
        this.dispatchBotCommand = dispatchBotCommand;
    }
}
