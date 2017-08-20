package br.com.igbeni.crawler.telegram;

import br.com.igbeni.crawler.Crawler;
import br.com.igbeni.crawler.model.SubReddit;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.Collections;
import java.util.List;

/**
 * Telegram bot for sending reddit top thread listing.
 *
 * @author Iggor Alves
 */
public class Bot extends TelegramLongPollingBot {
    static final String BOT_TOKEN = "417804166:AAHY9lD6i7Kdcq2kbRuc4HJn6sRTtcv0Mn0";
    static final String BOT_USERNAME = "QuimeraBot";

    /**
     * This method is called when receiving updates via GetUpdates method.
     *
     * @param update Update received.
     */
    @Override
    public void onUpdateReceived(@NotNull Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String[] input = update.getMessage().getText().split(" ");

            Long chatId = update.getMessage().getChatId();

            if (input.length > 1) {
                if (input[0].equals("/NadaPraFazer")) {
                    String[] subreddits = input[1].split(";");

                    if (subreddits.length == 0) {
                        sendMessage(chatId, "Invalid number of subreddits.");
                    }

                    sendMessage(chatId, "Searching for the top threads of the moment on Reddit ...");

                    List<SubReddit> subReddits = Crawler.getInstance().getSubRedditsInfo(subreddits);

                    Collections.sort(subReddits);

                    StringBuilder sb = new StringBuilder();

                    for (SubReddit subReddit : subReddits) {
                        sb.append(subReddit).append('\n');
                    }

                    sendMessage(chatId, "The top threads of the moment on Reddit are:");
                    sendMessage(chatId, sb.toString());
                } else {
                    sendMessage(chatId, "Wrong command, type /NadaPraFazer askreddit;worldnews;cats");
                }

            } else {
                sendMessage(chatId, "Invalid number of inputs.\nType /NadaPraFazer askreddit;worldnews;cats");
            }
        }
    }

    /**
     * Function to send text message to a chat.
     *
     * @param chatId Destination chat id.
     * @param message The message to be sent.
     */
    private void sendMessage(@NotNull Long chatId, String message) {
        try {
            sendMessage(
                    new SendMessage()
                            .setChatId(chatId)
                            .setText(message)
            );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return bot username of this bot.
     *
     * @return Bot username
     */
    @NotNull
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    /**
     * Return bot token to access Telegram API.
     *
     * @return Bot token
     */
    @NotNull
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
