package com.example.springtelegramhelloworld.front.components.commands;

import com.example.springtelegramhelloworld.back.database.User;
import com.example.springtelegramhelloworld.back.database.UserRepository;
import com.example.springtelegramhelloworld.front.bot.Buttons222;
import com.example.springtelegramhelloworld.front.components.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class StartCommand extends Command {

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public StartCommand() {
        super("/start", "Start.textOnButton", "Start.descriptionForHelp");
    }
    public StartCommand(UserRepository userRepository) {
        super("/start", "Start.textOnButton", "Start.descriptionForHelp");
        this.userRepository=userRepository;
    }

    @Override
    public SendMessage prepareMessage(User user, Update update) {
        SendMessage message = new SendMessage();
        long chatId = -1;
        String userName = "";
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
        }
        if (user == null || user.getLanguage() == null) {
            user = createUser(chatId);
        }
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));

        message.setText(String.format(bundle.getString("HiMessage"), userName));
        message.setChatId(chatId);
        message.setReplyMarkup(Buttons222.mainMenuButtons(user.getLanguage()));


        return message;
    }

    private User createUser(long chatId) {
        Optional<User> user = userRepository.findById(chatId);
        if (user.isEmpty()) {
            userRepository.save(new User(chatId, Language.EN));
            user = userRepository.findById(chatId);
        }
        return user.get();
    }
}
