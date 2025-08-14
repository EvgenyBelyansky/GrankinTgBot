package org.grtgb.grankintgbot.botCommand;

import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.entity.UserEntity;
import org.grtgb.grankintgbot.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Instant;
import java.util.Optional;

@Component("/start")
@RequiredArgsConstructor
public class StartCommand implements Command{

    private final UserRepository userRepository;

    @Override
    public SendMessage process(Update update) {

        final Long chatId = update.getMessage().getChatId();

        registerOrUpdateLoginDate(chatId);

        return new SendMessage(
                chatId.toString(),
                "Привет %s. Хочешь записаться на урок?".formatted(update.getMessage().getFrom().getFirstName())
        );
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
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
