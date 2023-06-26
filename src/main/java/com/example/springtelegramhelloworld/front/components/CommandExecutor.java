package com.example.springtelegramhelloworld.front.components;

import com.example.springtelegramhelloworld.back.database.UserRepository;
import com.example.springtelegramhelloworld.back.repository.WeatherRepo;
import com.example.springtelegramhelloworld.front.components.commands.*;
import com.example.springtelegramhelloworld.front.view.WeatherView;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandExecutor {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WeatherView weatherView;
    @Autowired
    private WeatherRepo weatherRepo;

    public CommandExecutor(UserRepository userRepository, WeatherView weatherView, WeatherRepo weatherRepo) {
        this.userRepository = userRepository;
        this.weatherView = weatherView;
        this.weatherRepo = weatherRepo;
    }

    public static Command parseCommand(String receivedMessage) {
        log.info("receivedMessage is:" + receivedMessage);
        Command command = new StartCommand();
        Command.class.getClasses();
        switch (receivedMessage) {
            case "/start"              -> command = new StartCommand();
            case "/help"               -> command = new HelpCommand();
            case "/weather"            -> command = new WeatherCommand();
            case "/changeLanguage"     -> command = new LanguageCommand();
            case "/changeLanguageToRU" -> command = new ChangeLangToRuCommand();
            case "/changeLanguageToEN" -> command = new ChangeLangToEnCommand();
        }
        return command;
    }


    /*
    public SendMessage executeCommand(Command command, User user, Update update) {
        SendMessage message = null;
        switch (command) {
            case HELP -> message = helpMessage(user, update);
            case START -> message = startMessage(user, update);
            case WEATHER -> message = weatherMessage(user, update);
            case LANGUAGE -> message = changeLanguage(user, update);
            case CHANGE_LANGUAGE_TO_RU ->
                    message = changeLanguageToRu(user, update);
            case CHANGE_LANGUAGE_TO_EN ->
                    message = changeLanguageToEn(user, update);
        }
        return message;
    }

    private SendMessage changeLanguageToRu(User user, Update update) {
        Language newlanguage = Language.RU;
        SendMessage message = new SendMessage();
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));
        message.setChatId(user.getUserId());
        message.setText(String.format(bundle.getString("ChangeLanguage.result"), newlanguage));
        user.setLanguage(newlanguage);
        userRepository.save(user);
        message.setReplyMarkup(Buttons.mainMenuButtons(user.getLanguage()));
        return message;

    }

    private SendMessage changeLanguageToEn(User user, Update update) {
        Language newlanguage = Language.EN;
        SendMessage message = new SendMessage();
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));
        message.setChatId(user.getUserId());
        message.setText(String.format(bundle.getString("ChangeLanguage.result"), newlanguage));
        user.setLanguage(newlanguage);
        userRepository.save(user);
        message.setReplyMarkup(Buttons.mainMenuButtons(user.getLanguage()));
        return message;

    }

    private SendMessage weatherMessage(User user, Update update) {
        return weatherView.prepareAnswer(user, weatherRepo.getWeather("Minsk"));
    }

    private SendMessage changeLanguage(User user, Update update) {
        SendMessage message = new SendMessage();
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));
        message.setChatId(user.getUserId());
        message.setText(bundle.getString("ChangeLanguage.preview"));
        message.setReplyMarkup(Buttons.selectLanguageButtons(user.getLanguage()));
        return message;
    }

    private SendMessage startMessage(User user, Update update) {
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
        message.setReplyMarkup(Buttons.mainMenuButtons(user.getLanguage()));


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

    private SendMessage helpMessage(User user, Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(user.getUserId());
        ResourceBundle bundle = ResourceBundle.getBundle("language\\messages"
                , Locale.forLanguageTag(user.getLanguage().getValue()));
        message.setText(bundle.getString("help.text"));
        return message;
    }

*/
}
