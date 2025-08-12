package org.grtgb.grankintgbot.bot;


import org.grtgb.grankintgbot.botCommand.*;
import org.grtgb.grankintgbot.service.BotCommandService;
import org.grtgb.grankintgbot.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Bot extends TelegramLongPollingBot {

    private final LessonService lessonService;
    private final BotCommandService botCommandService;

    private final Map<String, Command> commandMap = Map.of(
            "/start", new StartCommand(),
            "/help", new HelpCommand(),
            "/registration", new RegistrationCommand()
    );

    private final ErrorCommand errorCommand = new ErrorCommand();


    @Override
    public void onUpdateReceived(Update update) {
        final String text = update.getMessage().getText();


        Pattern pattern = Pattern.compile("^/(\\w+)\\s*(.*)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            String command = matcher.group(1);
            String args = matcher.group(2);
            System.out.println("Команда: " + command);
            System.out.println("Аргументы: " + args);
        } else {
            System.out.println("Неверный формат команды!");
        }

        final Command command = commandMap.getOrDefault(text, errorCommand);

        SendMessage sendMessage = command.process(update);

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
    public Bot(LessonService lessonService, BotCommandService botCommandService) {
        super("");
        this.lessonService = lessonService;
        this.botCommandService = botCommandService;
    }
}
