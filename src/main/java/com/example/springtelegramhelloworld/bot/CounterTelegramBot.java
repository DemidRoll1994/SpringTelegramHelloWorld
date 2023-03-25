package com.example.springtelegramhelloworld.bot;

import com.example.springtelegramhelloworld.components.*;
import com.example.springtelegramhelloworld.config.BotConfig;
import com.example.springtelegramhelloworld.database.HibernateUtil;
import com.example.springtelegramhelloworld.database.User;
import com.example.springtelegramhelloworld.repository.WeatherRepo;
import com.example.springtelegramhelloworld.view.WeatherView;
import com.fasterxml.classmate.AnnotationConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Locale;
import java.util.ResourceBundle;


@Slf4j
@Component
public class CounterTelegramBot extends TelegramLongPollingBot implements BotCommands {
    /*    @Autowired
        private UserRepository userRepository;*/
    final BotConfig config;
    private WeatherRepo weatherRepo = new WeatherRepo(); //TODO REMOVE NEW
    private WeatherView weatherView = new WeatherView(); //TODO REMOVE NEW

    ResourceBundle localMessageBundle = ResourceBundle.getBundle("language.messages", Locale.forLanguageTag("EN"));

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
        /*Command command = Command.valueOf(receivedMessage); // можно сломать, если передать не тот аргумент, например "/generateErrorRandomTag111111" TODO
        CommandFactory factory = new CommandFactory();
        factory.executeCommand(command);*/

        switch (receivedMessage) {
            case "/start":
                startBot(chatId, userName);
                break;
            case "/help":
                sendHelpText(chatId, localMessageBundle.getString("help.text"));
                break;
            case "/weather":
                sendWeather(chatId, "Minsk");
                break;
            case "/language":
                saveLanguageToDatabase(1l,"");
                break;
            default:
                break;
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(String.format("Hi, %s! I'm a Telegram bot.'", userName));
        message.setReplyMarkup(Buttons.mainMenuButtons());


        try {
            message.getChatId();
            execute(message);
            log.info("Start Reply sent");
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
            log.info("Help Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void saveLanguageToDatabase(long chatId, String textToSend) {

        SessionFactory factory;
        try {
            factory = new AnnotationConfiguration().
                    configure().addAnnotatedClass(User.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }





        /*Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, chatId);
            user.setLanguage(Language.RU.getValue());
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
/*
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Student > students = session.createQuery("from Student", Student.class).list();
            students.forEach(s - > System.out.println(s.getFirstName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }*/
    }

    private void sendWeather(long chatId, String city) {
        try {
            execute(weatherView.prepareAnswer(chatId, weatherRepo.getWeather()));
            log.info("Weather Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

/*
    private void updateDB(long userId, String userName) {
        if(userRepository.findById(userId).isEmpty()){
            User user = new User();
            user.setId(userId);
            user.setName(userName);
            //сразу добавляем в столбец каунтера 1 сообщение
            user.setMsg_numb(1);

            userRepository.save(user);
            log.info("Added to DB: " + user);
        } else {
            userRepository.updateMsgNumberByUserId(userId);
        }
    }*/
}