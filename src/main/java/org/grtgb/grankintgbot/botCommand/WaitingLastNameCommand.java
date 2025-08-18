package org.grtgb.grankintgbot.botCommand;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.grtgb.grankintgbot.entity.UserEntity;
import org.grtgb.grankintgbot.enums.RegistrationState;
import org.grtgb.grankintgbot.enums.UserState;
import org.grtgb.grankintgbot.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class WaitingLastNameCommand implements Command{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public SendMessage process(Update update) {

        final Long chatId = update.getMessage().getChatId();
        final UserEntity user = userRepository.findByChatId(chatId);

        user.setLastName(update.getMessage().getText());

        user.setUserState(UserState.DEFAULT);
        user.setUserRegistrationState(RegistrationState.REGISTERED);

        userRepository.save(user);
        return null;
    }

    @Override
    public UserState getState() {
        return UserState.WAITING_LAST_NAME;
    }
}
