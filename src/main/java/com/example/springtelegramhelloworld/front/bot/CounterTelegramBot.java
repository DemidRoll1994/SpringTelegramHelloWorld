package com.example.springtelegramhelloworld.front.bot;

import com.example.springtelegramhelloworld.front.components.*;
import com.example.springtelegramhelloworld.front.config.BotConfig;
import com.example.springtelegramhelloworld.back.database.User;
import com.example.springtelegramhelloworld.back.database.UserRepository;
import com.example.springtelegramhelloworld.back.repository.WeatherRepo;
import com.example.springtelegramhelloworld.front.view.WeatherView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Optional;


@Slf4j
@Component
public class CounterTelegramBot extends TelegramLongPollingBot implements BotCommands {
    @Autowired
    private UserRepository userRepository;

    final BotConfig config;
    @Autowired
    private WeatherRepo weatherRepo;
    @Autowired
    private WeatherView weatherView;

    @Autowired
    private CommandExecutor commandExecutor;

    public CounterTelegramBot(BotConfig config) {
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = 0;
        String receivedMessage;
        User user = null;

        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            if (userRepository.findById(chatId).isEmpty()) {
                try {
                    execute(commandExecutor.executeCommand(Command.START, new User(), update));
                    log.info("Reply sent");
                } catch (TelegramApiException e) {
                    log.error(e.getMessage());
                }

            }
            user = userRepository.findById(chatId).get();
            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                botAnswerUtils(receivedMessage, user, update);
            }
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            if (userRepository.findById(chatId).isEmpty()) {
                try {
                    execute(commandExecutor.executeCommand(Command.START, new User(), update));
                    log.info("Reply sent");
                } catch (TelegramApiException e) {
                    log.error(e.getMessage());
                }
            }
            user = userRepository.findById(chatId).get();
            receivedMessage = update.getCallbackQuery().getData();
            botAnswerUtils(receivedMessage, user, update);
        }

    }

    public void onUpdateReceived2(Update update) {
        long chatId = 0;
        //long userId = 0;
        String userName = null;
        String receivedMessage;
        User user = null;

        if (update.hasMessage()) {
            //userId = update.getMessage().getFrom().getId(); //in private chats userId=chatId
            chatId = update.getMessage().getChatId();
            if (userRepository.findById(chatId).isEmpty()) {
                startBot(chatId, userName);
            }
            user = userRepository.findById(chatId).get();
            userName = update.getMessage().getFrom().getFirstName();
            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                botAnswerUtils2(receivedMessage, user, userName);
            }
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            //userId = update.getCallbackQuery().getFrom().getId();
            if (userRepository.findById(chatId).isEmpty()) {
                startBot(chatId, userName);
            }
            user = userRepository.findById(chatId).get();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();
            botAnswerUtils2(receivedMessage, user, userName);
        }
        /*if(chatId == Long.valueOf(config.getChatId())){
            updateDB(userId, userName);
        }*/
    }

    private void botAnswerUtils(String receivedMessage, User user, Update update) {
        log.info("receivedMessage is:" + receivedMessage);
        Command command = Command.START;
        switch (receivedMessage) {
            case "/start":
                command = Command.START;
                if (user == null) {user = new User();}
                break;
            case "/help":
                command = Command.HELP;
                break;
            case "/weather":
                command = Command.WEATHER;
                break;
            case "/changeLanguage":
                command = Command.LANGUAGE;
                break;
            case "/changeLanguageToRU":
                command = Command.CHANGE_LANGUAGE_TO_RU;
                break;
            case "/changeLanguageToEN":
                command = Command.CHANGE_LANGUAGE_TO_EN;
                break;
            default:
                break;
        }
        try {
            execute(commandExecutor.executeCommand(command, user, update));
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void botAnswerUtils2(String receivedMessage, User user, String userName) {
        log.info("receivedMessage is:" + receivedMessage);
        switch (receivedMessage) {
            case "/start":
                startBot(user.getUserId(), userName);
                break;
            case "/help":
                sendHelpText(user);
                break;
            case "/weather":
                sendWeather(user, "Minsk");
                break;
            case "/changeLanguage":
                selectLanguage(user);
                break;
            case "/changeLanguageToRU":
                changeLanguage(user, Language.RU);
                break;
            case "/changeLanguageToEN":
                changeLanguage(user, Language.EN);
                break;
            default:
                break;
        }
    }

    private void selectLanguage(User user) {
        SendMessage message = new SendMessage();
        message.setChatId(user.getUserId());
        message.setText("select Language:");
        message.setReplyMarkup(Buttons.selectLanguageButtons(user.getLanguage()));
        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void changeLanguage(User user, Language newLanguage) {
        SendMessage message = new SendMessage();
        message.setChatId(user.getUserId());
        message.setText(newLanguage.getValue() + " selected");
        user.setLanguage(newLanguage);
        userRepository.save(user);
        message.setReplyMarkup(Buttons.mainMenuButtons(user.getLanguage()));
        try {
            execute(message);
            log.info(String.format("user %d change language to %s", user.getUserId(), newLanguage.getValue()));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hi, " + userName + "! I'm a Telegram bot.'");
        message.setReplyMarkup(Buttons.mainMenuButtons(Language.EN));
        try {
            Optional<User> user = userRepository.findById(chatId);
            if (user.isEmpty()) {
                userRepository.save(new User(chatId, Language.EN));
            }
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(User user) {
        SendMessage message = new SendMessage();
        message.setChatId(user.getUserId());
        message.setText(HELP_TEXT); //todo i18n
        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendWeather(User user, String city) {
        try {
            execute(weatherView.prepareAnswer(user, weatherRepo.getWeather(city)));
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

/*
    private void updateDB(long userId, String userName) {
        if(userRepository.findById(userId).isEmpty()){
            User user = new User();
            user.setUserId(userId);
            user.setLanguage(userName);
            //сразу добавляем в столбец каунтера 1 сообщение
            user.setWeatherCities(new HashSet<>());

            userRepository.save(user);
            log.info("Added to DB: " + user);
        } else {
            userRepository.updateMsgNumberByUserId(userId);
        }
    }*/
}