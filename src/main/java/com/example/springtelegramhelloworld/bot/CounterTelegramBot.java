package com.example.springtelegramhelloworld.bot;

import com.example.springtelegramhelloworld.components.BotCommands;
import com.example.springtelegramhelloworld.components.Buttons;
import com.example.springtelegramhelloworld.components.Language;
import com.example.springtelegramhelloworld.config.BotConfig;
import com.example.springtelegramhelloworld.database.User;
import com.example.springtelegramhelloworld.database.UserRepository;
import com.example.springtelegramhelloworld.repository.WeatherRepo;
import com.example.springtelegramhelloworld.view.WeatherView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private WeatherRepo weatherRepo = new WeatherRepo(); //TODO REMOVE NEW
    private WeatherView weatherView = new WeatherView(); //TODO REMOVE NEW

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
        long userId = 0;
        String userName = null;
        String receivedMessage;

        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userId = update.getMessage().getFrom().getId();
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                botAnswerUtils(receivedMessage, chatId, userName);
            }
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            botAnswerUtils(receivedMessage, chatId, userName);
        }

        /*if(chatId == Long.valueOf(config.getChatId())){
            updateDB(userId, userName);
        }*/
    }

    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        switch (receivedMessage) {
            case "/start":
                startBot(chatId, userName);
                break;
            case "/help":
                sendHelpText(chatId, HELP_TEXT);
                break;
            case "/weather":
                sendWeather(chatId, "Minsk");
                break;
            case "/changeLanguage":
                selectLanguage(chatId);
                break;
            case "/changeLanguageToRU":
                changeLanguage(chatId, Language.RU);
                break;
            case "/changeLanguageToEN":
                changeLanguage(chatId, Language.EN);
                break;
            default:
                break;
        }
    }

    private void selectLanguage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("select Language:");
        message.setReplyMarkup(Buttons.selectLanguageButtons());
        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void changeLanguage(long chatId, Language language) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("English selected");
        message.setReplyMarkup(Buttons.mainMenuButtons());
        try {
            Optional<User> user = userRepository.findById(chatId);
            if (user.isPresent()) {
                user.get().setLanguage(language);
                userRepository.save(user.get());
            } else {
                //todo user = Optional.of(new User(chatId, Language.EN, new HashSet<>()));
            }
            execute(message);
            log.info(String.format("user %d change language to %s",chatId,language.getValue()));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hi, " + userName + "! I'm a Telegram bot.'");
        message.setReplyMarkup(Buttons.mainMenuButtons());
        try {
            Optional<User> user = userRepository.findById(chatId);
            if (user.isEmpty()) {
                userRepository.save(new User(chatId, Language.EN, new HashSet<>()));
            }
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendWeather(long chatId, String city) {
        try {
            execute(weatherView.prepareAnswer(chatId, weatherRepo.getWeather()));
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