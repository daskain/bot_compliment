package compliment;

import java.util.Timer;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotForYou extends TelegramLongPollingBot {
	
	public String getBotUsername() {
        // TODO
        return "compiment_bot";
    }

    
    public String getBotToken() {
        // TODO
        return "867096059:AAF7A6ezg4ud6sHTh_jh6iPpL-ng0iOXYzo";
    }
    
    public void onUpdateReceived(final Update update) {
        // We check if the update has a message and the message has text
    	Timer timer = new Timer();
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            
        }
        Thread run = new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {
                    	SendMessage message = new SendMessage()
                    			.setChatId(update.getMessage().getChatId())// Create a SendMessage object with mandatory fields
                                .setText("Это первый пробный комплимент - ты самая красивая!");
                        Thread.sleep(1000); //1000 - 1 сек
                        try {
                            execute(message); // Call method to send the message
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        run.start(); // заводим
    }
}